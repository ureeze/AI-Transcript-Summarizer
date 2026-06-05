package com.example.aitranscriptsummarizer.summary;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SummaryService {

    public SummarizeResponse summarize(SummarizeRequest request) {
        return new SummarizeResponse(
                List.of(
                        "입력된 유튜브 영상 URL을 기반으로 자막을 추출할 예정입니다.",
                        "현재는 OpenAI API 연동 전 더미 응답으로 전체 화면 흐름을 검증합니다.",
                        "다음 단계에서 자막 추출과 실제 요약 프롬프트를 연결합니다."
                ),
                List.of(
                        "유튜브 URL 입력 검증",
                        "영상 ID 추출 준비",
                        "자막 추출 로직 추가 예정",
                        "OpenAI API 연동 준비",
                        "프론트엔드와 백엔드 연결"
                ),
                List.of("YouTube", "Transcript", "Summary", "OpenAI", "Spring")
        );
    }
}
