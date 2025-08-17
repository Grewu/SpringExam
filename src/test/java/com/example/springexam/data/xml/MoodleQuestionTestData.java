package com.example.springexam.data.xml;

import com.example.springexam.model.dto.response.xml.*;
import lombok.Builder;

import java.util.List;

@Builder(setterPrefix = "with")
public class MoodleQuestionTestData {
    @Builder.Default private String type = "multichoice";
    @Builder.Default private boolean single = Boolean.FALSE;
    @Builder.Default private Name name = NameTestData.builder().build().buildName();
    @Builder.Default private Text questionText = TextTestData.builder().build().buildText();
    @Builder.Default private List<MoodelAnswer> answers = List.of(MoodelAnswerTestData.builder().build().buildMoodelAnswer());
    @Builder.Default private GeneralFeedback generalfeedback = GeneralFeedbackTestData.builder().build().buildGeneralFeedback();
    @Builder.Default private Topic topic = TopicTestData.builder().build().buildTopic();

    public MoodleQuestion buildMoodleQuestion() {
        return MoodleQuestion.builder()
                .type(type)
                .single(single)
                .name(name)
                .questionText(questionText)
                .answers(answers)
                .generalfeedback(generalfeedback)
                .topic(topic)
                .build();
    }


}