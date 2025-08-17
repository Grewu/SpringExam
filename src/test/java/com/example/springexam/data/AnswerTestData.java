package com.example.springexam.data;

import com.example.springexam.model.entity.Answer;
import com.example.springexam.model.entity.Question;
import lombok.Builder;

@Builder(setterPrefix = "with")
public class AnswerTestData {
    @Builder.Default private Long id = 1L;
    @Builder.Default private Question question = QuestionTestData.builder().build().buildQuestion();
    @Builder.Default private String answerText = "answerText";
    @Builder.Default private Boolean isCorrect = false;

    public Answer buildAnswer() {
        return Answer.builder()
                .id(id)
                .question(question)
                .answerText(answerText)
                .isCorrect(isCorrect)
                .build();
    }

}