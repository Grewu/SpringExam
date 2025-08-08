package com.example.springexam.controller;

import com.example.springexam.model.dto.response.ParsedInfo;
import com.example.springexam.model.xml.Quiz;
import com.example.springexam.service.api.DataBaseService;
import com.example.springexam.service.api.QuestionService;
import com.example.springexam.service.parser.ExamParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Validated
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ExamController {
    private final ExamParserService examParserService;
    private final QuestionService questionService;
    private final DataBaseService dataBaseService;

    @PostMapping("/parse")
    public ResponseEntity<List<ParsedInfo>> parse() {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(examParserService.parse());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ParsedInfo>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(questionService.getAllParsedInfo());
    }

    @GetMapping(value = "/getAllXml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Quiz> getAllXml() {
        return ResponseEntity.ok()
                .body(questionService.getAllMoodleQuiz());
    }

    @PostMapping("/reset-db")
    public ResponseEntity<Void> resetDatabase() {
        dataBaseService.hardReset();
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
