package com.example.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.mapper.Mapper;
import com.example.service.TrainingService;

@WebMvcTest
class TrainingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainingService mockService;

    @MockBean
    private Mapper spyMapper;

    @Test
    void shouldOpenStartPage() throws Exception {
	this.mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Trainings")));
    }

    @Test
    void shouldOpenAddNewTrainingPage() throws Exception {
	this.mockMvc.perform(get("/add"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Date")));
    }
}
