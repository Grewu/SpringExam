package com.example.springexam.service.impl;

import com.example.springexam.repository.*;
import com.example.springexam.service.api.DataBaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Transactional
    @Override
    public void hardReset() {
        log.warn("Performing HARD database reset");
        answerRepository.deleteAllInBatch();
        explanationRepository.deleteAllInBatch();
        questionRepository.deleteAllInBatch();
        topicRepository.deleteAllInBatch();
        log.info("Database reset completed");
    }
}