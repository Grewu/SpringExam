package com.example.springexam.service.parser.entity;

import com.example.springexam.model.entity.Topic;
import com.example.springexam.model.entity.enums.TopicType;
import com.example.springexam.service.api.TopicService;
import com.example.springexam.service.parser.api.EntityParser;
import com.example.springexam.utils.HtmlSelectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicParser implements EntityParser<Topic, String> {
    private final TopicService topicService;

    @Override
    @Transactional
    public Topic parseAndSave(Element container, String fileName) {
        Objects.requireNonNull(container, "Container cannot be null");
        log.warn("Received file name: '{}'", fileName);

        String topicName = extractTopicText(container, fileName);
        TopicType topicType = determineTopicType(topicName, fileName);

        log.debug("Parsed topic '{}' from file: {}", topicType.getDisplayName(), fileName);

        return topicService.create(
                Topic.builder()
                        .name(topicType)
                        .description("description")
                        .build()
        );
    }

    private TopicType determineTopicType(String topicName, String fileName) {
        TopicType topicType = TopicType.fromDisplayName(topicName);

        if (topicType != null) {
            return topicType;
        }

        log.warn("Failed to determine topic type from '{}' (file: {}), defaulting to SQL",
                topicName, fileName);

        return determineTopicFromFilename(fileName);
    }

    private String extractTopicText(Element container, String fileName) {
        String htmlTopic = Optional.ofNullable(container.selectFirst(HtmlSelectors.Topic.PANE))
                .map(pane -> pane.selectFirst(HtmlSelectors.Topic.NAME))
                .map(Element::text)
                .orElse(null);


        if (htmlTopic != null && !htmlTopic.trim().isEmpty()) {
            return htmlTopic;
        }

        return createTopicFromFilename(fileName);
    }

    private String createTopicFromFilename(String filename) {
        return filename.replace(".html", "")
                .replace("-", " ")
                .trim();
    }

    private TopicType determineTopicFromFilename(String filename) {
        String lowerFilename = filename.toLowerCase();

        if (lowerFilename.contains("docker")) {
            return TopicType.DOCKER;
        } else if (lowerFilename.contains("oracle")) {
            return TopicType.SQL;
        } else {
            return TopicType.SQL;
        }
    }

}