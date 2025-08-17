package com.example.springexam.service.parser.entity;

import com.example.springexam.model.entity.Question;
import com.example.springexam.model.entity.Topic;
import com.example.springexam.service.api.QuestionService;
import com.example.springexam.service.parser.api.EntityParser;
import com.example.springexam.utils.HtmlSelectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionParser implements EntityParser<Question, Topic> {
    private final QuestionService questionService;


    @Override
    @Transactional
    public Question parseAndSave(Element container, Topic topic) {
        Objects.requireNonNull(container, "Container cannot be null");
        Objects.requireNonNull(topic, "Topic cannot be null");
        log.debug("Parsing question for topic ID: {}", topic.getId());
        try {
            return questionService.create(buildQuestion(container, topic));
        } catch (Exception e) {
            log.warn("Failed to save question for topic ID: {}", topic.getId(), e);
            throw e;
        }
    }

    private Question buildQuestion(Element container, Topic topic) {
        return Question.builder()
                .questionText(extractQuestionText(container))
                .topic(topic)
                .build();
    }


    private String extractQuestionText(Element container) {
        return Optional.ofNullable(container.selectFirst(HtmlSelectors.Question.PROMPT))
                .map(e -> e.text().trim())
                .filter(text -> !text.isEmpty())
                .orElseGet(() -> {
                    log.warn("No question text found in container");
                    return "Question not found";
                });
    }
}
