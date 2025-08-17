package com.example.springexam.controller;

import com.example.springexam.service.api.DataBaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminController.class)
class AdminControllerTest {
    private static final String URL = "/api/v1/admin/reset-db";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DataBaseService dataBaseService;

    @Test
    void resetDatabaseShouldReturnStatusNoContent() throws Exception {
        // given
        doNothing().when(dataBaseService).hardReset();

        // when & then
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(dataBaseService, times(1)).hardReset();
    }
}