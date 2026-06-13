package com.example.aitranscriptsummarizer.summary.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "openai.api-key=test-key")
class OpenAiSummaryClientBeanTests {

    @Autowired
    private OpenAiSummaryClient openAiSummaryClient;

    @Test
    void createsOpenAiSummaryClientBean() {
        assertThat(openAiSummaryClient).isNotNull();
    }
}
