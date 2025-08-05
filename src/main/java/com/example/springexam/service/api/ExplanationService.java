package com.example.springexam.service.api;

import com.example.springexam.model.entity.Explanation;
import com.example.springexam.service.AbstractService;

public interface ExplanationService extends AbstractService<Long, Explanation> {
    String getExplanationContent(Long questionId);
}
