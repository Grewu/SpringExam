package com.example.springexam.data.xml;

import com.example.springexam.model.dto.response.xml.Topic;
import com.example.springexam.model.entity.enums.TopicType;
import lombok.Builder;

@Builder(setterPrefix = "with")
public class TopicTestData {
    @Builder.Default private String topic = TopicType.SPRING_CORE.name() ;

    public Topic buildTopic() {
        return Topic.builder()
                .topic(topic)
                .build();
    }

    public Topic buildEmptyTopic() {
        return builder()
                .withTopic("")
                .build()
                .buildTopic();
    }

}