package com.example.aitranscriptsummarizer.summary;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SummaryService {

    private final YouTubeTranscriptService youTubeTranscriptService;

    public SummaryService(YouTubeTranscriptService youTubeTranscriptService) {
        this.youTubeTranscriptService = youTubeTranscriptService;
    }

    public SummarizeResponse summarize(SummarizeRequest request) {
        String transcript = youTubeTranscriptService.fetchTranscript(request.youtubeUrl());
        return new SummarizeResponse(
                List.of(
                        "입력된 유튜브 영상에서 공개 자막을 추출했습니다.",
                        "현재는 OpenAI API 연동 전 더미 응답으로 전체 화면 흐름을 검증합니다.",
                        "다음 단계에서 추출된 자막을 실제 요약 프롬프트에 연결합니다."
                ),
                List.of(
                        "유튜브 URL 입력 검증",
                        "영상 ID 추출",
                        "공개 자막 추출",
                        "OpenAI API 연동 준비",
                        "추출 자막 길이: " + transcript.length() + "자"
                ),
                List.of("YouTube", "Transcript", "Summary", "OpenAI", "Spring")
        );
    }
}
