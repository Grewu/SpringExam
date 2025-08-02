package com.example.springexam.service.parser.entity;

import com.example.springexam.model.entity.Answer;
import com.example.springexam.model.entity.Question;
import com.example.springexam.service.api.AnswerService;
import com.example.springexam.service.parser.api.EntityParser;
import com.example.springexam.utils.HtmlSelectors;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerParser implements EntityParser<List<Answer>, Question> {

    private final AnswerService answerService;

    @Override
    public List<Answer> parseAndSave(Element container, Question question) {
        return answerService.createAll(buildAnswers(container, question));
    }

    private List<Answer> buildAnswers(Element container, Question question) {
        return container.select(HtmlSelectors.Answer.BLOCK).stream()
                .map(block -> {
                    String text = extractAnswerText(block);
                    return text != null && !text.isEmpty()
                            ? buildAnswer(text, question, isAnswerCorrect(block))
                            : null;
                })
                .filter(Objects::nonNull)
                .toList();
    }

    private boolean isAnswerCorrect(Element answerBlock) {
        return isCorrectBySelector(answerBlock) || isCorrectByLabel(answerBlock);
    }

    private boolean isCorrectBySelector(Element answerBlock) {
        return answerBlock.selectFirst(HtmlSelectors.Answer.TEXT) != null;
    }

    private boolean isCorrectByLabel(Element answerBlock) {
        return Optional.ofNullable(answerBlock.selectFirst(HtmlSelectors.Answer.IS_CORRECT))
                .map(Element::text)
                .map(String::trim)
                .filter(this::isValidCorrectLabel)
                .isPresent();
    }

    private boolean isValidCorrectLabel(String labelText) {
        return labelText.equals("Your selection is correct")
                || labelText.equals("Correct selection");
    }

    private String extractAnswerText(Element answerBlock) {
        return Optional.ofNullable(answerBlock.selectFirst(HtmlSelectors.Answer.ANSWER_ID))
                .map(Element::text)
                .map(String::trim)
                .orElse(null);
    }

    private Answer buildAnswer(String answerText, Question question, boolean isCorrect) {
        return Answer.builder()
                .question(question)
                .answerText(answerText)
                .isCorrect(isCorrect)
                .build();
    }
}