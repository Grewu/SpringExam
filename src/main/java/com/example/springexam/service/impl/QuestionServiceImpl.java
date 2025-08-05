package com.example.springexam.service.impl;

import com.example.springexam.exception.EntityAlreadyExistsException;
import com.example.springexam.model.dto.response.ParsedInfo;
import com.example.springexam.model.entity.Question;
import com.example.springexam.repository.QuestionRepository;
import com.example.springexam.service.api.AnswerService;
import com.example.springexam.service.api.ExplanationService;
import com.example.springexam.service.api.QuestionService;
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
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerService answerService;
    private final ExplanationService explanationService;

    @Override
    public Question create(Question question) {
        if (questionRepository.existsByQuestionText(question.getQuestionText())) {
            throw new EntityAlreadyExistsException(Question.class, question.getQuestionText());
        }
        return questionRepository.save(question);
    }

    @Override
    public Page<Question> getAll(Pageable pageable) {
        return questionRepository.findAll(pageable);
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

    public List<ParsedInfo> getAllParsedInfo() {
        return questionRepository.findAll()
                .stream()
                .map(this::toParsedInfo)
                .toList();
    }

    private ParsedInfo toParsedInfo(Question question) {
        return ParsedInfo.builder()
                .question(question.getQuestionText())
                .answer(answerService.getAnswerTextsByQuestionId(question.getId()))
                .explanation(explanationService.getExplanationContent(question.getId()))
                .topic(question.getTopic().getName().name())
                .build();
    }
}
