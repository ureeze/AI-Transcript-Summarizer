package com.example.aitranscriptsummarizer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.aitranscriptsummarizer.summary.TranscriptUnavailableException;
import com.example.aitranscriptsummarizer.summary.YouTubeTranscriptService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class AiTranscriptSummarizerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private YouTubeTranscriptService youTubeTranscriptService;

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
        when(youTubeTranscriptService.fetchTranscript(anyString()))
                .thenReturn("테스트 자막입니다.");

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

    @Test
    void summarizeReturnsErrorWhenTranscriptIsUnavailable() throws Exception {
        when(youTubeTranscriptService.fetchTranscript(anyString()))
                .thenThrow(new TranscriptUnavailableException());

        mockMvc.perform(post("/api/summarize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "youtubeUrl": "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
                                }
                                """))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").value("이 영상의 자막을 가져올 수 없습니다."));
    }
}
