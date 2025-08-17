package com.example.springexam.data.xml;

import com.example.springexam.model.dto.response.xml.MoodelAnswer;
import lombok.Builder;

@Builder(setterPrefix = "with")
public class MoodelAnswerTestData {
    @Builder.Default private double fraction = 0.0;
    @Builder.Default private String answerText = "answerText";

    public MoodelAnswer buildMoodelAnswer() {
        return MoodelAnswer.builder()
                .fraction(fraction)
                .text(answerText)
                .build();
    }


}
