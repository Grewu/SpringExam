package com.example.springexam.service.api;

import com.example.springexam.model.dto.response.xml.Quiz;

public interface QuizService {
    Quiz generateMoodleQuiz();

    Quiz generateMoodleQuiz(String title);
}
