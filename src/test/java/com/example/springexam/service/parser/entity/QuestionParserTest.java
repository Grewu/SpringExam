package com.example.springexam.service.parser.entity;

import com.example.springexam.data.QuestionTestData;
import com.example.springexam.data.TopicTestData;
import com.example.springexam.model.entity.Question;
import com.example.springexam.service.api.QuestionService;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionParserTest {

    private static final String TAG = "div";
    private static final String BASE_URI = "";
    private static final String PROMPT_SELECTOR = "#question-prompt";
    private static final String PROMPT_TEXT = "Sample question?";
    private static final String FALLBACK_TEXT = "Question not found";
    public static final String ATTRIBUTE_KEY = "id";
    public static final String STRING_EMPTY = "";
    public static final int FIRST_ARGUMENT = 0;
    public static final int BEGIN_INDEX = 1;

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private QuestionParser questionParser;

    @Test
    void parseAndSaveShouldReturnQuestionWhenPromptExists() {
        // given
        var topic = TopicTestData.builder().build().buildTopic();
        var expected = QuestionTestData.builder().withTopic(topic).withQuestionText(PROMPT_TEXT).build().buildQuestion();

        var container = new Element(Tag.valueOf(TAG), BASE_URI);
        container.appendElement(TAG)
                .attr(ATTRIBUTE_KEY, PROMPT_SELECTOR.substring(1))
                .text(PROMPT_TEXT);

        when(questionService.create(any())).thenAnswer(inv -> inv.getArgument(FIRST_ARGUMENT));

        // when
        var actual = questionParser.parseAndSave(container, topic);

        // then
        assertNotNull(actual);
        assertEquals(expected.getQuestionText(), actual.getQuestionText());
        assertEquals(topic, actual.getTopic());
        verify(questionService).create(any(Question.class));
    }

    @Test
    void parseAndSaveShouldReturnFallbackWhenPromptIsEmpty() {
        var topic = TopicTestData.builder().build().buildTopic();

        var container = new Element(Tag.valueOf(TAG), BASE_URI);
        container.appendElement(TAG).attr(ATTRIBUTE_KEY, PROMPT_SELECTOR.substring(BEGIN_INDEX)).text(STRING_EMPTY);

        when(questionService.create(any())).thenAnswer(inv -> inv.getArgument(FIRST_ARGUMENT));

        var actual = questionParser.parseAndSave(container, topic);

        assertNotNull(actual);
        assertEquals(FALLBACK_TEXT, actual.getQuestionText());
    }

    @Test
    void parseAndSaveShouldReturnFallbackWhenPromptMissing() {
        var topic = TopicTestData.builder().build().buildTopic();
        var container = new Element(Tag.valueOf(TAG), BASE_URI);

        when(questionService.create(any())).thenAnswer(inv -> inv.getArgument(FIRST_ARGUMENT));

        var actual = questionParser.parseAndSave(container, topic);

        assertNotNull(actual);
        assertEquals(FALLBACK_TEXT, actual.getQuestionText());
    }

    @Test
    void parseAndSaveShouldThrowWhenContainerIsNull() {
        var topic = TopicTestData.builder().build().buildTopic();

        assertThrows(NullPointerException.class, () -> questionParser.parseAndSave(null, topic));
        verifyNoInteractions(questionService);
    }

    @Test
    void parseAndSaveShouldThrowWhenTopicIsNull() {
        var container = new Element(Tag.valueOf(TAG), BASE_URI);

        assertThrows(NullPointerException.class, () -> questionParser.parseAndSave(container, null));
        verifyNoInteractions(questionService);
    }
}