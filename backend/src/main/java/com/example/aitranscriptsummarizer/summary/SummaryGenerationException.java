package com.example.aitranscriptsummarizer.summary;

public class SummaryGenerationException extends RuntimeException {

    public SummaryGenerationException() {
        super("요약을 생성하지 못했습니다. 잠시 후 다시 시도해주세요.");
    }

    public SummaryGenerationException(Throwable cause) {
        super("요약을 생성하지 못했습니다. 잠시 후 다시 시도해주세요.", cause);
    }
}
