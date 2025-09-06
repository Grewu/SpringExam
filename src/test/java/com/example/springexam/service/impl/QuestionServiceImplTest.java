package com.example.springexam.service.impl;

import com.example.springexam.data.AnswerTestData;
import com.example.springexam.data.ExplanationTestData;
import com.example.springexam.data.QuestionTestData;
import com.example.springexam.model.dto.response.html.HtmlParsedResponse;
import com.example.springexam.repository.QuestionRepository;
import com.example.springexam.service.api.AnswerService;
import com.example.springexam.service.api.ExplanationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private AnswerService answerService;
    @Mock
    private ExplanationService explanationService;
    @InjectMocks
    private QuestionServiceImpl questionService;

    @Test
    void createShouldReturnQuestion() {
        // given
        var expected = QuestionTestData.builder().build().buildQuestion();
        when(questionRepository.save(expected)).thenReturn(expected);
        // when
        var actual = questionService.create(expected);
        // then
        assertEquals(expected, actual);
    }

    @Test
    void createShouldThrowNullPointerExceptionWhenQuestionIsNull() {
        assertThrows(NullPointerException.class, () -> questionService.create(null));
        verify(questionRepository, never()).save(any());
    }

    @Test
    void createShouldThrowIllegalArgumentExceptionWhenQuestionTextIsNull() {
        // given
        var question = QuestionTestData.builder().build().buildQuestion();
        question.setQuestionText(null);
        // then
        assertThrows(IllegalArgumentException.class, () -> questionService.create(question));
        verify(questionRepository, never()).save(any());
    }

    @Test
    void createShouldThrowIllegalArgumentExceptionWhenQuestionTextIsEmpty() {
        // given
        var question = QuestionTestData.builder().withQuestionText("").build().buildQuestion();
        // then
        assertThrows(IllegalArgumentException.class, () -> questionService.create(question));
        verify(questionRepository, never()).save(any());
    }

    @Test
    void createShouldSaveQuestionWhenValid() {
        // given
        var question = QuestionTestData.builder().build().buildQuestion();
        var savedQuestion = QuestionTestData.builder().build().buildQuestion();

        when(questionRepository.save(question)).thenReturn(savedQuestion);

        // when
        var result = questionService.create(question);

        // then
        assertNotNull(result);
        assertEquals(savedQuestion.getId(), result.getId());
        verify(questionRepository, times(1)).save(question);
    }

    @Test
    void getAllShouldReturnPage() {
        // given
        var pageable = mock(Pageable.class);
        var question = QuestionTestData.builder().build().buildQuestion();
        var page = new PageImpl<>(Collections.singletonList(question));
        when(questionRepository.findAll(pageable)).thenReturn(page);
        // when
        var result = questionService.getAll(pageable);
        // then
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(question, result.getContent().getFirst());
    }


    @Test
    void getAllParsedInfoShouldReturnListWithParsedResponse() {
        // given
        var question = QuestionTestData.builder().build().buildQuestion();
        var answer = List.of(AnswerTestData.builder().build().buildAnswer().getAnswerText());
        var explanation = ExplanationTestData.builder().build().buildExplanation();
        when(questionRepository.findAll()).thenReturn(Collections.singletonList(question));
        when(answerService.getAnswerTextsByQuestionId(question.getId())).thenReturn(answer);
        when(explanationService.getExplanationContent(question.getId())).thenReturn(explanation.getContent());

        // when
        var result = questionService.getAllParsedInfo();
        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        HtmlParsedResponse response = result.getFirst();
        assertEquals(question.getQuestionText(), response.question());
    }

    @Test
    void getAllParsedInfoShouldHandleNullAnswersExplanationAndTopic() {
        // given
        var question = QuestionTestData.builder().build().buildQuestion();

        when(questionRepository.findAll()).thenReturn(Collections.singletonList(question));
        when(answerService.getAnswerTextsByQuestionId(question.getId())).thenReturn(null);
        when(explanationService.getExplanationContent(question.getId())).thenReturn(null);

        // when
        var result = questionService.getAllParsedInfo();
        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        HtmlParsedResponse response = result.getFirst();
        assertEquals(question.getQuestionText(), response.question());
        assertEquals(question.getTopic().getName().name(), response.topic());
    }

    @Test
    void getAllParsedInfoShouldHandleNul() {
        // given
        var question = QuestionTestData.builder()
                .withQuestionText(null)
                .build().buildQuestion();

        when(questionRepository.findAll()).thenReturn(Collections.singletonList(question));
        when(answerService.getAnswerTextsByQuestionId(question.getId())).thenReturn(null);
        when(explanationService.getExplanationContent(question.getId())).thenReturn(null);

        // when
        var result = questionService.getAllParsedInfo();
        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        HtmlParsedResponse response = result.getFirst();
        assertEquals(question.getTopic().getName().name(), response.topic());
    }

    @Test
    void getAllParsedInfoShouldHandleWhenTopicNull() {
        // given
        var question = QuestionTestData.builder().withTopic(null).build().buildQuestion();

        when(questionRepository.findAll()).thenReturn(Collections.singletonList(question));
        when(answerService.getAnswerTextsByQuestionId(question.getId())).thenReturn(null);
        when(explanationService.getExplanationContent(question.getId())).thenReturn(null);

        // when
        var result = questionService.getAllParsedInfo();
        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        HtmlParsedResponse response = result.getFirst();
        assertEquals(question.getQuestionText(), response.question());
    }


    @Test
    void getAllParsedInfoShouldReturnEmptyListWhenNoQuestions() {
        when(questionRepository.findAll()).thenReturn(Collections.emptyList());
        var result = questionService.getAllParsedInfo();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getAllShouldThrowNullPointerExceptionWhenPageableIsNull() {
        assertThrows(NullPointerException.class, () -> questionService.getAll(null));
    }

    @Test
    void getByIdShouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> questionService.getById(1L));
    }

    @Test
    void updateShouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> questionService.update(1L, QuestionTestData.builder().build().buildQuestion()));
    }

    @Test
    void deleteShouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> questionService.delete(1L));
    }

}