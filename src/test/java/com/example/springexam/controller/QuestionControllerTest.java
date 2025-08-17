package com.example.springexam.controller;

import com.example.springexam.data.HtmlParsedResponseTestData;
import com.example.springexam.service.api.QuestionService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuestionController.class)
class QuestionControllerTest {
    private static final String URL = "/api/v1/questions";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private QuestionService questionService;

    @Test
    void getAllQuestionsShouldReturnOkStatus() throws Exception {
        var expectedResponse = List.of(HtmlParsedResponseTestData.builder().build().buildHtmlParsedResponse());

        when(questionService.getAllParsedInfo()).thenReturn(expectedResponse);

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(questionService, times(1)).getAllParsedInfo();
    }
}