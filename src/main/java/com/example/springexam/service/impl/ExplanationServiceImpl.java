package com.example.springexam.service.impl;

import com.example.springexam.model.dto.request.ExplanationRequest;
import com.example.springexam.model.dto.response.ExplanationResponse;
import com.example.springexam.model.entity.Explanation;
import com.example.springexam.model.entity.Question;
import com.example.springexam.repository.ExplanationRepository;
import com.example.springexam.repository.QuestionRepository;
import com.example.springexam.service.api.ExplanationService;
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
public class ExplanationServiceImpl implements ExplanationService {

    private final ExplanationRepository explanationRepository;
    private final QuestionRepository questionRepository;

    @Override
    public Explanation create(Explanation explanation) {
        log.info("Explanation SAVE");
        return explanationRepository.save(explanation);
    }

    @Override
    public Page<Explanation> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Explanation getById(Long id) {
        return null;
    }

    @Override
    public Explanation update(Long id, Explanation t) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}