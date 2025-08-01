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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExplanationServiceImpl implements ExplanationService {

    private final ExplanationRepository explanationRepository;
    private final QuestionRepository questionRepository;
//    private final ExplanationMapper explanationMapper;

    @Override
    @Transactional
    public ExplanationResponse create(ExplanationRequest request) {
        return null;
    }

    @Override
    public Page<ExplanationResponse> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public ExplanationResponse getById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public ExplanationResponse update(Long id, ExplanationRequest request) {
//        return explanationRepository.findById(id)
//                .map(existing -> {
//                    explanationMapper.update(request, existing);
//                    if (!existing.getQue    stion().getId().equals(request.questionId())) {
//                        var newQuestion = questionRepository.findById(request.questionId())
//                                .orElseThrow(() -> new EntityNotFoundException(Question.class, request.questionId()));
//                        existing.setQuestion(newQuestion);
//                    }
//                    return explanationRepository.save(existing);
//                })
//                .map(explanationMapper::toResponse)
//                .orElseThrow(() -> new EntityNotFoundException(Explanation.class, id));
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}