package com.example.springexam.model.dto.response;

import com.example.springexam.model.entity.Topic;
import com.example.springexam.model.entity.enums.QuestionType;

import java.util.List;

public record QuestionResponse(
        Long id,
        Topic topic,
        QuestionType type,
        String questionText,
        Integer difficulty,
        List<AnswerResponse> answers,
        ExplanationResponse explanation
) {}
