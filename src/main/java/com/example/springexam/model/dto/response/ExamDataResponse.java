package com.example.springexam.model.dto.response;


import com.example.springexam.model.entity.Answer;
import lombok.Builder;

import java.util.List;


@Builder
public record ExamDataResponse(
        String question,
        List<String> answer,
        String explanation,
        String topic
) {

}

