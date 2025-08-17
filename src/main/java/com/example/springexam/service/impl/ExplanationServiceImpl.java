package com.example.springexam.service.impl;

import com.example.springexam.exception.EntityNotFoundException;
import com.example.springexam.model.entity.Explanation;
import com.example.springexam.repository.ExplanationRepository;
import com.example.springexam.service.api.ExplanationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Slf4j
@Service
@RequiredArgsConstructor
public class ExplanationServiceImpl implements ExplanationService {

    private final ExplanationRepository explanationRepository;


    @Override
    @Transactional
    public Explanation create(Explanation explanation) {
        Objects.requireNonNull(explanation, "Explanation cannot be null");
        log.debug("Creating explanation for question ID: {}", explanation.getQuestion().getId());
        return explanationRepository.save(explanation);
    }


    @Override
    public Page<Explanation> getAll(Pageable pageable) {
        throw new UnsupportedOperationException("Retrieving all explanations with pagination is not supported");
    }


    @Override
    @Transactional(readOnly = true)
    public String getExplanationContent(Long questionId) {
        Objects.requireNonNull(questionId, "Question ID cannot be null");
        log.debug("Fetching explanation content for question ID: {}", questionId);
        Explanation explanation = explanationRepository.findByQuestionId(questionId);
        if (explanation == null || explanation.getContent() == null) {
            log.warn("No explanation found for question ID: {}", questionId);
            throw new EntityNotFoundException(Explanation.class,questionId);
        }
        return explanation.getContent();
    }

    @Override
    public Explanation getById(Long id) {
        throw new UnsupportedOperationException("Retrieving explanation by ID is not supported");
    }

    @Override
    public Explanation update(Long id, Explanation explanation) {
        throw new UnsupportedOperationException("Updating explanations is not supported");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Deleting explanations is not supported");
    }
}