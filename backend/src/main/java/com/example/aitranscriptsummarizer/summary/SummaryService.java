package com.example.aitranscriptsummarizer.summary;

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
