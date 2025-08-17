package com.example.springexam.data;

import com.example.springexam.model.entity.Explanation;
import com.example.springexam.model.entity.Question;
import lombok.Builder;

@Builder(setterPrefix = "with")
public class ExplanationTestData {
    @Builder.Default private Long id = 1L;
    @Builder.Default private Question question = QuestionTestData.builder().build().buildQuestion();
    @Builder.Default private String content = "content";

    public Explanation buildExplanation() {
        return Explanation.builder()
                .id(id)
                .question(question)
                .content(content)
                .build();
    }
}