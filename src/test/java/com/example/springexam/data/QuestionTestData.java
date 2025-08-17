package com.example.springexam.data;

import com.example.springexam.model.entity.Question;

import com.example.springexam.model.entity.Topic;
import lombok.Builder;

@Builder(setterPrefix = "with")
public class QuestionTestData {
    @Builder.Default private Long id = 1L;
    @Builder.Default private Topic topic = TopicTestData.builder().build().buildTopic();
    @Builder.Default private String questionText = "questionText";

    public Question buildQuestion() {
        return Question.builder()
                .id(id)
                .topic(topic)
                .questionText(questionText)
                .build();
    }

    public Question buildQuestionWithoutTopic() {
        return Question.builder()
                .id(id)
                .questionText(questionText)
                .build();
    }
}