package com.example.springexam.service.parser.entity;

import com.example.springexam.model.entity.Explanation;
import com.example.springexam.model.entity.Question;
import com.example.springexam.service.api.ExplanationService;
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
public class ExplanationParser implements EntityParser<Explanation, Question> {

    private final ExplanationService explanationService;

    @Override
    @Transactional
    public Explanation parseAndSave(Element container, Question question) {
        Objects.requireNonNull(container, "Container cannot be null");
        Objects.requireNonNull(question, "Question cannot be null");
        log.debug("Parsing explanation for question ID: {}", question.getId());
        return explanationService.create(buildExplanation(container, question));
    }

    private Explanation buildExplanation(Element container, Question question) {
        return Explanation.builder()
                .content(extractExplanationText(container))
                .question(question)
                .build();
    }

    private String extractExplanationText(Element container) {
        return Optional.ofNullable(container.selectFirst(HtmlSelectors.Explanation.CONTAINER))
                .map(explContainer -> {
                    Element details = explContainer.selectFirst(HtmlSelectors.Explanation.DETAILS);
                    return details != null ? details.text() : explContainer.text();
                })
                .map(String::trim)
                .filter(text -> !text.isEmpty())
                .orElseGet(() -> {
                    log.warn("No explanation text found in container");
                    return "No explanation available";
                });
    }
}