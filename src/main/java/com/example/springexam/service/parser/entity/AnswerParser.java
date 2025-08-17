package com.example.springexam.service.parser.entity;

import com.example.springexam.model.entity.Answer;
import com.example.springexam.model.entity.Question;
import com.example.springexam.service.api.AnswerService;
import com.example.springexam.service.parser.api.EntityParser;
import com.example.springexam.utils.HtmlSelectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerParser implements EntityParser<List<Answer>, Question> {

    private static final String CORRECT_LABEL_1 = "Your selection is correct";
    private static final String CORRECT_LABEL_2 = "Correct selection";

    private final AnswerService answerService;

    @Override
    @Transactional
    public List<Answer> parseAndSave(Element container, Question question) {
        Objects.requireNonNull(container, "Container element cannot be null");
        Objects.requireNonNull(question, "Question cannot be null");
        List<Answer> answers = buildAnswers(container, question);
        return answerService.createAll(answers);
    }

    private List<Answer> buildAnswers(Element container, Question question) {
        log.debug("Parsing answers for question: {}", question.getId());
        List<Answer> answers = container.select(HtmlSelectors.Answer.BLOCK).stream()
                .map(answerBlock -> extractAnswer(answerBlock, question))
                .filter(Objects::nonNull)
                .toList();
        if (answers.isEmpty()) {
            log.warn("No answers found for question: {}", question.getId());
        }
        return answers;
    }


    private Answer extractAnswer(Element answerBlock, Question question) {
        String text = extractAnswerText(answerBlock);
        return text != null && !text.isEmpty()
                ? buildAnswer(text, question, isAnswerCorrect(answerBlock))
                : null;
    }

    private boolean isAnswerCorrect(Element answerBlock) {

        Element textElement = answerBlock.selectFirst(HtmlSelectors.Answer.TEXT);
        if (textElement != null) {
            return true;
        }
        return Optional.ofNullable(answerBlock.selectFirst(HtmlSelectors.Answer.IS_CORRECT))
                .map(Element::text)
                .map(String::trim)
                .filter(this::isValidCorrectLabel)
                .isPresent();

    }

    private boolean isValidCorrectLabel(String labelText) {
        return CORRECT_LABEL_1.equals(labelText) || CORRECT_LABEL_2.equals(labelText);
    }

    private String extractAnswerText(Element answerBlock) {
        return Optional.ofNullable(answerBlock)
                .map(block -> block.selectFirst(HtmlSelectors.Answer.ANSWER_ID))
                .map(Element::text)
                .map(String::trim)
                .map(text -> Normalizer.normalize(text, Normalizer.Form.NFC))
                .filter(text -> !text.isEmpty())
                .orElseGet(() -> {
                    log.warn("No valid answer text found in block: {}", answerBlock);
                    return null;
                });
    }

    private Answer buildAnswer(String answerText, Question question, boolean isCorrect) {
        return Answer.builder()
                .question(question)
                .answerText(answerText)
                .isCorrect(isCorrect)
                .build();
    }
}