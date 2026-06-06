package com.example.aitranscriptsummarizer.summary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OpenAiSummaryClientTests {

    @Test
    void returnsSummaryResponseFromOpenAiJsonContent() throws Exception {
        try (TestOpenAiServer server = TestOpenAiServer.start(200, """
                {
                  "choices": [
                    {
                      "message": {
                        "content": "{\\"summary\\":[\\"요약1\\",\\"요약2\\",\\"요약3\\"],\\"keyPoints\\":[\\"핵심1\\"],\\"keywords\\":[\\"AI\\"]}"
                      }
                    }
                  ]
                }
                """)) {
            OpenAiSummaryClient client = new OpenAiSummaryClient(
                    new OpenAiProperties("test-key", "gpt-4o-mini", server.url()),
                    HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(2)).build(),
                    new ObjectMapper()
            );

            SummarizeResponse response = client.summarize("테스트 자막");

            assertThat(response.summary()).containsExactly("요약1", "요약2", "요약3");
            assertThat(response.keyPoints()).containsExactly("핵심1");
            assertThat(response.keywords()).containsExactly("AI");
        }
    }

    @Test
    void failsWhenApiKeyIsMissing() {
        OpenAiSummaryClient client = new OpenAiSummaryClient(
                new OpenAiProperties("", "gpt-4o-mini", "http://localhost"),
                HttpClient.newHttpClient(),
                new ObjectMapper()
        );

        assertThatThrownBy(() -> client.summarize("테스트 자막"))
                .isInstanceOf(SummaryGenerationException.class)
                .hasMessage("요약을 생성하지 못했습니다. 잠시 후 다시 시도해주세요.");
    }

    @Test
    void failsWhenOpenAiReturnsErrorStatus() throws Exception {
        try (TestOpenAiServer server = TestOpenAiServer.start(500, "{}")) {
            OpenAiSummaryClient client = new OpenAiSummaryClient(
                    new OpenAiProperties("test-key", "gpt-4o-mini", server.url()),
                    HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(2)).build(),
                    new ObjectMapper()
            );

            assertThatThrownBy(() -> client.summarize("테스트 자막"))
                    .isInstanceOf(SummaryGenerationException.class)
                    .hasMessage("요약을 생성하지 못했습니다. 잠시 후 다시 시도해주세요.");
        }
    }

    private static class TestOpenAiServer implements AutoCloseable {

        private final HttpServer server;

        private TestOpenAiServer(HttpServer server) {
            this.server = server;
        }

        static TestOpenAiServer start(int statusCode, String responseBody) throws Exception {
            HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);
            server.createContext("/v1/chat/completions", exchange -> {
                byte[] body = responseBody.getBytes(StandardCharsets.UTF_8);
                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.sendResponseHeaders(statusCode, body.length);
                exchange.getResponseBody().write(body);
                exchange.close();
            });
            server.start();
            return new TestOpenAiServer(server);
        }

        String url() {
            return "http://localhost:" + server.getAddress().getPort() + "/v1/chat/completions";
        }

        @Override
        public void close() {
            server.stop(0);
        }
    }
}
