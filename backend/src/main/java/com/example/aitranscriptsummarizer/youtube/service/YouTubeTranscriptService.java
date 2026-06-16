package com.example.aitranscriptsummarizer.youtube.service;

import com.example.aitranscriptsummarizer.summary.exception.TranscriptUnavailableException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

@Service
public class YouTubeTranscriptService {

    private static final Pattern VIDEO_ID_PATTERN = Pattern.compile(
            "^(?:https?://)?(?:www\\.)?(?:youtube\\.com/watch\\?[^\\s#]*v=|youtu\\.be/)([A-Za-z0-9_-]{11}).*$"
    );
    private static final String CAPTION_TRACKS_TOKEN = "\"captionTracks\":";

    private final TextFetcher textFetcher;
    private final ObjectMapper objectMapper;

    public YouTubeTranscriptService() {
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
        this.textFetcher = uri -> {
            HttpRequest request = HttpRequest.newBuilder(uri)
                    .timeout(Duration.ofSeconds(10))
                    .header("User-Agent", "Mozilla/5.0")
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new TranscriptUnavailableException();
            }
            return response.body();
        };
        this.objectMapper = new ObjectMapper();
    }

    YouTubeTranscriptService(TextFetcher textFetcher, ObjectMapper objectMapper) {
        this.textFetcher = textFetcher;
        this.objectMapper = objectMapper;
    }

    public String extractVideoId(String youtubeUrl) {
        Matcher matcher = VIDEO_ID_PATTERN.matcher(youtubeUrl);
        if (!matcher.matches()) {
            throw new TranscriptUnavailableException();
        }
        return matcher.group(1);
    }

    public String fetchTranscript(String youtubeUrl) {
        try {
            String videoId = extractVideoId(youtubeUrl);
            String watchPage = textFetcher.fetch(URI.create("https://www.youtube.com/watch?v=" + videoId));
            String captionTracksJson = extractCaptionTracksJson(watchPage)
                    .orElseThrow(TranscriptUnavailableException::new);
            JsonNode captionTracks = objectMapper.readTree(captionTracksJson);
            List<String> captionUrls = selectCaptionUrls(captionTracks);
            if (captionUrls.isEmpty()) {
                throw new TranscriptUnavailableException();
            }
            for (String captionUrl : captionUrls) {
                try {
                    String transcriptJson = textFetcher.fetch(URI.create(addJsonFormat(captionUrl)));
                    String transcript = parseTranscriptText(transcriptJson);
                    if (!transcript.isBlank()) {
                        return transcript;
                    }
                } catch (Exception ignored) {
                    // Try the next available track because YouTube can return empty or invalid caption payloads.
                }
            }
            throw new TranscriptUnavailableException();
        } catch (TranscriptUnavailableException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new TranscriptUnavailableException(exception);
        }
    }

    private Optional<String> extractCaptionTracksJson(String watchPage) {
        int tokenStart = watchPage.indexOf(CAPTION_TRACKS_TOKEN);
        if (tokenStart < 0) {
            return Optional.empty();
        }

        int arrayStart = watchPage.indexOf('[', tokenStart);
        if (arrayStart < 0) {
            return Optional.empty();
        }

        int depth = 0;
        boolean inString = false;
        boolean escaped = false;
        for (int index = arrayStart; index < watchPage.length(); index++) {
            char current = watchPage.charAt(index);

            if (escaped) {
                escaped = false;
                continue;
            }
            if (current == '\\') {
                escaped = true;
                continue;
            }
            if (current == '"') {
                inString = !inString;
                continue;
            }
            if (inString) {
                continue;
            }
            if (current == '[') {
                depth++;
            } else if (current == ']') {
                depth--;
                if (depth == 0) {
                    return Optional.of(watchPage.substring(arrayStart, index + 1));
                }
            }
        }
        return Optional.empty();
    }

    private List<String> selectCaptionUrls(JsonNode captionTracks) {
        if (!captionTracks.isArray() || captionTracks.isEmpty()) {
            return List.of();
        }

        Set<String> captionUrls = new LinkedHashSet<>();
        findCaptionUrlByLanguage(captionTracks, "ko").ifPresent(captionUrls::add);
        findCaptionUrlByLanguage(captionTracks, "en").ifPresent(captionUrls::add);

        Iterator<JsonNode> iterator = captionTracks.elements();
        while (iterator.hasNext()) {
            JsonNode track = iterator.next();
            JsonNode baseUrl = track.get("baseUrl");
            if (baseUrl != null && baseUrl.isTextual()) {
                captionUrls.add(baseUrl.asText());
            }
        }
        return new ArrayList<>(captionUrls);
    }

    private Optional<String> findCaptionUrlByLanguage(JsonNode captionTracks, String languageCode) {
        Iterator<JsonNode> iterator = captionTracks.elements();
        while (iterator.hasNext()) {
            JsonNode track = iterator.next();
            JsonNode trackLanguageCode = track.get("languageCode");
            JsonNode baseUrl = track.get("baseUrl");
            if (trackLanguageCode != null && baseUrl != null && languageCode.equals(trackLanguageCode.asText())) {
                return Optional.of(baseUrl.asText());
            }
        }
        return Optional.empty();
    }

    private String addJsonFormat(String captionUrl) {
        String separator = captionUrl.contains("?") ? "&" : "?";
        return captionUrl + separator + "fmt=json3";
    }

    private String parseTranscriptText(String transcriptJson) throws IOException {
        JsonNode root = objectMapper.readTree(transcriptJson);
        JsonNode events = root.get("events");
        if (events == null || !events.isArray()) {
            return "";
        }

        StringBuilder transcript = new StringBuilder();
        for (JsonNode event : events) {
            JsonNode segments = event.get("segs");
            if (segments == null || !segments.isArray()) {
                continue;
            }
            for (JsonNode segment : segments) {
                JsonNode text = segment.get("utf8");
                if (text != null) {
                    transcript.append(text.asText());
                }
            }
            transcript.append(' ');
        }
        return HtmlUtils.htmlUnescape(transcript.toString())
                .replaceAll("\\s+", " ")
                .trim();
    }

    @FunctionalInterface
    interface TextFetcher {
        String fetch(URI uri) throws IOException, InterruptedException;
    }
}
