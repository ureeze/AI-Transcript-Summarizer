package com.example.aitranscriptsummarizer.summary.exception;

import com.example.aitranscriptsummarizer.summary.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SummaryExceptionHandler {

    @ExceptionHandler(TranscriptUnavailableException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ApiErrorResponse handleTranscriptUnavailable(TranscriptUnavailableException exception) {
        return new ApiErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(SummaryGenerationException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ApiErrorResponse handleSummaryGeneration(SummaryGenerationException exception) {
        return new ApiErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(InvalidSummarizeRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleInvalidRequest(InvalidSummarizeRequestException exception) {
        return new ApiErrorResponse(exception.getMessage());
    }
}
