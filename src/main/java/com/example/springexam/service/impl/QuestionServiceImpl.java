package com.example.springexam.service.impl;

import com.example.springexam.exception.EntityAlreadyExistsException;
import com.example.springexam.model.dto.request.QuestionRequest;
import com.example.springexam.model.dto.response.QuestionResponse;
import com.example.springexam.model.entity.Question;
import com.example.springexam.model.entity.Topic;
import com.example.springexam.repository.QuestionRepository;
import com.example.springexam.repository.TopicRepository;
import com.example.springexam.service.api.QuestionService;
import jakarta.persistence.EntityNotFoundException;
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
    private final TopicRepository topicRepository;

    @Override
    public Question create(Question question) {
        if (questionRepository.existsByQuestionText(question.getQuestionText())) {
            throw new EntityAlreadyExistsException(Question.class, question.getQuestionText());
        }
        log.info("QUESTION TO SAVE");
        return questionRepository.save(question);
    }

    @Override
    public Page<Question> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Question getById(Long id) {
        return null;
    }

    @Override
    public Question update(Long id, Question t) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
