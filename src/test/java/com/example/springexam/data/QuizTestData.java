package com.example.springexam.data;



import com.example.springexam.model.dto.response.xml.Quiz;
import lombok.Builder;

import java.util.List;

@Builder(setterPrefix = "with")
public class QuizTestData {
    @Builder.Default
    private List<String> questions = List.of("question 1", "question 2");

    public Quiz buildQuiz() {
        return new Quiz();
    }
}
