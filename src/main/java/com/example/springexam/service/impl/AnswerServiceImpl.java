package com.example.springexam.service.impl;

import com.example.springexam.model.entity.Answer;
import com.example.springexam.repository.AnswerRepository;
import com.example.springexam.repository.QuestionRepository;
import com.example.springexam.service.api.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;


    @Override
    public Answer create(Answer t) {
        return null;
    }
    @Override
    public List<Answer> createAll(List<Answer> answers) {
        log.info("ANSWERS TO SAVE");
        return answerRepository.saveAll(answers);
    }

    @Override
    public Page<Answer> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Answer getById(Long id) {
        return null;
    }

    @Override
    public Answer update(Long id, Answer t) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }


}