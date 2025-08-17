package com.example.springexam.controller;

import com.example.springexam.model.dto.response.html.HtmlParsedResponse;
import com.example.springexam.service.parser.ExamParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/import")
@RequiredArgsConstructor
public class ImportController {
    private final ExamParserService examParserService;

    @PostMapping
    public ResponseEntity<List<HtmlParsedResponse>> parseAndImport() {
        return ResponseEntity.status(HttpStatus.CREATED).body(examParserService.parse());
    }
}