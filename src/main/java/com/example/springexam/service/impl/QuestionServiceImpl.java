package com.example.springexam.service.impl;

import com.example.springexam.exception.EntityAlreadyExistsException;
import com.example.springexam.model.entity.Question;
import com.example.springexam.repository.QuestionRepository;
import com.example.springexam.service.api.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public Question create(Question question) {
        if (questionRepository.existsByQuestionText(question.getQuestionText())) {
            throw new EntityAlreadyExistsException(Question.class, question.getQuestionText());
        }
        return questionRepository.save(question);
    }

    @Override
    public Page<Question> getAll(Pageable pageable) {
        throw new UnsupportedOperationException("getAll not implemented yet");
    }

    @Override
    public Question getById(Long id) {
        throw new UnsupportedOperationException("getById not implemented yet");
    }

    @Override
    public Question update(Long id, Question  question) {
        throw new UnsupportedOperationException("update not implemented yet");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("delete not implemented yet");
    }
}
