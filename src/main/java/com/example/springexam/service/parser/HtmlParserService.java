package com.example.springexam.service.parser;

import com.example.springexam.model.ParsedInfo;
import com.example.springexam.model.entity.*;
import com.example.springexam.model.entity.enums.*;
import com.example.springexam.service.api.*;
import com.example.springexam.service.parser.api.ParseService;
import com.example.springexam.utils.HtmlSelectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HtmlParserService implements ParseService {
    private static final String DEFAULT_QUESTION = "Question not found";
    private static final String DEFAULT_EXPLANATION = "Explanation not found";
    private static final String DEFAULT_TOPIC = "Topic not found";

    private final QuestionService questionService;
    private final TopicService topicService;
    private final AnswerService answerService;
    private final ExplanationService explanationService;

    @Override
    public ParsedInfo parseHtml(Element container, String url) {
        Topic topic = findAndSaveTopic(container);
        Question question = findAndSaveQuestion(container, topic);
        List<Answer> answers = findAndSaveAnswers(container, question);
        Explanation explanation = findAndSaveExplanation(container,question);

        return buildParsedInfo(url, question, answers, topic,explanation);
    }

    private Topic findAndSaveTopic(Element container) {
        return topicService.create(buildTopic(container));
    }
    private Question findAndSaveQuestion(Element container, Topic topic) {
        return questionService.create(buildQuestion(container, topic));
    }

    private Explanation findAndSaveExplanation(Element container, Question question) {
        String explanationText = extractExplanationText(container);
        Explanation explanation = Explanation.builder()
                .content(explanationText)
                .question(question)
                .build();
        return explanationService.create(explanation);
    }

    private Topic buildTopic(Element container) {
        String topicText = extractTopicText(container);
        return Topic.builder()
                .name(TopicType.fromDisplayName(topicText))
                .description("description")
                .build();
    }



    private String extractTopicText(Element container) {
        return Optional.ofNullable(container.selectFirst(HtmlSelectors.Topic.PANE))
                .map(pane -> pane.selectFirst(HtmlSelectors.Topic.NAME))
                .map(Element::text)
                .orElse(DEFAULT_TOPIC);
    }


    private Question buildQuestion(Element container, Topic topic) {
        return Question.builder()
                .questionText(extractQuestionText(container))
                .type(QuestionType.SINGLE_CHOICE)
                .topic(topic)
                .build();
    }

    private String extractQuestionText(Element container) {
        return Optional.ofNullable(container.selectFirst(HtmlSelectors.Question.PROMPT))
                .map(Element::text)
                .orElse(DEFAULT_QUESTION);
    }

    private List<Answer> findAndSaveAnswers(Element container, Question question) {
        return answerService.createAll(buildAnswers(container, question));
    }

    private List<Answer> buildAnswers(Element container, Question question) {
        return container.select(HtmlSelectors.Answer.TEXT).stream()
                .limit(4)
                .map(Element::text)
                .map(String::trim)
                .map(answerText -> buildAnswer(answerText, question))
                .toList();
    }

    private Answer buildAnswer(String answerText, Question question) {
        return Answer.builder()
                .question(question)
                .answerText(answerText)
                .isCorrect(false)
                .build();
    }

    private String extractExplanationText(Element container) {
        return Optional.ofNullable(container.selectFirst(HtmlSelectors.Explanation.CONTAINER))
                .map(expelContainer -> {
                    Element details = expelContainer.selectFirst(HtmlSelectors.Explanation.DETAILS);
                    return details != null ? details.text() : expelContainer.text();
                })
                .orElse(DEFAULT_EXPLANATION);
    }

    private ParsedInfo buildParsedInfo(String url, Question question,
                                       List<Answer> answers, Topic topic,
                                       Explanation explanation) {
        return ParsedInfo.builder()
                .url(url)
                .question(question.getQuestionText())
                .answer(answers.stream().map(Answer::getAnswerText).toList())
                .explanation(explanation.getContent())
                .topic(topic.getName().getDisplayName())
                .build();
    }
}