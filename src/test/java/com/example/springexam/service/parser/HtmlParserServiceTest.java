package com.example.springexam.service.parser;

import com.example.springexam.model.entity.Answer;
import com.example.springexam.model.entity.Explanation;
import com.example.springexam.model.entity.Question;
import com.example.springexam.model.entity.Topic;
import com.example.springexam.service.parser.api.EntityParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class HtmlParserServiceTest {

    @Mock
    private EntityParser<Topic, Void> topicParser;

    @Mock
    private EntityParser<Question, Topic> questionParser;

    @Mock
    private EntityParser<List<Answer>, Question> answerParser;

    @Mock
    private EntityParser<Explanation, Question> explanationParser;

    @InjectMocks
    private HtmlParserService htmlParserService;


    @Test
    void parseHtmlShouldThrowExceptionWhenContainerIsNull() {
        // then
        assertThrows(NullPointerException.class, () -> htmlParserService.parseHtml(null));
        verifyNoInteractions(topicParser, questionParser, answerParser, explanationParser);
    }

}
