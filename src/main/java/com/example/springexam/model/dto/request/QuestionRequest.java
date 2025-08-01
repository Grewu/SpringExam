package com.example.springexam.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record QuestionRequest(
        @NotNull(message = "Topic ID cannot be null") Long topicId,
        @NotNull(message = "Type ID cannot be null") Long typeId,
        @NotBlank(message = "Question text cannot be blank") String questionText,
        Integer difficulty
) {}


