package com.example.springexam.service.impl;

import com.example.springexam.exception.EntityAlreadyExistsException;
import com.example.springexam.model.dto.response.html.HtmlParsedResponse;
import com.example.springexam.model.entity.Question;
import com.example.springexam.repository.QuestionRepository;
import com.example.springexam.service.api.AnswerService;
import com.example.springexam.service.api.ExplanationService;
import com.example.springexam.service.api.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerService answerService;
    private final ExplanationService explanationService;

    @Override
    @Transactional
    public Question create(Question question) {
        Objects.requireNonNull(question, "Question cannot be null");
        if (question.getQuestionText() == null || question.getQuestionText().trim().isEmpty()) {
            log.error("Question text cannot be null or empty");
            throw new IllegalArgumentException("Question text cannot be null or empty");
        }
//        if (questionRepository.existsByQuestionText(question.getQuestionText())) {
//            log.warn("Question with text '{}' already exists", question.getQuestionText());
//            throw new EntityAlreadyExistsException(Question.class, question.getQuestionText());
//        }
        log.debug("Creating question with text: {}", question.getQuestionText());
        return questionRepository.save(question);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Question> getAll(Pageable pageable) {
        Objects.requireNonNull(pageable, "Pageable cannot be null");
        log.debug("Fetching all questions with pageable: {}", pageable);
        return questionRepository.findAll(pageable);
    }

    @Override
    public Question getById(Long id) {
        throw new UnsupportedOperationException("Retrieving question by ID is not supported");
    }

    @Override
    public Question update(Long id, Question question) {
        throw new UnsupportedOperationException("Updating questions is not supported");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Deleting questions is not supported");
    }

    @Override
    @Transactional(readOnly = true)
    public List<HtmlParsedResponse> getAllParsedInfo() {
        log.debug("Fetching all parsed question information");

        List<HtmlParsedResponse> responses = questionRepository.findAll()
                .stream()
                .map(this::toHtmlParsedResponse)
                .toList();

        if (responses.isEmpty()) {
            log.warn("No questions found for parsed info");
        }
        return responses;

    }

    private HtmlParsedResponse toHtmlParsedResponse(Question question) {
        Objects.requireNonNull(question, "Question cannot be null");
        String questionText = question.getQuestionText() != null ? question.getQuestionText() : "";
        List<String> answers = answerService.getAnswerTextsByQuestionId(question.getId());
        if (answers == null) {
            log.warn("No answers found for question ID: {}", question.getId());
            answers = new ArrayList<>();
        }
        String explanation = explanationService.getExplanationContent(question.getId());
        if (explanation == null) {
            log.warn("No explanation found for question ID: {}", question.getId());
            explanation = "";
        }
        String topic = question.getTopic() != null && question.getTopic().getName() != null ?
                question.getTopic().getName().name() : "";
        if (topic.isEmpty()) {
            log.warn("No topic found for question ID: {}", question.getId());
        }
        return HtmlParsedResponse.builder()
                .question(questionText)
                .answer(answers)
                .explanation(explanation)
                .topic(topic)
                .build();
    }
}