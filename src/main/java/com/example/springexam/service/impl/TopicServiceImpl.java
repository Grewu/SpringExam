package com.example.springexam.service.impl;

import com.example.springexam.model.entity.Topic;
import com.example.springexam.repository.TopicRepository;
import com.example.springexam.service.api.TopicService;
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
        return topicRepository.save(topic);
    }

    @Override
    public Page<Topic> getAll(Pageable pageable) {
        throw new UnsupportedOperationException("getAll not implemented yet");
    }

    @Override
    public Topic getById(Long id) {
        throw new UnsupportedOperationException("getById not implemented yet");
    }

    @Override
    public Topic update(Long id, Topic topic) {
        throw new UnsupportedOperationException("update not implemented yet");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("delete not implemented yet");
    }

}
