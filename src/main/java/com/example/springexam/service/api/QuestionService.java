package com.example.springexam.service.api;

import com.example.springexam.model.dto.response.ParsedInfo;
import com.example.springexam.model.entity.Question;
import com.example.springexam.service.AbstractService;

import java.util.List;

public interface QuestionService extends AbstractService<Long, Question> {
    List<ParsedInfo> getAllParsedInfo();
}
