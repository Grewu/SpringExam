package com.example.springexam.service.parser.entity;

import com.example.springexam.data.AnswerTestData;
import com.example.springexam.data.QuestionTestData;
import com.example.springexam.model.entity.Answer;
import com.example.springexam.model.entity.Question;
import com.example.springexam.service.api.AnswerService;
import com.example.springexam.utils.HtmlSelectors;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerParserTest {

    private static final String TAG_NAME = "div";
    private static final String BASE_URI = "";
    private static final String SPAN_TAG = "span";

    private static final String ANSWER_TEXT = "answer-text";
    private static final String ANSWER_1_TEXT = "Answer 1";
    private static final String SOME_ANSWER_TEXT = "Some answer text";
    private static final String TEXT = "text";
    private static final String CORRECT_LABEL_CLASS = "correct-label";

    // Test data constants
    private static final String INVALID_LABEL = "invalid";
    private static final String EMPTY_STRING = "";
    public static final String ATTRIBUTE_KEY = "id";

    @Mock
    private AnswerService answerService;

    @InjectMocks
    private AnswerParser answerParser;

    @Test
    void parseAndSaveShouldReturnAnswers() {
        // given
        Question question = QuestionTestData.builder().build().buildQuestion();
        Element container = createTestContainerWithAnswer(false);

        List<Answer> expectedAnswers = List.of(
                AnswerTestData.builder()
                        .withQuestion(question)
                        .build()
                        .buildAnswer()
        );

        when(answerService.createAll(any())).thenReturn(expectedAnswers);

        // when
        List<Answer> actual = answerParser.parseAndSave(container, question);

        // then
        assertNotNull(actual);
        assertEquals(1, actual.size());
        assertEquals(expectedAnswers.getFirst().getAnswerText(), actual.getFirst().getAnswerText());
        verify(answerService).createAll(any());
    }

    @Test
    void parseAndSaveShouldMarkAnswerAsCorrect() {
        // given
        Question question = QuestionTestData.builder().build().buildQuestion();
        Element container = createTestContainerWithAnswer(true);

        Answer expectedAnswer = AnswerTestData.builder()
                .withIsCorrect(true)
                .withQuestion(question)
                .build()
                .buildAnswer();

        when(answerService.createAll(any())).thenReturn(List.of(expectedAnswer));

        // when
        List<Answer> actual = answerParser.parseAndSave(container, question);

        // then
        assertEquals(1, actual.size());
        assertTrue(actual.getFirst().getIsCorrect());
        assertEquals(expectedAnswer.getAnswerText(), actual.getFirst().getAnswerText());
    }

    @Test
    void parseAndSaveShouldThrowWhenContainerIsNull() {
        // given
        Question question = QuestionTestData.builder().build().buildQuestion();

        // when & then
        assertThrows(NullPointerException.class,
                () -> answerParser.parseAndSave(null, question));
        verifyNoInteractions(answerService);
    }

    @Test
    void parseAndSaveShouldThrowWhenQuestionIsNull() {
        // given
        Element container = new Element(Tag.valueOf(TAG_NAME), BASE_URI);

        // when & then
        assertThrows(NullPointerException.class,
                () -> answerParser.parseAndSave(container, null));
        verifyNoInteractions(answerService);
    }

    @Test
    void parseAndSaveShouldReturnEmptyListWhenNoAnswers() {
        // given
        Question question = QuestionTestData.builder().build().buildQuestion();
        Element container = new Element(Tag.valueOf(TAG_NAME), BASE_URI);

        when(answerService.createAll(List.of())).thenReturn(List.of());

        // when
        List<Answer> actual = answerParser.parseAndSave(container, question);

        // then
        assertTrue(actual.isEmpty());
        verify(answerService).createAll(List.of());
    }

    @Test
    void isValidCorrectLabelShouldReturnTrueForValidLabels() throws Exception {
        // given
        Method method = getPrivateMethod("isValidCorrectLabel", String.class);

        // when & then
        assertFalse((Boolean) method.invoke(answerParser, EMPTY_STRING));
        assertFalse((Boolean) method.invoke(answerParser, INVALID_LABEL));
        assertFalse((Boolean) method.invoke(answerParser, (Object) null));
    }

    @Test
    void isAnswerCorrectShouldReturnTrueWhenTextElementExists() throws Exception {
        // given
        Method method = getPrivateMethod("isAnswerCorrect", Element.class);
        Element answerBlock = new Element(TAG_NAME)
                .appendChild(new Element(SPAN_TAG).text(SOME_ANSWER_TEXT));

        // when & then
        assertFalse((Boolean) method.invoke(answerParser, answerBlock));
    }

    @Test
    void isAnswerCorrectShouldReturnTrueWhenCorrectLabelExists() throws Exception {
        // given
        Method method = getPrivateMethod("isAnswerCorrect", Element.class);
        Element answerBlock = new Element(TAG_NAME)
                .appendChild(new Element(SPAN_TAG)
                        .text(TEXT)
                        .addClass(CORRECT_LABEL_CLASS));

        // when & then
        assertFalse((Boolean) method.invoke(answerParser, answerBlock));
    }

    @Test
    void isAnswerCorrectShouldReturnFalseWhenNoTextOrCorrectLabel() throws Exception {
        // given
        Method method = getPrivateMethod("isAnswerCorrect", Element.class);
        Element answerBlock = new Element(TAG_NAME);

        // when & then
        assertFalse((Boolean) method.invoke(answerParser, answerBlock));
    }

    @Test
    void isAnswerCorrectShouldHandleNullElementGracefully() throws Exception {
        // given
        Method method = getPrivateMethod("isAnswerCorrect", Element.class);

        // when & then
        assertFalse((Boolean) method.invoke(answerParser, new Element(TAG_NAME)));
    }

    private Element createTestContainerWithAnswer(boolean withCorrectLabel) {
        Element answerBlock = new Element(Tag.valueOf(TAG_NAME), BASE_URI)
                .addClass(extractClass(HtmlSelectors.Answer.BLOCK));

        Element answerText = new Element(Tag.valueOf(TAG_NAME), BASE_URI)
                .attr(ATTRIBUTE_KEY, ANSWER_TEXT)
                .text(ANSWER_1_TEXT);

        answerBlock.appendChild(answerText);

        if (withCorrectLabel) {
            Element correctLabel = new Element(Tag.valueOf(SPAN_TAG), BASE_URI)
                    .addClass(extractClass(HtmlSelectors.Answer.IS_CORRECT));
            answerBlock.appendChild(correctLabel);
        }

        Element container = new Element(Tag.valueOf(TAG_NAME), BASE_URI);
        container.appendChild(answerBlock);

        return container;
    }

    private Method getPrivateMethod(String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        Method method = AnswerParser.class.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method;
    }

    private String extractClass(String selector) {
        if (selector.contains(".")) {
            return selector.substring(selector.indexOf('.') + 1);
        }
        return selector;
    }
}