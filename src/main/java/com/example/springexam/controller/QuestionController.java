package com.example.springexam.controller;

import com.example.springexam.model.dto.response.html.HtmlParsedResponse;
import com.example.springexam.service.api.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<HtmlParsedResponse>> getAllQuestions() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(questionService.getAllParsedInfo());
    }

}