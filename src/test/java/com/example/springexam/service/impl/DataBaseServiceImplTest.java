package com.example.springexam.service.impl;

import com.example.springexam.repository.AnswerRepository;
import com.example.springexam.repository.ExplanationRepository;
import com.example.springexam.repository.QuestionRepository;
import com.example.springexam.repository.TopicRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class DataBaseServiceImplTest {
    public static final String DB_ERROR = "DB error";
    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private  ExplanationRepository explanationRepository;
    @Mock
    private  QuestionRepository questionRepository;
    @Mock
    private  TopicRepository topicRepository;

    @InjectMocks
    private DataBaseServiceImpl dataBaseService;

    @Test
    void hardReset_deletesAllEntitiesInOrder() {
        dataBaseService.hardReset();

        InOrder inOrder = Mockito.inOrder(
                answerRepository,
                explanationRepository,
                questionRepository,
                topicRepository
        );
        inOrder.verify(answerRepository).deleteAllInBatch();
        inOrder.verify(explanationRepository).deleteAllInBatch();
        inOrder.verify(questionRepository).deleteAllInBatch();
        inOrder.verify(topicRepository).deleteAllInBatch();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void hardReset_throwsExceptionAndLogsIfDataAccessFails() {
        doThrow(new DataAccessException(DB_ERROR) {}).when(answerRepository).deleteAllInBatch();

        assertThrows(DataAccessException.class, () -> dataBaseService.hardReset());

        verify(answerRepository).deleteAllInBatch();
        verifyNoMoreInteractions(explanationRepository, questionRepository, topicRepository);
    }
}