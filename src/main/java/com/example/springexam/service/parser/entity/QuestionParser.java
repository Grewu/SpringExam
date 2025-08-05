package com.example.springexam.service.parser.entity;

import com.example.springexam.model.entity.Question;
import com.example.springexam.model.entity.Topic;
import com.example.springexam.model.entity.enums.QuestionType;
import com.example.springexam.service.api.QuestionService;
import com.example.springexam.service.parser.api.EntityParser;
import com.example.springexam.utils.HtmlSelectors;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionParser implements EntityParser<Question, Topic> {
    private final QuestionService questionService;

    @Override
    @Transactional
    public Question parseAndSave(Element container, Topic topic) {
        return questionService.create(buildQuestion(container, topic));
    }

    private Question buildQuestion(Element container, Topic topic) {
        return Question.builder()
                .questionText(extractQuestionText(container))
                .topic(topic)
                .build();
    }

    private String extractQuestionText(Element container) {
        return Optional.ofNullable(container.selectFirst(HtmlSelectors.Question.PROMPT))
                .map(Element::text)
                .orElse("Question not found");
    }
}
