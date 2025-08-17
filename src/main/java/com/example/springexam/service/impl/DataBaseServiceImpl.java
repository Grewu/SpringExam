package com.example.springexam.service.impl;

import com.example.springexam.repository.AnswerRepository;
import com.example.springexam.repository.ExplanationRepository;
import com.example.springexam.repository.QuestionRepository;
import com.example.springexam.repository.TopicRepository;
import com.example.springexam.service.api.DataBaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class DataBaseServiceImpl implements DataBaseService {

    private final AnswerRepository answerRepository;
    private final ExplanationRepository explanationRepository;
    private final QuestionRepository questionRepository;
    private final TopicRepository topicRepository;


    @Override
    @Transactional
    public void hardReset() {
        log.info("Starting hard reset of database");
        try {
            answerRepository.deleteAllInBatch();
            log.debug("Deleted all Answers");
            explanationRepository.deleteAllInBatch();
            log.debug("Deleted all Explanations");
            questionRepository.deleteAllInBatch();
            log.debug("Deleted all Questions");
            topicRepository.deleteAllInBatch();
            log.debug("Deleted all Topics");
            log.info("Hard reset completed successfully");
        } catch (DataAccessException e) {
            log.error("Failed to perform hard reset", e);
            throw e;
        }
    }
}