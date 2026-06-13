package com.example.aitranscriptsummarizer;

import com.example.aitranscriptsummarizer.summary.config.OpenAiProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableConfigurationProperties(OpenAiProperties.class)
@SpringBootApplication
public class AiTranscriptSummarizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiTranscriptSummarizerApplication.class, args);
    }
}
