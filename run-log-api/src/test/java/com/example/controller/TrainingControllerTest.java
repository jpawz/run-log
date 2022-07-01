package com.example.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TrainingControllerTest {

    @LocalServerPort
    private int port;

    private String url;

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
	assertThat(response.getBody()).contains("Date:");
    }
}
