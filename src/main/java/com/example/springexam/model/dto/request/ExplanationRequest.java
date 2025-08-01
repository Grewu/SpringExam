package com.example.springexam.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExplanationRequest(
        @NotNull(message = "Question ID cannot be null") Long questionId,
        @NotBlank(message = "Content cannot be blank") String content
) {}

