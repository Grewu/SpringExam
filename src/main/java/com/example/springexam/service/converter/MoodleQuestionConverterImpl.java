package com.example.springexam.service.converter;

import com.example.springexam.model.dto.response.xml.GeneralFeedback;
import com.example.springexam.model.dto.response.xml.MoodelAnswer;
import com.example.springexam.model.dto.response.xml.MoodleQuestion;
import com.example.springexam.model.dto.response.xml.Name;
import com.example.springexam.model.dto.response.xml.Text;
import com.example.springexam.model.dto.response.xml.Topic;
import com.example.springexam.model.entity.Answer;
import com.example.springexam.model.entity.Question;
import com.example.springexam.service.api.AnswerService;
import com.example.springexam.service.api.ExplanationService;
import com.example.springexam.service.converter.api.MoodleQuestionConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MoodleQuestionConverterImpl implements MoodleQuestionConverter {

    private static final String QUESTION_TYPE = "multichoice";
    private static final String TEXT_FORMAT = "html";
    private static final String DEFAULT_NAME = "Question";
    private static final int CORRECT_FRACTION = 100;
    private static final int INCORRECT_FRACTION = 0;

    private final AnswerService answerService;
    private final ExplanationService explanationService;

    @Override
    @Transactional(readOnly = true)
    public MoodleQuestion convert(Question question) {
        if (question == null) {
            log.warn("Question is null, cannot convert");
            return null;
        }
        log.debug("Converting question with ID: {}", question.getId());

        List<Answer> answers = answerService.getAnswerByQuestionId(question.getId());
        if (answers == null) {
            log.warn("No answers found for question ID: {}", question.getId());
            return null;
        }

        return MoodleQuestion.builder()
                .type(QUESTION_TYPE)
                .single(hasSingleCorrectAnswer(answers))
                .name(buildName())
                .questionText(buildQuestionText(question))
                .answers(convertAnswers(answers))
                .generalfeedback(buildGeneralFeedback(question))
                .topic(buildTopic(question))
                .build();
    }

    private List<MoodelAnswer> convertAnswers(List<Answer> answers) {
        return answers.stream()
                .filter(Objects::nonNull)
                .map(this::convertAnswer)
                .toList();
    }

    private MoodelAnswer convertAnswer(Answer answer) {
        String text = answer.getAnswerText() != null ? answer.getAnswerText() : "";
        return MoodelAnswer.builder()
                .fraction(answer.getIsCorrect() ? CORRECT_FRACTION : INCORRECT_FRACTION)
                .text(text)
                .build();
    }


    private boolean hasSingleCorrectAnswer(List<Answer> answers) {
        return answers.stream()
                .filter(Objects::nonNull)
                .filter(Answer::getIsCorrect)
                .count() == 1;
    }


    private Name buildName() {
        return Name.builder()
                .text(DEFAULT_NAME)
                .build();
    }


    private Text buildQuestionText(Question question) {
        String text = question.getQuestionText() != null ? question.getQuestionText() : "";
        return Text.builder()
                .format(TEXT_FORMAT)
                .text(text)
                .build();
    }

    private GeneralFeedback buildGeneralFeedback(Question question) {
        String feedback = explanationService.getExplanationContent(question.getId());
        if (feedback == null) {
            log.warn("No explanation found for question ID: {}", question.getId());
            feedback = "";
        }
        return GeneralFeedback.builder()
                .text(feedback)
                .build();
    }


    private Topic buildTopic(Question question) {
        String topicName = question.getTopic() != null && question.getTopic().getName() != null
                ? question.getTopic().getName().name() : "";
        if (topicName.isEmpty()) {
            log.warn("No topic found for question ID: {}", question.getId());
        }
        return Topic.builder()
                .topic(topicName)
                .build();
    }
}