package com.example.springexam.service.api;

import com.example.springexam.model.entity.Answer;
import com.example.springexam.service.AbstractService;

import java.util.List;

public interface AnswerService extends AbstractService<Long, Answer> {
    List<Answer> createAll(List<Answer> answers);
}
