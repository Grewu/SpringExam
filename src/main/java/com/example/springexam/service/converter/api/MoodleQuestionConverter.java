package com.example.springexam.service.converter.api;

import com.example.springexam.model.entity.Question;
import com.example.springexam.model.dto.response.xml.MoodleQuestion;

public interface MoodleQuestionConverter {
    MoodleQuestion convert(Question question);
}
