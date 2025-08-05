package com.example.springexam.service.impl;

import com.example.springexam.model.entity.Explanation;
import com.example.springexam.repository.ExplanationRepository;
import com.example.springexam.service.api.ExplanationService;
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
    @Override
    public Explanation create(Explanation explanation) {
        return explanationRepository.save(explanation);
    }

    @Override
    public Page<Explanation> getAll(Pageable pageable) {
        throw new UnsupportedOperationException("getAll not implemented yet");
    }
    @Override
    public String getExplanationContent(Long questionId) {
        Explanation explanation = explanationRepository.findByQuestionId(questionId);
        return explanation != null ? explanation.getContent() : "Explanation not found";
    }
    @Override
    public Explanation getById(Long id) {
        throw new UnsupportedOperationException("getById not implemented yet");
    }

    @Override
    public Explanation update(Long id, Explanation explanation) {
        throw new UnsupportedOperationException("update not implemented yet");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("delete not implemented yet");
    }


}