package com.example.springexam.service.parser.entity;

import com.example.springexam.data.TopicTestData;
import com.example.springexam.model.entity.Topic;
import com.example.springexam.model.entity.enums.TopicType;
import com.example.springexam.service.api.TopicService;
import com.example.springexam.utils.HtmlSelectors;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TopicParserTest {

    public static final String CONTAINER_CANNOT_BE_NULL = "Container cannot be null";
    @Mock
    private TopicService topicService;
    @Mock
    private Element container;
    @Mock
    private Element pane;
    @Mock
    private Element nameElem;

    @InjectMocks
    private TopicParser topicParser;

    @Test
    void parseAndSaveShouldReturnSavedTopicWhenValidContainer() {
        // given
        when(container.selectFirst(HtmlSelectors.Topic.PANE)).thenReturn(pane);
        when(pane.selectFirst(HtmlSelectors.Topic.NAME)).thenReturn(nameElem);
        when(nameElem.text()).thenReturn("Spring Core");

        var expected = TopicTestData.builder().build().buildTopic();

        when(topicService.create(ArgumentMatchers.argThat(t ->
                t.getName() == TopicType.SPRING_CORE &&
                        t.getDescription().equals("description") &&
                        t.getId() == null
        ))).thenReturn(expected);

        // when
        var actual = topicParser.parseAndSave(container, null);

        // then
        assertEquals(expected, actual);
        verify(topicService).create(any(Topic.class));
    }

    @Test
    void parseAndSaveShouldThrowNullPointerExceptionWhenContainerIsNull() {
        // when & then
        var exception = assertThrows(NullPointerException.class,
                () -> topicParser.parseAndSave(null, null));
        assertEquals(CONTAINER_CANNOT_BE_NULL, exception.getMessage());
        verifyNoInteractions(topicService);
    }

    @Test
    void parseAndSaveShouldFallbackToFilenameWhenTopicPaneNotFound() {
        // given
        var fileName = "docker.html";
        when(container.selectFirst(HtmlSelectors.Topic.PANE)).thenReturn(null);

        var expected = TopicTestData.builder().build().buildTopic();

        when(topicService.create(ArgumentMatchers.argThat(t ->
                t.getName() == TopicType.DOCKER &&
                        t.getDescription().equals("description") &&
                        t.getId() == null
        ))).thenReturn(expected);

        // when
        var actual = topicParser.parseAndSave(container, fileName);

        // then
        assertEquals(expected, actual);
        verify(topicService).create(any(Topic.class));
    }

    @Test
    void parseAndSaveShouldFallbackToFilenameWhenTopicNameNotFound() {
        // given
        var fileName = "oracle.html";
        when(container.selectFirst(HtmlSelectors.Topic.PANE)).thenReturn(pane);
        when(pane.selectFirst(HtmlSelectors.Topic.NAME)).thenReturn(null);

        var expected = TopicTestData.builder().build().buildTopic();

        when(topicService.create(ArgumentMatchers.argThat(t ->
                t.getName() == TopicType.SQL &&
                        t.getDescription().equals("description") &&
                        t.getId() == null
        ))).thenReturn(expected);

        // when
        var actual = topicParser.parseAndSave(container, fileName);

        // then
        assertEquals(expected, actual);
        verify(topicService).create(any(Topic.class));
    }
}