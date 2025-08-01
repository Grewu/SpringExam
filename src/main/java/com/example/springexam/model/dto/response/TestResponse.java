package com.example.springexam.model.dto.response;

import java.util.List;

public record TestResponse(
        Long id,
        String title,
        String description,
        List<QuestionResponse> questions
) {}