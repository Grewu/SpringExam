package com.example.springexam.model.dto.response;

import com.example.springexam.model.entity.enums.TopicType;

public record TopicResponse(
        Long id,
        TopicType name,
        String description
) {}
