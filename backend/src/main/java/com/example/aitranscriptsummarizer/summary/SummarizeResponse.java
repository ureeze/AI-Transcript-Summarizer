package com.example.aitranscriptsummarizer.summary;

import java.util.List;

public record SummarizeResponse(
        List<String> summary,
        List<String> keyPoints,
        List<String> keywords
) {
}
