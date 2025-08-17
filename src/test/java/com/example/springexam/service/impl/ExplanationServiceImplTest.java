package com.example.springexam.service.impl;

import com.example.springexam.data.ExplanationTestData;
import com.example.springexam.data.QuestionTestData;
import com.example.springexam.exception.EntityNotFoundException;
import com.example.springexam.repository.ExplanationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExplanationServiceImplTest {
    @Mock
    private ExplanationRepository explanationRepository;
    @InjectMocks
    private ExplanationServiceImpl explanationService;

    @Test
    void createShouldReturnExplanation() {
        //given
        var expected = ExplanationTestData.builder().build().buildExplanation();
        when(explanationRepository.save(expected)).thenReturn(expected);
        //when
        var actual = explanationService.create(expected);
        //then
        assertEquals(expected,actual);
    }


    @Test
    void getExplanationContentShouldReturnString() {
        //given
        var question = QuestionTestData.builder().build().buildQuestion();
        var expected = ExplanationTestData.builder().build().buildExplanation().getContent();
        var explanation = ExplanationTestData.builder().build().buildExplanation();
        when(explanationRepository.findByQuestionId(question.getId())).thenReturn(explanation);
        //when
        var actual = explanationService.getExplanationContent(question.getId());

        assertEquals(expected,actual);
    }

    @Test
    void getExplanationContentShouldThrowEntityNotFoundException() {
        //given
        var question = QuestionTestData.builder().build().buildQuestion();
        when(explanationRepository.findByQuestionId(question.getId())).thenReturn(null);
        //when
        assertThrows(EntityNotFoundException.class, () -> explanationService.getExplanationContent(question.getId()));
    }

    @Test
    void getExplanationContentShouldThrowExceptionWhenQuestionIdIsNull() {
        assertThrows(NullPointerException.class, () -> explanationService.getExplanationContent(null));
    }

    @Test
    void getAllShouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> explanationService.getAll(null));
    }

    @Test
    void getByIdShouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> explanationService.getById(1L));
    }

    @Test
    void updateShouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> explanationService.update(1L, ExplanationTestData.builder().build().buildExplanation()));
    }

    @Test
    void deleteShouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> explanationService.delete(1L));
    }
}