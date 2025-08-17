package com.example.springexam.service.impl;

import com.example.springexam.model.entity.Topic;
import com.example.springexam.repository.TopicRepository;
import com.example.springexam.service.api.TopicService;
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
@Transactional(readOnly = true)
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    @Override
    public Topic create(Topic topic) {
        Objects.requireNonNull(topic, "Topic cannot be null");
        if (topic.getName() == null || topic.getName().toString().trim().isEmpty()) {
            log.error("Topic name cannot be null or empty");
            throw new IllegalArgumentException("Topic name cannot be null or empty");
        }
        log.debug("Creating topic with name: {}", topic.getName());
        try {
            return topicRepository.save(topic);
        } catch (DataAccessException e) {
            log.error("Failed to create topic with name: {}", topic.getName(), e);
            throw e;
        }
    }

    @Override
    public Page<Topic> getAll(Pageable pageable) {
        throw new UnsupportedOperationException("Retrieving all topics with pagination is not supported");
    }

    @Override
    public Topic getById(Long id) {
        throw new UnsupportedOperationException("Retrieving topic by ID is not supported");
    }

    @Override
    public Topic update(Long id, Topic topic) {
        throw new UnsupportedOperationException("Updating topics is not supported");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Deleting topics is not supported");
    }
}
