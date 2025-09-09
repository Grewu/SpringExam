package com.example.springexam.controller;

import com.example.springexam.model.dto.response.xml.Quiz;
import com.example.springexam.service.api.QuizService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exports")
@RequiredArgsConstructor
public class MoodleExportController {

    private final QuizService quizService;

    @GetMapping(value = "/moodle-xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Quiz> exportToMoodleXml(
            @Parameter(schema = @Schema(type = "string", allowableValues = {"java", "spring", "k8s", "sql"}))
            @RequestParam(required = false) String keyword) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(quizService.generateMoodleQuiz(keyword));
    }

}