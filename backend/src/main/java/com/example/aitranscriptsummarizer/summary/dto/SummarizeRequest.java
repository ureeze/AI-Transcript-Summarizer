package com.example.aitranscriptsummarizer.summary.dto;

import com.example.aitranscriptsummarizer.summary.exception.InvalidSummarizeRequestException;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

public record SummarizeRequest(
        String youtubeUrl,
        String transcript
) {
    private static final Pattern YOUTUBE_URL_PATTERN = Pattern.compile(
            "^(https?://)?(www\\.)?(youtube\\.com/watch\\?v=|youtu\\.be/)[A-Za-z0-9_-]{11}.*$"
    );
    private static final int MIN_TRANSCRIPT_LENGTH = 50;

    public void validate() {
        if (hasTranscript()) {
            if (normalizedTranscript().length() < MIN_TRANSCRIPT_LENGTH) {
                throw new InvalidSummarizeRequestException("최소 50자 이상의 자막 내용을 입력해주세요.");
            }
            return;
        }

        if (!StringUtils.hasText(youtubeUrl) || !YOUTUBE_URL_PATTERN.matcher(normalizedYoutubeUrl()).matches()) {
            throw new InvalidSummarizeRequestException("유효한 유튜브 영상 URL을 입력해주세요.");
        }
    }

    public boolean hasTranscript() {
        return StringUtils.hasText(transcript);
    }

    public String normalizedYoutubeUrl() {
        return youtubeUrl == null ? "" : youtubeUrl.trim();
    }

    public String normalizedTranscript() {
        return transcript == null ? "" : transcript.trim();
    }
}
