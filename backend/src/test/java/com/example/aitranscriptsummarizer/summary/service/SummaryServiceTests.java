package com.example.aitranscriptsummarizer.summary.service;

import com.example.aitranscriptsummarizer.summary.client.OpenAiSummaryClient;
import com.example.aitranscriptsummarizer.summary.dto.SummarizeRequest;
import com.example.aitranscriptsummarizer.summary.dto.SummarizeResponse;
import com.example.aitranscriptsummarizer.youtube.service.YouTubeTranscriptService;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class SummaryServiceTests {

    @Test
    void summarizesDirectTranscriptWithoutFetchingYoutubeTranscript() {
        YouTubeTranscriptService youTubeTranscriptService = mock(YouTubeTranscriptService.class);
        OpenAiSummaryClient openAiSummaryClient = mock(OpenAiSummaryClient.class);
        SummaryService summaryService = new SummaryService(youTubeTranscriptService, openAiSummaryClient);
        SummarizeResponse expected = new SummarizeResponse(
                List.of("요약 1", "요약 2", "요약 3"),
                List.of("핵심 1"),
                List.of("키워드")
        );

        when(openAiSummaryClient.summarize("직접 입력 자막입니다. 충분히 긴 테스트용 자막 내용입니다."))
                .thenReturn(expected);

        SummarizeResponse response = summaryService.summarize(new SummarizeRequest(
                null,
                "직접 입력 자막입니다. 충분히 긴 테스트용 자막 내용입니다."
        ));

        assertThat(response).isEqualTo(expected);
        verifyNoInteractions(youTubeTranscriptService);
        verify(openAiSummaryClient).summarize("직접 입력 자막입니다. 충분히 긴 테스트용 자막 내용입니다.");
    }

    @Test
    void fetchesYoutubeTranscriptWhenDirectTranscriptDoesNotExist() {
        YouTubeTranscriptService youTubeTranscriptService = mock(YouTubeTranscriptService.class);
        OpenAiSummaryClient openAiSummaryClient = mock(OpenAiSummaryClient.class);
        SummaryService summaryService = new SummaryService(youTubeTranscriptService, openAiSummaryClient);
        SummarizeResponse expected = new SummarizeResponse(
                List.of("요약 1", "요약 2", "요약 3"),
                List.of("핵심 1"),
                List.of("키워드")
        );

        when(youTubeTranscriptService.fetchTranscript("https://www.youtube.com/watch?v=dQw4w9WgXcQ"))
                .thenReturn("유튜브 자막");
        when(openAiSummaryClient.summarize("유튜브 자막")).thenReturn(expected);

        SummarizeResponse response = summaryService.summarize(new SummarizeRequest(
                "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
                null
        ));

        assertThat(response).isEqualTo(expected);
        verify(youTubeTranscriptService).fetchTranscript("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        verify(openAiSummaryClient).summarize("유튜브 자막");
    }
}
