package com.example.aitranscriptsummarizer.summary.controller;

import com.example.aitranscriptsummarizer.summary.dto.SummarizeRequest;
import com.example.aitranscriptsummarizer.summary.dto.SummarizeResponse;
import com.example.aitranscriptsummarizer.summary.service.SummaryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "${app.cors.allowed-origin:http://localhost:5173}")
@RequestMapping("/api")
public class SummaryController {

    private final SummaryService summaryService;

    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @PostMapping("/summarize")
    public SummarizeResponse summarize(@Valid @RequestBody SummarizeRequest request) {
        return summaryService.summarize(request);
    }
}
