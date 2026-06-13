package com.example.aitranscriptsummarizer.summary.exception;

public class TranscriptUnavailableException extends RuntimeException {

    public TranscriptUnavailableException() {
        super("이 영상의 자막을 가져올 수 없습니다.");
    }

    public TranscriptUnavailableException(Throwable cause) {
        super("이 영상의 자막을 가져올 수 없습니다.", cause);
    }
}
