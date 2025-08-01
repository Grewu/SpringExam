package com.example.springexam.service.impl;

import com.example.springexam.model.dto.request.TopicRequest;
import com.example.springexam.model.dto.response.TopicResponse;
import com.example.springexam.model.entity.Topic;
import com.example.springexam.repository.TopicRepository;
import com.example.springexam.service.api.TopicService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Override
    public TopicResponse create(TopicRequest t) {
        return null;
    }

    @Override
    public Page<TopicResponse> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public TopicResponse getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public TopicResponse update(Long id, TopicRequest t) {
        return null;
    }
//    private final TopicMapper topicMapper;


}
