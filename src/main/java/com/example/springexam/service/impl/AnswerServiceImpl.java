package com.example.springexam.service.impl;

import com.example.springexam.model.entity.Answer;
import com.example.springexam.repository.AnswerRepository;
import com.example.springexam.service.api.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    public Answer create(Answer answer) {
        throw new UnsupportedOperationException("Single answer creation is not supported. Use createAll instead.");
    }

    @Override
    @Transactional
    public List<Answer> createAll(List<Answer> answers) {
        if (answers == null || answers.isEmpty()) {
            log.warn("Attempted to create null or empty answer list");
            return List.of();
        }
        log.debug("Creating {} answers", answers.size());
        return answerRepository.saveAll(answers);
    }

    @Override
    public Page<Answer> getAll(Pageable pageable) {
        throw new UnsupportedOperationException("Retrieving all answers with pagination is not supported");
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAnswerTextsByQuestionId(Long questionId) {
        Objects.requireNonNull(questionId, "Question ID cannot be null");
        log.debug("Fetching answer texts for question ID: {}", questionId);
        List<String> texts = answerRepository.findByQuestionId(questionId)
                .stream()
                .map(answer -> answer.getAnswerText() != null ? answer.getAnswerText() : "")
                .toList();
        if (texts.isEmpty()) {
            log.warn("No answer texts found for question ID: {}", questionId);
        }
        return texts;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Answer> getAnswerByQuestionId(Long questionId) {
        Objects.requireNonNull(questionId, "Question ID cannot be null");
        log.debug("Fetching answers for question ID: {}", questionId);
        List<Answer> answers = answerRepository.findByQuestionId(questionId);
        if (answers.isEmpty()) {
            log.warn("No answers found for question ID: {}", questionId);
        }
        return answers;
    }

    @Override
    public Answer getById(Long id) {
        throw new UnsupportedOperationException("Retrieving answer by ID is not supported");
    }


    @Override
    public Answer update(Long id, Answer answer) {
        throw new UnsupportedOperationException("Updating answers is not supported");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Deleting answers is not supported");
    }
}