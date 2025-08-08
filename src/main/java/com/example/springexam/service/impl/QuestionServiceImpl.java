package com.example.springexam.service.impl;

import com.example.springexam.exception.EntityAlreadyExistsException;
import com.example.springexam.model.dto.response.ParsedInfo;
import com.example.springexam.model.entity.Answer;
import com.example.springexam.model.entity.Question;
import com.example.springexam.model.xml.GeneralFeedback;
import com.example.springexam.model.xml.MoodelAnswer;
import com.example.springexam.model.xml.MoodleQuestion;
import com.example.springexam.model.xml.Name;
import com.example.springexam.model.xml.Quiz;
import com.example.springexam.model.xml.Text;
import com.example.springexam.model.xml.Topic;
import com.example.springexam.repository.QuestionRepository;
import com.example.springexam.service.api.AnswerService;
import com.example.springexam.service.api.ExplanationService;
import com.example.springexam.service.api.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerService answerService;
    private final ExplanationService explanationService;

    @Override
    public Question create(Question question) {
        if (questionRepository.existsByQuestionText(question.getQuestionText())) {
            throw new EntityAlreadyExistsException(Question.class, question.getQuestionText());
        }
        return questionRepository.save(question);
    }

    @Override
    public Page<Question> getAll(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @Override
    public Question getById(Long id) {
        throw new UnsupportedOperationException("getById not implemented yet");
    }

    @Override
    public Question update(Long id, Question  question) {
        throw new UnsupportedOperationException("update not implemented yet");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("delete not implemented yet");
    }

    public List<ParsedInfo> getAllParsedInfo() {
        return questionRepository.findAll()
                .stream()
                .map(this::toParsedInfo)
                .toList();
    }

    @Override
    public Quiz getAllMoodleQuiz() {
        List<MoodleQuestion> moodelQuestions = questionRepository.findAll()
                .stream()
                .map(this::toMoodelQuestion)
                .toList();
        return Quiz.builder()
                .question(moodelQuestions)
                .build();
    }

    private MoodleQuestion toMoodelQuestion(Question question) {
        List<Answer> answers = answerService.getAnswerByQuestionId(question.getId());

        List<MoodelAnswer> moodleAnswers = answers.stream()
                .map(answer -> MoodelAnswer.builder()
                        .fraction(answer.getIsCorrect() ? 100 : 0)
                        .text(answer.getAnswerText())
                        .build())
                .toList();

        return MoodleQuestion.builder()
                .type("multichoice")
                .name(moodelName())
                .questionText(moodelQuestionText(question))
                .answers(moodleAnswers)
                .generalFeedback(moodelGeneralFeedback(question))
                .topic(moodelTopic(question))
                .build();
    }

    private Topic moodelTopic(Question question) {
        return Topic.builder()
                .topic(question.getTopic().getName().name())
                .build();
    }

    private Name moodelName() {
        return Name.builder()
                .text("name text")
                .build();
    }

    private Text moodelQuestionText(Question question) {
        return Text.builder()
                .format("html")
                .text(question.getQuestionText())
                .build();
    }

    private GeneralFeedback moodelGeneralFeedback(Question question) {
        return GeneralFeedback.builder()
                .text(explanationService.getExplanationContent(question.getId()))
                .build();
    }

    private ParsedInfo toParsedInfo(Question question) {
        return ParsedInfo.builder()
                .question(question.getQuestionText())
                .answer(answerService.getAnswerTextsByQuestionId(question.getId()))
                .explanation(explanationService.getExplanationContent(question.getId()))
                .topic(question.getTopic().getName().name())
                .build();
    }
}
