package com.example.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.example.repository.TrainingRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TrainingControllerTest {

    private String url;

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrainingRepository repository;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
	this.url = "http://localhost:" + port + "/";
    }

    @Test
    void shouldOpenStartPage() {
	ResponseEntity<String> response = template.getForEntity(url, String.class);
	assertThat(response.getBody()).contains("Trainings");
    }

    @Test
    void shouldOpenAddNewTrainingPage() {
	ResponseEntity<String> response = template.getForEntity(url + "add", String.class);
	assertThat(response.getBody()).contains("Add new training");
    }

    @Test
    void shouldAddNewTraining() throws Exception {
	mockMvc.perform(post(url + "add")
		.param("date", "2022-07-03")
		.param("distanceInKilometer", "5")
		.param("durationH", "0")
		.param("durationM", "30")
		.param("durationS", "0")
		.param("kCalBurned", "600")
		.param("comment", "")
		.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(redirectedUrl("/index"))
		.andExpect(status().is3xxRedirection());

	assertThat(repository.findAll().spliterator().getExactSizeIfKnown()).isEqualTo(1);
    }
}
