package com.example.springexam.model.dto.request;

import com.example.springexam.model.entity.enums.TopicType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TopicRequest(
        @NotNull(message = "Topic type cannot be null") TopicType name,
        String description
) {}

