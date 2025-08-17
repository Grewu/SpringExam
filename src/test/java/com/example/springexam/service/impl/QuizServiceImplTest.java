package com.example.springexam.service.impl;

import com.example.springexam.data.QuestionTestData;
import com.example.springexam.data.xml.MoodleQuestionTestData;
import com.example.springexam.model.dto.response.xml.MoodleQuestion;
import com.example.springexam.model.dto.response.xml.Quiz;
import com.example.springexam.model.entity.Question;
import com.example.springexam.repository.QuestionRepository;
import com.example.springexam.service.converter.api.MoodleQuestionConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizServiceImplTest {
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private MoodleQuestionConverter questionConverter;
    @InjectMocks
    private QuizServiceImpl quizService;

    @Test
    void generateMoodleQuizShouldReturnQuizWithQuestions() {
        // given
        Question question = QuestionTestData.builder().build().buildQuestion();
        MoodleQuestion moodleQuestion = MoodleQuestionTestData.builder().build().buildMoodleQuestion();

        when(questionRepository.findAll()).thenReturn(List.of(question));
        when(questionConverter.convert(question)).thenReturn(moodleQuestion);

        // when
        Quiz result = quizService.generateMoodleQuiz();

        // then
        assertNotNull(result);
        assertEquals(1, result.getQuestion().size());
        assertEquals(moodleQuestion, result.getQuestion().getFirst());
        verify(questionRepository).findAll();
        verify(questionConverter).convert(question);
    }

    @Test
    void generateMoodleQuizShouldReturnEmptyQuizWhenNoQuestions() {
        // given
        when(questionRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        Quiz result = quizService.generateMoodleQuiz();

        // then
        assertNotNull(result);
        assertNotNull(result.getQuestion());
        assertTrue(result.getQuestion().isEmpty());
        verify(questionRepository).findAll();
        verify(questionConverter, never()).convert(any());
    }
}