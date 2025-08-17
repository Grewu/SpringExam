package com.example.springexam.service.impl;

import com.example.springexam.model.dto.response.xml.Quiz;
import com.example.springexam.model.entity.Question;
import com.example.springexam.repository.QuestionRepository;
import com.example.springexam.service.api.QuizService;
import com.example.springexam.service.converter.api.MoodleQuestionConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizServiceImpl implements QuizService {

    private final QuestionRepository questionRepository;
    private final MoodleQuestionConverter questionConverter;

    @Override
    @Transactional(readOnly = true)
    public Quiz generateMoodleQuiz() {
        log.info("Starting Moodle quiz generation");
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            log.warn("No questions found in repository. Returning empty quiz.");
            return Quiz.builder().question(List.of()).build();
        }
        log.debug("Found {} questions in repository", questions.size());
        return Quiz.builder()
                .question(questions.stream()
                        .map(questionConverter::convert)
                        .toList()
                )
                .build();
    }

}