package com.example.springexam.service.api;

import com.example.springexam.model.dto.request.QuestionRequest;
import com.example.springexam.model.dto.response.QuestionResponse;
import com.example.springexam.service.AbstractService;

public interface QuestionService extends AbstractService<Long, QuestionRequest, QuestionResponse> {
}
