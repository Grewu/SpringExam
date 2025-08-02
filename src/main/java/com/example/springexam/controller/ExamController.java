package com.example.springexam.controller;

import com.example.springexam.model.ParsedInfo;
import com.example.springexam.model.dto.request.SearchDto;
import com.example.springexam.service.parser.ExamParserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Validated
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ExamController {
    private final ExamParserService examParserService;
    @PostMapping
    public ResponseEntity<List<ParsedInfo>> parse(@Valid @RequestBody SearchDto search) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(examParserService.parse(search.url()));
    }
}
