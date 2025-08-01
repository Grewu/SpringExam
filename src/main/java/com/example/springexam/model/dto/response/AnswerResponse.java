package com.example.springexam.model.dto.response;

public record AnswerResponse(
        Long id,
        String answerText,
        Boolean isCorrect
) {}
