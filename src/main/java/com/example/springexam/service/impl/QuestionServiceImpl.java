package com.example.springexam.service.impl;

import com.example.springexam.model.dto.request.QuestionRequest;
import com.example.springexam.model.dto.response.QuestionResponse;
import com.example.springexam.model.entity.Topic;
import com.example.springexam.repository.QuestionRepository;
import com.example.springexam.repository.TopicRepository;
import com.example.springexam.service.api.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final TopicRepository topicRepository;
//    private final QuestionTypeRepository typeRepository;
//    private final QuestionMapper questionMapper;

    @Override
    @Transactional
    public QuestionResponse create(QuestionRequest request) {
//        var topic = topicRepository.findById(request.topicId())
//                .orElseThrow(() -> new EntityNotFoundException(Topic.class, request.topicId()));
//
//        var type = typeRepository.findById(request.typeId())
//                .orElseThrow(() -> new EntityNotFoundException(QuestionTypeEntity.class, request.typeId()));
//
//        var question = questionMapper.toEntity(request);
//        question.setTopic(topic);
//        question.setType(type);
//
//        return questionMapper.toResponse(questionRepository.save(question));
        return null;
    }

    @Override
    public Page<QuestionResponse> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public QuestionResponse getById(Long id) {
//        return questionRepository.findById(id)
//                .map(questionMapper::toResponse)
//                .orElseThrow(() -> new EntityNotFoundException(Question.class, id));
        return null;
    }

    @Override
    @Transactional
    public QuestionResponse update(Long id, QuestionRequest request) {
//        return questionRepository.findById(id)
//                .map(existing -> {
//                    questionMapper.update(request, existing);
//                    if (!existing.getTopic().getId().equals(request.topicId())) {
//                        var newTopic = topicRepository.findById(request.topicId())
//                                .orElseThrow(() -> new EntityNotFoundException(Topic.class, request.topicId()));
//                        existing.setTopic(newTopic);
//                    }
//                    if (!existing.getType().getId().equals(request.typeId())) {
//                        var newType = typeRepository.findById(request.typeId())
//                                .orElseThrow(() -> new EntityNotFoundException(QuestionTypeEntity.class, request.typeId()));
//                        existing.setType(newType);
//                    }
//                    return questionRepository.save(existing);
//                })
//                .map(questionMapper::toResponse)
//                .orElseThrow(() -> new EntityNotFoundException(Question.class, id));
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
//        if (!questionRepository.existsById(id)) {
//            throw new EntityNotFoundException(Question.class, id);
//        }
//        questionRepository.deleteById(id);

    }
}
