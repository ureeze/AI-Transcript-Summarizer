package com.example.aitranscriptsummarizer.summary.client;

import com.example.aitranscriptsummarizer.summary.config.OpenAiProperties;
import com.example.aitranscriptsummarizer.summary.dto.SummarizeResponse;
import com.example.aitranscriptsummarizer.summary.exception.SummaryGenerationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class OpenAiSummaryClient {

    private static final String SYSTEM_PROMPT = """
            You are an expert content analyst.

            Analyze the transcript extracted from a YouTube video and return JSON only.

            Required:
            1. summary (3 sentences)
            2. keyPoints (maximum 5)
            3. keywords (maximum 10)

            Response Format:
            {
              "summary": [],
              "keyPoints": [],
              "keywords": []
            }
            """;

    private final OpenAiProperties properties;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public OpenAiSummaryClient(OpenAiProperties properties, ObjectMapper objectMapper) {
        this(
                properties,
                HttpClient.newBuilder()
                        .connectTimeout(Duration.ofSeconds(10))
                        .build(),
                objectMapper
        );
    }

    OpenAiSummaryClient(OpenAiProperties properties, HttpClient httpClient, ObjectMapper objectMapper) {
        this.properties = properties;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public SummarizeResponse summarize(String transcript) {
        if (!StringUtils.hasText(properties.apiKey())) {
            throw new SummaryGenerationException();
        }

        try {
            String requestBody = objectMapper.writeValueAsString(Map.of(
                    "model", properties.model(),
                    "messages", List.of(
                            Map.of("role", "system", "content", SYSTEM_PROMPT),
                            Map.of("role", "user", "content", "Transcript:\n" + transcript)
                    ),
                    "response_format", Map.of("type", "json_object")
            ));
            HttpRequest request = HttpRequest.newBuilder(URI.create(properties.chatCompletionsUrl()))
                    .timeout(Duration.ofSeconds(20))
                    .header("Authorization", "Bearer " + properties.apiKey())
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new SummaryGenerationException();
            }
            return parseResponse(response.body());
        } catch (SummaryGenerationException exception) {
            throw exception;
        } catch (IOException | InterruptedException exception) {
            if (exception instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            throw new SummaryGenerationException(exception);
        }
    }

    private SummarizeResponse parseResponse(String responseBody) throws IOException {
        JsonNode root = objectMapper.readTree(responseBody);
        JsonNode content = root.path("choices").path(0).path("message").path("content");
        if (!content.isTextual()) {
            throw new SummaryGenerationException();
        }

        JsonNode summaryJson = objectMapper.readTree(content.asText());
        List<String> summary = parseStringList(summaryJson, "summary");
        List<String> keyPoints = parseStringList(summaryJson, "keyPoints");
        List<String> keywords = parseStringList(summaryJson, "keywords");
        if (summary.isEmpty() || keyPoints.isEmpty() || keywords.isEmpty()) {
            throw new SummaryGenerationException();
        }
        return new SummarizeResponse(summary, keyPoints, keywords);
    }

    private List<String> parseStringList(JsonNode root, String fieldName) throws IOException {
        JsonNode value = root.get(fieldName);
        if (value == null || !value.isArray()) {
            throw new SummaryGenerationException();
        }
        return objectMapper.readerForListOf(String.class).readValue(value);
    }
}
