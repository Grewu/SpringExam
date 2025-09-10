package com.example.springexam.service.parser;

import com.example.springexam.model.dto.response.html.HtmlParsedResponse;
import com.example.springexam.model.entity.Answer;
import com.example.springexam.model.entity.Explanation;
import com.example.springexam.model.entity.Question;
import com.example.springexam.model.entity.Topic;
import com.example.springexam.service.parser.api.EntityParser;
import com.example.springexam.service.parser.api.ParseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class HtmlParserService implements ParseService<HtmlParsedResponse, Element> {

    private final EntityParser<Topic, String> topicParser;
    private final EntityParser<Question, Topic> questionParser;
    private final EntityParser<List<Answer>, Question> answerParser;
    private final EntityParser<Explanation, Question> explanationParser;
    @Override
    public HtmlParsedResponse parseHtml(Element container,String fileName) {
        Objects.requireNonNull(container, "Container element cannot be null");

        Topic topic = parseTopic(container,fileName);
        Question question = parseQuestion(container, topic);
        List<Answer> answers = parseAnswers(container, question);
        Explanation explanation = parseExplanation(container, question);

        return buildResponse(question, answers, explanation, topic);
    }

    private Topic parseTopic(Element container, String fileName) {
        return topicParser.parseAndSave(container, fileName);
    }

    private Question parseQuestion(Element container, Topic topic) {
        return questionParser.parseAndSave(container, topic);
    }

    private List<Answer> parseAnswers(Element container, Question question) {
        return answerParser.parseAndSave(container, question);
    }

    private Explanation parseExplanation(Element container, Question question) {
        return explanationParser.parseAndSave(container, question);
    }

    private HtmlParsedResponse buildResponse(Question question, List<Answer> answers,
                                             Explanation explanation, Topic topic) {
        return HtmlParsedResponse.builder()
                .question(question.getQuestionText())
                .answer(answers.stream().map(Answer::getAnswerText).toList())
                .explanation(explanation.getContent())
                .topic(topic.getName().getDisplayName())
                .build();
    }
}