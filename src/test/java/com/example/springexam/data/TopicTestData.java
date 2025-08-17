package com.example.springexam.data;

import com.example.springexam.model.entity.Topic;
import com.example.springexam.model.entity.enums.TopicType;
import lombok.Builder;

@Builder(setterPrefix = "with")
public class TopicTestData {
    @Builder.Default private Long id = 1L;
    @Builder.Default private TopicType name = TopicType.SPRING_CORE;
    @Builder.Default private String description = "description";
    @Builder.Default private String displayName = "Spring Core";

    public Topic buildTopic() {
        return Topic.builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
    }

    public Topic buildTopicForHtml() {
        return Topic.builder()
                .id(id)
                .name(TopicType.fromDisplayName(displayName))
                .description(description)
                .build();
    }



}