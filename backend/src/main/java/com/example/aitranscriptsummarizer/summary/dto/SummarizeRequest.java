package com.example.aitranscriptsummarizer.summary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SummarizeRequest(
        @NotBlank(message = "유효한 유튜브 영상 URL을 입력해주세요.")
        @Pattern(
                regexp = "^(https?://)?(www\\.)?(youtube\\.com/watch\\?v=|youtu\\.be/)[A-Za-z0-9_-]{11}.*$",
                message = "유효한 유튜브 영상 URL을 입력해주세요."
        )
        String youtubeUrl
) {
}
