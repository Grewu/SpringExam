package com.example.springexam.service.impl;

import com.example.springexam.data.AnswerTestData;
import com.example.springexam.data.QuestionTestData;
import com.example.springexam.model.entity.Answer;
import com.example.springexam.repository.AnswerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerServiceImplTest {
    @Mock
    private AnswerRepository answerRepository;
    @InjectMocks
    private AnswerServiceImpl answerService;

    @Test
    void create() {
        var answer = AnswerTestData.builder().build().buildAnswer();
        assertThrows(UnsupportedOperationException.class, () -> answerService.create(answer));
    }

    @Test
    void createAllShouldReturnListOfAnswer() {
        //given
        var expected = List.of(AnswerTestData.builder().build().buildAnswer());
        when(answerRepository.saveAll(expected)).thenReturn(expected);
        //when
        var actual = answerService.createAll(expected);
        //then
        assertEquals(expected,actual);

    }

    @Test
    void createAllShouldReturnListWhenAnswerIsEmpty() {
        //given
        var expected = new ArrayList<Answer>();
        //when
        var actual = answerService.createAll(expected);
        //then
        assertEquals(expected,actual);

    }

    @Test
    void createAllShouldReturnEmptyListWhenAnswerIsNull() {
        //given
        var expected = List.of();
        //when
        var actual = answerService.createAll(null);
        //then
        assertEquals(expected,actual);

    }

    @Test
    void getAll() {
        assertThrows(UnsupportedOperationException.class, () -> answerService.getAll(Pageable.unpaged()));
    }

    @Test
    void getAnswerTextsByQuestionIdShouldReturnText() {
        //given
        var question = QuestionTestData.builder().build().buildQuestion();
        var answerList = List.of(AnswerTestData.builder().build().buildAnswer());
        var expected = List.of(AnswerTestData.builder().build().buildAnswer().getAnswerText());
        when(answerRepository.findByQuestionId(question.getId())).thenReturn(answerList);
        //when
        var actual = answerService.getAnswerTextsByQuestionId(question.getId());

        assertEquals(expected,actual);
    }

    @Test
    void getAnswerTextsByQuestionIdShouldReturnStringWhenAnswerTextEmpty() {
        //given
        var question = QuestionTestData.builder()
                .withQuestionText(" ")
                .build().buildQuestion();
        var answerList = List.of(AnswerTestData.builder().withAnswerText(" ").build().buildAnswer());
        var expected = List.of(AnswerTestData.builder().withAnswerText(" ").build().buildAnswer().getAnswerText());
        when(answerRepository.findByQuestionId(question.getId())).thenReturn(answerList);
        //when
        var actual = answerService.getAnswerTextsByQuestionId(question.getId());

        assertEquals(expected,actual);
    }


    @Test
    void getAnswerByQuestionIdShouldReturnAnswer() {
        //given
        var question = QuestionTestData.builder().build().buildQuestion();
        var answerList = new ArrayList<Answer>();
        var expected = new ArrayList<Answer>();
        when(answerRepository.findByQuestionId(question.getId())).thenReturn(answerList);
        //when
        var actual = answerService.getAnswerByQuestionId(question.getId());

        assertEquals(expected,actual);
    }

    @Test
    void getAnswerTextsByQuestionIdShouldThrowNullPointerException() {
        //when
        assertThrows(NullPointerException.class, () -> answerService.getAnswerTextsByQuestionId(null));
    }

    @Test
    void getAnswerByQuestionIdShouldThrowNullPointerException() {
        //when
        assertThrows(NullPointerException.class, () -> answerService.getAnswerByQuestionId(null));
    }

    @Test
    void getById() {
        var answer = AnswerTestData.builder().build().buildAnswer();
        assertThrows(UnsupportedOperationException.class, () -> answerService.getById(answer.getId()));
    }

    @Test
    void update() {
        var answer = AnswerTestData.builder().build().buildAnswer();
        assertThrows(UnsupportedOperationException.class, () -> answerService.update(answer.getId(),answer));
    }

    @Test
    void delete() {
        var answer = AnswerTestData.builder().build().buildAnswer();
        assertThrows(UnsupportedOperationException.class, () -> answerService.delete(answer.getId()));
    }
}