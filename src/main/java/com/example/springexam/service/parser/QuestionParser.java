package com.example.springexam.service.parser;

import com.example.springexam.model.ParsedInfo;
import com.example.springexam.utils.HtmlSelectors;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionParser {
    private static final String DEFAULT_QUESTION = "Question not found";
    private static final String DEFAULT_EXPLANATION = "Explanation not found";
    private static final String DEFAULT_TOPIC = "Topic not found";

    public ParsedInfo parseQuestion(Element container, String url) {
        return ParsedInfo.builder()
                .url(url)
                .question(findQuestionText(container))
                .answer(findAnswers(container))
                .explanation(findExplanation(container))
                .topic(findTopic(container))
                .build();
    }

    private String findQuestionText(Element container) {
        return Optional.ofNullable(container.selectFirst(HtmlSelectors.Question.PROMPT))
                .map(Element::text)
                .orElse(DEFAULT_QUESTION);
    }

    private List<String> findAnswers(Element container) {
        return container.select(HtmlSelectors.Answer.TEXT)
                .stream()
                .limit(4)
                .map(Element::text)
                .map(String::trim)
                .toList();
    }

    private String findExplanation(Element container) {
        Element explanationContainer = container.selectFirst(HtmlSelectors.Explanation.CONTAINER);
        if (explanationContainer != null) {
            Element details = explanationContainer.selectFirst(HtmlSelectors.Explanation.DETAILS);
            return details != null ? details.text() : explanationContainer.text();
        }
        return DEFAULT_EXPLANATION;
    }

    private String findTopic(Element container) {
        return Optional.ofNullable(container.selectFirst(HtmlSelectors.Topic.PANE))
                .map(pane -> pane.selectFirst(HtmlSelectors.Topic.NAME))
                .map(Element::text)
                .orElse(DEFAULT_TOPIC);
    }

}