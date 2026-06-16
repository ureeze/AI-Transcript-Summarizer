package com.example.aitranscriptsummarizer.summary.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "openai")
public record OpenAiProperties(
        String apiKey,
        String model,
        String chatCompletionsUrl
) {
}
