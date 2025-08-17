package com.example.springexam.controller;

import com.example.springexam.data.HtmlParsedResponseTestData;
import com.example.springexam.service.parser.ExamParserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImportController.class)
class ImportControllerTest {
    private static final String URL = "/api/v1/import";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ExamParserService examParserService;

    @Test
    void parseAndImportShouldReturnListOfHtmlParsedResponse() throws Exception {
        // given
        var expectedResponse = List.of(HtmlParsedResponseTestData.builder().build().buildHtmlParsedResponse());
        when(examParserService.parse()).thenReturn(expectedResponse);

        // when & then
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(examParserService, times(1)).parse();
    }
}