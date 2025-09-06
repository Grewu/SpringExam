package com.example.springexam.controller;

import com.example.springexam.data.QuizTestData;
import com.example.springexam.service.api.QuizService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MoodleExportController.class)
class MoodleExportControllerTest {
    private static final String URL = "/api/v1/exports/moodle-xml";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private QuizService quizService;

    @Test
    void exportToMoodleXmlShouldReturnQuiz() throws Exception {
        var expectedResponse = QuizTestData.builder().build().buildQuiz();
        String testKeyword = "test";

        when(quizService.generateMoodleQuiz(eq(testKeyword))).thenReturn(expectedResponse);

        mockMvc.perform(get(URL)
                        .param("keyword", testKeyword)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isOk());

        verify(quizService, times(1)).generateMoodleQuiz(eq(testKeyword));
    }

    @Test
    void exportToMoodleXmlShouldReturnQuizWithoutKeyword() throws Exception {
        var expectedResponse = QuizTestData.builder().build().buildQuiz();

        when(quizService.generateMoodleQuiz(eq(null))).thenReturn(expectedResponse);

        mockMvc.perform(get(URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isOk());

        verify(quizService, times(1)).generateMoodleQuiz(eq(null));
    }
}