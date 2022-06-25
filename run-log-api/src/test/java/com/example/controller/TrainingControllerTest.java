package com.example.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.domain.Training;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class TrainingControllerTest {

    @LocalServerPort
    private int port;

    private String url;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {
	url = String.format("http://localhost:%d/", port);
    }

    @Test
    void whenPostNewTraining_thenRedirect() {
	Training training = new Training(LocalDate.now(), 5, 1, 500, "Bitten by a dog.");

	ResponseEntity<Training> response = restTemplate.postForEntity(url+"/add", training, Training.class);

	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
    }
}
