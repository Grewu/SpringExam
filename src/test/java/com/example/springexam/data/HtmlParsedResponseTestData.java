package com.example.springexam.data;

import com.example.springexam.model.dto.response.html.HtmlParsedResponse;
import lombok.Builder;

import java.util.List;

@Builder(setterPrefix = "with")
public class HtmlParsedResponseTestData {
    @Builder.Default private String question = "question";
    @Builder.Default private List<String> answer = List.of("answer1", "answer2");
    @Builder.Default private String explanation = "explanation";
    @Builder.Default private String topic = "topic";

    public HtmlParsedResponse buildHtmlParsedResponse() {
        return new HtmlParsedResponse(question, answer, explanation, topic);
    }
}