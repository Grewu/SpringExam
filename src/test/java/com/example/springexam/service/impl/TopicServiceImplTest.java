package com.example.springexam.service.impl;

import com.example.springexam.data.TopicTestData;
import com.example.springexam.model.entity.Topic;
import com.example.springexam.repository.TopicRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TopicServiceImplTest {
    public static final String DB_ERROR = "DB error";
    @Mock
    private TopicRepository topicRepository;
    @InjectMocks
    private TopicServiceImpl topicService;

    @Test
    void createShouldReturnTopic() {
        // given
        Topic expected = TopicTestData.builder().build().buildTopic();
        when(topicRepository.save(expected)).thenReturn(expected);
        // when
        Topic actual = topicService.create(expected);
        // then
        assertEquals(expected, actual);
        verify(topicRepository).save(expected);
    }

    @Test
    void createShouldThrowNullPointerExceptionWhenTopicIsNull() {
        assertThrows(NullPointerException.class, () -> topicService.create(null));
        verify(topicRepository, never()).save(any());
    }

    @Test
    void createShouldThrowIllegalArgumentExceptionWhenNameIsNull() {
        // given
        Topic topic = TopicTestData.builder().withName(null).build().buildTopic();
        // then
        assertThrows(IllegalArgumentException.class, () -> topicService.create(topic));
        verify(topicRepository, never()).save(any());
    }

    @Test
    void createShouldThrowIllegalArgumentExceptionWhenNameIsEmptyString() {
        // given
        Topic topic = TopicTestData.builder().withName(null).build().buildTopic();

        // then
        assertThrows(IllegalArgumentException.class, () -> topicService.create(topic));
        verify(topicRepository, never()).save(any());
    }

    @Test
    void createShouldThrowDataAccessException() {
        // given
        Topic topic = TopicTestData.builder().build().buildTopic();
        when(topicRepository.save(topic)).thenThrow(new DataAccessException(DB_ERROR) {});
        // then
        assertThrows(DataAccessException.class, () -> topicService.create(topic));
        verify(topicRepository).save(topic);
    }

    @Test
    void getAllShouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> topicService.getAll(mock(Pageable.class)));
    }

    @Test
    void getByIdShouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> topicService.getById(1L));
    }

    @Test
    void updateShouldThrowUnsupportedOperationException() {
        Topic topic = TopicTestData.builder().build().buildTopic();
        assertThrows(UnsupportedOperationException.class, () -> topicService.update(1L, topic));
    }

    @Test
    void deleteShouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> topicService.delete(1L));
    }
}