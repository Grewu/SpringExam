package com.example.springexam.service.parser;

import com.example.springexam.model.dto.response.html.HtmlParsedResponse;
import com.example.springexam.service.loader.api.DocumentLoader;
import com.example.springexam.service.parser.api.ParseService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamParserServiceTest {

    private static final String QUESTION_CONTAINER_HTML = "<div class=\"question-container\">%s</div>";
    private static final String QUESTION_1 = "Question 1";
    private static final String QUESTION_2 = "Question 2";
    private static final String QUESTION_3 = "Question 3";
    private static final String HTML = "<div>No questions</div>";

    @Mock
    private DocumentLoader documentLoader;
    @Mock
    private ParseService<HtmlParsedResponse, Element> htmlParser;

    @InjectMocks
    private ExamParserService examParserService;

    private Document mockDocument;

    @BeforeEach
    void setUp() {
        mockDocument = Jsoup.parse(String.format(QUESTION_CONTAINER_HTML, QUESTION_1));
    }

    @Test
    void shouldReturnEmptyListWhenNoDocumentsLoaded() {
        //given
        when(documentLoader.loadAllDocuments()).thenReturn(Collections.emptyList());

        //when
        var result = examParserService.parse();

        //then
        assertTrue(result.isEmpty());
        verify(documentLoader).loadAllDocuments();
        verifyNoInteractions(htmlParser);
    }

    @Test
    void shouldReturnParsedResponsesWhenDocumentsContainQuestionContainers() {
        //given
        var documents = List.of(mockDocument);
        when(documentLoader.loadAllDocuments()).thenReturn(documents);

        //when
        var result = examParserService.parse();

        //then
        assertEquals(0, result.size());
        verify(documentLoader).loadAllDocuments();
    }

    @Test
    void shouldProcessAllContainersWhenMultipleDocumentsWithMultipleContainers() {
        //given
        var doc1 = Jsoup.parse(
                String.format(QUESTION_CONTAINER_HTML, QUESTION_1) +
                        String.format(QUESTION_CONTAINER_HTML, QUESTION_2)
        );
        var doc2 = Jsoup.parse(String.format(QUESTION_CONTAINER_HTML, QUESTION_3));
        var documents = List.of(doc1, doc2);
        when(documentLoader.loadAllDocuments()).thenReturn(documents);

        //when
        var result = examParserService.parse();

        //then
        assertEquals(0, result.size());
        verify(documentLoader).loadAllDocuments();
    }

    @Test
    void shouldReturnEmptyListWhenNoQuestionContainersFound() {
        //given
        var emptyDoc = Jsoup.parse(HTML);
        when(documentLoader.loadAllDocuments()).thenReturn(List.of(emptyDoc));

        //when
        var result = examParserService.parse();

        //then
        assertTrue(result.isEmpty());
        verify(documentLoader).loadAllDocuments();
        verifyNoInteractions(htmlParser);
    }
}