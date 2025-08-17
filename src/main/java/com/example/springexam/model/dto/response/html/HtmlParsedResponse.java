package com.example.springexam.model.dto.response.html;


import lombok.Builder;

import java.util.List;


@Builder
public record HtmlParsedResponse(
        String question,
        List<String> answer,
        String explanation,
        String topic
) {

}

