package com.example.aitranscriptsummarizer.summary.service;

import com.example.aitranscriptsummarizer.summary.client.OpenAiSummaryClient;
import com.example.aitranscriptsummarizer.summary.dto.SummarizeRequest;
import com.example.aitranscriptsummarizer.summary.dto.SummarizeResponse;
import com.example.aitranscriptsummarizer.youtube.service.YouTubeTranscriptService;
import org.springframework.stereotype.Service;

@Service
public class SummaryService {

    private final YouTubeTranscriptService youTubeTranscriptService;
    private final OpenAiSummaryClient openAiSummaryClient;

    public SummaryService(YouTubeTranscriptService youTubeTranscriptService, OpenAiSummaryClient openAiSummaryClient) {
        this.youTubeTranscriptService = youTubeTranscriptService;
        this.openAiSummaryClient = openAiSummaryClient;
    }

    public SummarizeResponse summarize(SummarizeRequest request) {
        String transcript = youTubeTranscriptService.fetchTranscript(request.youtubeUrl());
        return openAiSummaryClient.summarize(transcript);
    }
}
