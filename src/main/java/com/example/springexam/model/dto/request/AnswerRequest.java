package com.example.springexam.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerRequest(
        @NotNull(message = "Question ID cannot be null") Long questionId,
        @NotBlank(message = "Answer text cannot be blank") String answerText,
        @NotNull(message = "Correct flag cannot be null") Boolean isCorrect
) {}


