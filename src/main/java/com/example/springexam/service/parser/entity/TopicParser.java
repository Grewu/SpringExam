package com.example.springexam.service.parser.entity;

import com.example.springexam.model.entity.Topic;
import com.example.springexam.model.entity.enums.TopicType;
import com.example.springexam.service.api.TopicService;
import com.example.springexam.service.parser.api.EntityParser;
import com.example.springexam.utils.HtmlSelectors;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicParser implements EntityParser<Topic, Void> {
    private final TopicService topicService;

    @Override
    public Topic parseAndSave(Element container, Void unused) {
        return topicService.create(buildTopic(container));
    }

    private Topic buildTopic(Element container) {
        return Topic.builder()
                .name(TopicType.fromDisplayName(extractTopicText(container)))
                //TODO ?  что такое  description
                .description("description")
                .build();
    }

    private String extractTopicText(Element container) {
        return Optional.ofNullable(container.selectFirst(HtmlSelectors.Topic.PANE))
                .map(pane -> pane.selectFirst(HtmlSelectors.Topic.NAME))
                .map(Element::text)
                .orElse("Topic not found");
    }
}