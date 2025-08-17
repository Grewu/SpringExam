package com.example.springexam.service.converter;

import com.example.springexam.data.AnswerTestData;
import com.example.springexam.data.QuestionTestData;
import com.example.springexam.data.xml.MoodleQuestionTestData;
import com.example.springexam.data.xml.TopicTestData;
import com.example.springexam.service.api.AnswerService;
import com.example.springexam.service.api.ExplanationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoodleQuestionConverterImplTest {
    @Mock
    private  AnswerService answerService;
    @Mock
    private  ExplanationService explanationService;
    @InjectMocks
    private MoodleQuestionConverterImpl moodleQuestionConverter;

    @Test
    void convertShouldReturnMoodleQuestion() {
        //given
        var expected = MoodleQuestionTestData.builder().build().buildMoodleQuestion();
        var answerList = List.of(AnswerTestData.builder().build().buildAnswer());
        var question = QuestionTestData.builder().build().buildQuestion();

        when(answerService.getAnswerByQuestionId(question.getId())).thenReturn(answerList);
        when(explanationService.getExplanationContent(question.getId())).thenReturn(any(String.class));

        //when
        var actual = moodleQuestionConverter.convert(question);
        //then
        assertEquals(expected,actual);
    }

    @Test
    void convertShouldReturnNullWhenQuestionIsNull() {
        // when
        var result = moodleQuestionConverter.convert(null);

        // then
        assertNull(result);
        verifyNoInteractions(answerService);
        verifyNoInteractions(explanationService);

    }

    @Test
    void convertShouldReturnNullWhenAnswerIsNull() {
        //given
        var question = QuestionTestData.builder().build().buildQuestion();
        when(answerService.getAnswerByQuestionId(question.getId())).thenReturn(null);
        // when
        var actual = moodleQuestionConverter.convert(question);

        // then
        assertNull(actual);
        verifyNoInteractions(explanationService);

    }

    @Test
    void convertShouldReturnMoodleQuestionWithEmptyTopic() {
        //given
        var expected = MoodleQuestionTestData.builder()
                .withTopic(TopicTestData.builder().build().buildEmptyTopic())
                .build().buildMoodleQuestion();
        var answerList = List.of(AnswerTestData.builder().build().buildAnswer());
        var question = QuestionTestData.builder()
                .withTopic(null)
                .build()
                .buildQuestion();

        when(answerService.getAnswerByQuestionId(question.getId())).thenReturn(answerList);
        when(explanationService.getExplanationContent(question.getId())).thenReturn(any(String.class));
        // when
        var actual = moodleQuestionConverter.convert(question);

        // then
        assertEquals(expected,actual);

    }
}