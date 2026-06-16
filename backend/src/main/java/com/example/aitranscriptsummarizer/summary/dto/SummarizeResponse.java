package com.example.aitranscriptsummarizer.summary.dto;

import java.util.List;

public record SummarizeResponse(
        List<String> summary,
        List<String> keyPoints,
        List<String> keywords
) {
}
