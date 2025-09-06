package com.example.springexam.service.impl;

import com.example.springexam.data.QuestionTestData;
import com.example.springexam.data.xml.MoodleQuestionTestData;
import com.example.springexam.model.dto.response.xml.MoodleQuestion;
import com.example.springexam.model.dto.response.xml.Quiz;
import com.example.springexam.model.entity.Question;
import com.example.springexam.model.entity.enums.TopicType;
import com.example.springexam.repository.QuestionRepository;
import com.example.springexam.service.converter.api.MoodleQuestionConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Set;

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
    @Test
    void generateMoodleQuizWithKeywordShouldReturnQuizWithFilteredQuestions() {
        // given
        String keyword = "java";
        Question question = QuestionTestData.builder().build().buildQuestion();
        MoodleQuestion moodleQuestion = MoodleQuestionTestData.builder().build().buildMoodleQuestion();

        // Получаем реальные топики по ключевому слову
        Set<TopicType> relatedTopics = TopicType.findByKeyword(keyword);

        when(questionRepository.findByTopicTypes(relatedTopics)).thenReturn(List.of(question));
        when(questionConverter.convert(question)).thenReturn(moodleQuestion);

        // when
        Quiz result = quizService.generateMoodleQuiz(keyword);

        // then
        assertNotNull(result);
        assertEquals(1, result.getQuestion().size());
        assertEquals(moodleQuestion, result.getQuestion().getFirst());
        verify(questionRepository).findByTopicTypes(relatedTopics);
        verify(questionConverter).convert(question);
    }

    @Test
    void generateMoodleQuizWithSpringKeywordShouldReturnQuizWithFilteredQuestions() {
        // given
        String keyword = "spring";
        Question question = QuestionTestData.builder().build().buildQuestion();
        MoodleQuestion moodleQuestion = MoodleQuestionTestData.builder().build().buildMoodleQuestion();

        // Получаем реальные топики по ключевому слову
        Set<TopicType> relatedTopics = TopicType.findByKeyword(keyword);

        when(questionRepository.findByTopicTypes(relatedTopics)).thenReturn(List.of(question));
        when(questionConverter.convert(question)).thenReturn(moodleQuestion);

        // when
        Quiz result = quizService.generateMoodleQuiz(keyword);

        // then
        assertNotNull(result);
        assertEquals(1, result.getQuestion().size());
        assertEquals(moodleQuestion, result.getQuestion().getFirst());
        verify(questionRepository).findByTopicTypes(relatedTopics);
        verify(questionConverter).convert(question);
    }

    @Test
    void generateMoodleQuizWithKubernetesKeywordShouldReturnQuizWithFilteredQuestions() {
        // given
        String keyword = "k8s";
        Question question = QuestionTestData.builder().build().buildQuestion();
        MoodleQuestion moodleQuestion = MoodleQuestionTestData.builder().build().buildMoodleQuestion();

        // Получаем реальные топики по ключевому слову
        Set<TopicType> relatedTopics = TopicType.findByKeyword(keyword);

        when(questionRepository.findByTopicTypes(relatedTopics)).thenReturn(List.of(question));
        when(questionConverter.convert(question)).thenReturn(moodleQuestion);

        // when
        Quiz result = quizService.generateMoodleQuiz(keyword);

        // then
        assertNotNull(result);
        assertEquals(1, result.getQuestion().size());
        assertEquals(moodleQuestion, result.getQuestion().getFirst());
        verify(questionRepository).findByTopicTypes(relatedTopics);
        verify(questionConverter).convert(question);
    }

    @Test
    void generateMoodleQuizWithBlankKeywordShouldFallbackToAllQuestions() {
        // given
        String blankKeyword = "   ";
        Question question = QuestionTestData.builder().build().buildQuestion();
        MoodleQuestion moodleQuestion = MoodleQuestionTestData.builder().build().buildMoodleQuestion();

        when(questionRepository.findAll()).thenReturn(List.of(question));
        when(questionConverter.convert(question)).thenReturn(moodleQuestion);

        // when
        Quiz result = quizService.generateMoodleQuiz(blankKeyword);

        // then
        assertNotNull(result);
        assertEquals(1, result.getQuestion().size());
        assertEquals(moodleQuestion, result.getQuestion().getFirst());
        verify(questionRepository).findAll();
        verify(questionConverter).convert(question);
        verify(questionRepository, never()).findByTopicTypes(any());
    }

    @Test
    void generateMoodleQuizWithEmptyKeywordShouldFallbackToAllQuestions() {
        // given
        String emptyKeyword = "";
        Question question = QuestionTestData.builder().build().buildQuestion();
        MoodleQuestion moodleQuestion = MoodleQuestionTestData.builder().build().buildMoodleQuestion();

        when(questionRepository.findAll()).thenReturn(List.of(question));
        when(questionConverter.convert(question)).thenReturn(moodleQuestion);

        // when
        Quiz result = quizService.generateMoodleQuiz(emptyKeyword);

        // then
        assertNotNull(result);
        assertEquals(1, result.getQuestion().size());
        assertEquals(moodleQuestion, result.getQuestion().getFirst());
        verify(questionRepository).findAll();
        verify(questionConverter).convert(question);
        verify(questionRepository, never()).findByTopicTypes(any());
    }

    @Test
    void generateMoodleQuizWithNullKeywordShouldFallbackToAllQuestions() {
        // given
        Question question = QuestionTestData.builder().build().buildQuestion();
        MoodleQuestion moodleQuestion = MoodleQuestionTestData.builder().build().buildMoodleQuestion();

        when(questionRepository.findAll()).thenReturn(List.of(question));
        when(questionConverter.convert(question)).thenReturn(moodleQuestion);

        // when
        Quiz result = quizService.generateMoodleQuiz(null);

        // then
        assertNotNull(result);
        assertEquals(1, result.getQuestion().size());
        assertEquals(moodleQuestion, result.getQuestion().getFirst());
        verify(questionRepository).findAll();
        verify(questionConverter).convert(question);
        verify(questionRepository, never()).findByTopicTypes(any());
    }

    @Test
    void generateMoodleQuizWithKeywordShouldReturnEmptyQuizWhenNoRelatedTopics() {
        // given
        String keyword = "nonexistentkeyword123";

        // when
        Quiz result = quizService.generateMoodleQuiz(keyword);

        // then
        assertNotNull(result);
        assertNotNull(result.getQuestion());
        assertTrue(result.getQuestion().isEmpty());
        verify(questionRepository, never()).findAll();
        verify(questionRepository, never()).findByTopicTypes(any());
        verify(questionConverter, never()).convert(any());
    }

    @Test
    void generateMoodleQuizWithKeywordShouldReturnEmptyQuizWhenNoQuestionsFound() {
        // given
        String keyword = "java";
        Set<TopicType> relatedTopics = TopicType.findByKeyword(keyword);

        when(questionRepository.findByTopicTypes(relatedTopics)).thenReturn(Collections.emptyList());

        // when
        Quiz result = quizService.generateMoodleQuiz(keyword);

        // then
        assertNotNull(result);
        assertNotNull(result.getQuestion());
        assertTrue(result.getQuestion().isEmpty());
        verify(questionRepository).findByTopicTypes(relatedTopics);
        verify(questionConverter, never()).convert(any());
    }

    @Test
    void generateMoodleQuizWithSpecificTopicKeywordShouldReturnFilteredQuestions() {
        // given
        String keyword = "java";
        Question question = QuestionTestData.builder().build().buildQuestion();
        MoodleQuestion moodleQuestion = MoodleQuestionTestData.builder().build().buildMoodleQuestion();
        Set<TopicType> relatedTopics = TopicType.findByKeyword(keyword);

        when(questionRepository.findByTopicTypes(relatedTopics)).thenReturn(List.of(question));
        when(questionConverter.convert(question)).thenReturn(moodleQuestion);

        // when
        Quiz result = quizService.generateMoodleQuiz(keyword);

        // then
        assertNotNull(result);
        assertEquals(1, result.getQuestion().size());
        assertEquals(moodleQuestion, result.getQuestion().getFirst());
        verify(questionRepository).findByTopicTypes(relatedTopics);
        verify(questionConverter).convert(question);
    }
}