package com.example.aitranscriptsummarizer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class AiTranscriptSummarizerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void summarizeRejectsInvalidYoutubeUrl() throws Exception {
        mockMvc.perform(post("/api/summarize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "youtubeUrl": "https://example.com/video"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void summarizeAcceptsYoutubeUrl() throws Exception {
        mockMvc.perform(post("/api/summarize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "youtubeUrl": "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summary").isArray())
                .andExpect(jsonPath("$.keyPoints").isArray())
                .andExpect(jsonPath("$.keywords").isArray());
    }
}
