package com.example.springexam.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record SearchDto(
        @NotBlank(message = "URL cannot be empty or null")
        @URL(message = "Invalid URL format")
        String url
) {}