package com.example.springexam.service.impl;

import com.example.springexam.model.dto.request.AnswerRequest;
import com.example.springexam.model.dto.response.AnswerResponse;
import com.example.springexam.repository.AnswerRepository;
import com.example.springexam.repository.QuestionRepository;
import com.example.springexam.service.api.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
//    private final AnswerMapper answerMapper;

    @Override
    @Transactional
    public AnswerResponse create(AnswerRequest request) {
//        var question = questionRepository.findById(request.questionId())
//                .orElseThrow(() -> new EntityNotFoundException(Question.class, request.questionId()));
//
//        var answer = answerMapper.toEntity(request);
//        answer.setQuestion(question);
//
//        return answerMapper.toResponse(answerRepository.save(answer));
        return null;
    }

    @Override
    public Page<AnswerResponse> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public AnswerResponse getById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public AnswerResponse update(Long id, AnswerRequest request) {
//        return answerRepository.findById(id)
//                .map(existing -> {
//                    answerMapper.update(request, existing);
//                    if (!existing.getQuestion().getId().equals(request.questionId())) {
//                        var newQuestion = questionRepository.findById(request.questionId())
//                                .orElseThrow(() -> new EntityNotFoundException(Question.class, request.questionId()));
//                        existing.setQuestion(newQuestion);
//                    }
//                    return answerRepository.save(existing);
//                })
//                .map(answerMapper::toResponse)
//                .orElseThrow(() -> new EntityNotFoundException(Answer.class, id));
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
//        if (!answerRepository.existsById(id)) {
//            throw new EntityNotFoundException(Answer.class, id);
//        }
//        answerRepository.deleteById(id);
    }
}