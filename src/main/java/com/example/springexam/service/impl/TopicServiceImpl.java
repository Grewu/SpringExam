package com.example.springexam.service.impl;

import com.example.springexam.exception.EntityAlreadyExistsException;
import com.example.springexam.model.dto.request.TopicRequest;
import com.example.springexam.model.dto.response.TopicResponse;
import com.example.springexam.model.entity.Question;
import com.example.springexam.model.entity.Topic;
import com.example.springexam.repository.TopicRepository;
import com.example.springexam.service.api.TopicService;
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
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    @Override
    public Topic create(Topic topic) {
        log.info("TOPIC TO SAVE");
        return topicRepository.save(topic);
    }

    @Override
    public Page<Topic> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Topic getById(Long id) {
        return null;
    }

    @Override
    public Topic update(Long id, Topic t) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
