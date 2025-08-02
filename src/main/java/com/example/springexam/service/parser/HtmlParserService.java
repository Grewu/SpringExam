package com.example.springexam.service.parser;

import com.example.springexam.model.dto.response.ParsedInfo;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class HtmlParserService implements ParseService {
    private final EntityParser<Topic, Void> topicParser;
    private final EntityParser<Question, Topic> questionParser;
    private final EntityParser<List<Answer>, Question> answerParser;
    private final EntityParser<Explanation, Question> explanationParser;

    @Override
    public ParsedInfo parseHtml(Element container, String url) {
        Topic topic = topicParser.parseAndSave(container, null);
        Question question = questionParser.parseAndSave(container, topic);
        List<Answer> answers = answerParser.parseAndSave(container, question);
        Explanation explanation = explanationParser.parseAndSave(container, question);

        return ParsedInfo.builder()
                .url(url)
                .question(question.getQuestionText())
                .answer(answers.stream().map(Answer::getAnswerText).toList())
                .explanation(explanation.getContent())
                .topic(topic.getName().getDisplayName())
                .build();
    }

}
