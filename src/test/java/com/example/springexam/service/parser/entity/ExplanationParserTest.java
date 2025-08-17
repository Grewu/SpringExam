package com.example.springexam.service.parser.entity;

import com.example.springexam.data.ExplanationTestData;
import com.example.springexam.data.QuestionTestData;
import com.example.springexam.model.entity.Explanation;
import com.example.springexam.service.api.ExplanationService;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExplanationParserTest {

    private static final String TAG_NAME = "div";
    private static final String BASE_URI = "";
    private static final String CONTAINER_CLASS = "overall-explanation-pane--overall-explanation123";
    private static final String DETAILS_CLASS = "ud-text-md rt-scaffolding";

    @Mock
    private ExplanationService explanationService;

    @InjectMocks
    private ExplanationParser explanationParser;

    @Test
    void parseAndSaveShouldReturnExplanation() {
        // given
        var question = QuestionTestData.builder().build().buildQuestion();
        var expected = ExplanationTestData.builder().build().buildExplanation();

        var explContainer = new Element(Tag.valueOf(TAG_NAME), BASE_URI).addClass(CONTAINER_CLASS);
        var details = new Element(Tag.valueOf(TAG_NAME), BASE_URI).addClass(DETAILS_CLASS).text(expected.getContent());
        explContainer.appendChild(details);

        var container = new Element(Tag.valueOf(TAG_NAME), BASE_URI);
        container.appendChild(explContainer);

        when(explanationService.create(any())).thenAnswer(inv -> inv.getArgument(0));

        // when
        var actual = explanationParser.parseAndSave(container, question);

        // then
        assertNotNull(actual);
        assertEquals(expected.getContent(), actual.getContent());
        assertEquals(question, actual.getQuestion());
        verify(explanationService).create(any(Explanation.class));
    }

    @Test
    void parseAndSaveShouldReturnFallbackTextWhenNoDetailsInside() {
        // given
        var question = QuestionTestData.builder().build().buildQuestion();
        var expected = ExplanationTestData.builder().build().buildExplanation();

        var explContainer = new Element(Tag.valueOf(TAG_NAME), BASE_URI)
                .addClass(CONTAINER_CLASS);
        explContainer.text(expected.getContent()); // без DETAILS

        var container = new Element(Tag.valueOf(TAG_NAME), BASE_URI);
        container.appendChild(explContainer);

        when(explanationService.create(any())).thenAnswer(inv -> inv.getArgument(0));

        // when
        var actual = explanationParser.parseAndSave(container, question);

        // then
        assertNotNull(actual);
        assertEquals(expected.getContent(), actual.getContent());
        assertEquals(question, actual.getQuestion());
    }

    @Test
    void parseAndSaveShouldReturnNullContentWhenContainerIsMissing() {
        // given
        var question = QuestionTestData.builder().build().buildQuestion();
        var container = new Element(Tag.valueOf(TAG_NAME), BASE_URI);

        when(explanationService.create(any())).thenAnswer(inv -> inv.getArgument(0));

        // when
        var actual = explanationParser.parseAndSave(container, question);

        // then
        assertNotNull(actual);
        assertNull(actual.getContent());
        assertEquals(question, actual.getQuestion());
    }

    @Test
    void parseAndSaveShouldThrowWhenContainerIsNull() {
        var question = QuestionTestData.builder().build().buildQuestion();
        assertThrows(NullPointerException.class,
                () -> explanationParser.parseAndSave(null, question));
        verifyNoInteractions(explanationService);
    }

    @Test
    void parseAndSaveShouldThrowWhenQuestionIsNull() {
        var container = new Element(Tag.valueOf(TAG_NAME), BASE_URI);
        assertThrows(NullPointerException.class,
                () -> explanationParser.parseAndSave(container, null));
        verifyNoInteractions(explanationService);
    }
}
