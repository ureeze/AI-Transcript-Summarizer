package com.example.aitranscriptsummarizer.youtube.service;

import com.example.aitranscriptsummarizer.summary.exception.TranscriptUnavailableException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class YouTubeTranscriptServiceTests {

    @Test
    void extractsVideoIdFromWatchUrl() {
        YouTubeTranscriptService service = new YouTubeTranscriptService(uri -> "", new ObjectMapper());

        String videoId = service.extractVideoId("https://www.youtube.com/watch?v=dQw4w9WgXcQ");

        assertThat(videoId).isEqualTo("dQw4w9WgXcQ");
    }

    @Test
    void extractsVideoIdFromShortUrl() {
        YouTubeTranscriptService service = new YouTubeTranscriptService(uri -> "", new ObjectMapper());

        String videoId = service.extractVideoId("https://youtu.be/dQw4w9WgXcQ");

        assertThat(videoId).isEqualTo("dQw4w9WgXcQ");
    }

    @Test
    void rejectsInvalidVideoUrl() {
        YouTubeTranscriptService service = new YouTubeTranscriptService(uri -> "", new ObjectMapper());

        assertThatThrownBy(() -> service.extractVideoId("https://example.com/video"))
                .isInstanceOf(TranscriptUnavailableException.class)
                .hasMessage("이 영상의 자막을 가져올 수 없습니다.");
    }

    @Test
    void failsWhenCaptionTracksDoNotExist() {
        YouTubeTranscriptService service = new YouTubeTranscriptService(uri -> "<html></html>", new ObjectMapper());

        assertThatThrownBy(() -> service.fetchTranscript("https://www.youtube.com/watch?v=dQw4w9WgXcQ"))
                .isInstanceOf(TranscriptUnavailableException.class)
                .hasMessage("이 영상의 자막을 가져올 수 없습니다.");
    }

    @Test
    void fetchesTranscriptFromCaptionTrack() {
        List<URI> requestedUris = new ArrayList<>();
        YouTubeTranscriptService service = new YouTubeTranscriptService(uri -> {
            requestedUris.add(uri);
            if (requestedUris.size() == 1) {
                return """
                        <script>
                        {"captionTracks":[{"baseUrl":"https://example.com/caption?lang=en","languageCode":"en"}],"audioTracks":[]}
                        </script>
                        """;
            }
            return """
                    {
                      "events": [
                        { "segs": [{ "utf8": "Hello " }, { "utf8": "world" }] },
                        { "segs": [{ "utf8": " from captions." }] }
                      ]
                    }
                    """;
        }, new ObjectMapper());

        String transcript = service.fetchTranscript("https://www.youtube.com/watch?v=dQw4w9WgXcQ");

        assertThat(transcript).isEqualTo("Hello world from captions.");
        assertThat(requestedUris.get(1).toString()).contains("fmt=json3");
    }

    @Test
    void triesNextCaptionTrackWhenPreferredTrackIsEmpty() {
        List<URI> requestedUris = new ArrayList<>();
        YouTubeTranscriptService service = new YouTubeTranscriptService(uri -> {
            requestedUris.add(uri);
            if (requestedUris.size() == 1) {
                return """
                        <script>
                        {"captionTracks":[
                          {"baseUrl":"https://example.com/caption?lang=en","languageCode":"en"},
                          {"baseUrl":"https://example.com/caption?lang=ja","languageCode":"ja"}
                        ],"audioTracks":[]}
                        </script>
                        """;
            }
            if (requestedUris.size() == 2) {
                return "";
            }
            return """
                    {
                      "events": [
                        { "segs": [{ "utf8": "Fallback caption" }] }
                      ]
                    }
                    """;
        }, new ObjectMapper());

        String transcript = service.fetchTranscript("https://www.youtube.com/watch?v=dQw4w9WgXcQ");

        assertThat(transcript).isEqualTo("Fallback caption");
        assertThat(requestedUris).hasSize(3);
        assertThat(requestedUris.get(1).toString()).contains("lang=en");
        assertThat(requestedUris.get(2).toString()).contains("lang=ja");
    }
}
