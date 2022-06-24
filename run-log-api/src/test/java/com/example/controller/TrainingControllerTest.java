package com.example.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
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
	url = String.format("http://localhost:%d/api", port);
    }

    @Test
    void whenPostNewTraining_thenReturn201Status() {
	Training training = new Training(LocalDate.now(), 5, Duration.ofMinutes(30), 500, "Bitten by a dog.");

	ResponseEntity<Training> response = restTemplate.postForEntity(url, training, Training.class);

	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void whenGetExistingTraining_thenReturn200Status() {
	Training training = new Training(LocalDate.now(), 5, Duration.ofMinutes(30), 500, "Bitten by a dog.");
	ResponseEntity<Training> persistedTraining = restTemplate.postForEntity(url, training, Training.class);

	ResponseEntity<Training> response = restTemplate
		.getForEntity(url + "/trainings/" + persistedTraining.getBody().getId(), Training.class);

	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void whenGetAllTrainings_thenReturnTwoEntities() {
	Training training1 = new Training(LocalDate.now(), 5, Duration.ofMinutes(30), 500, "Bitten by a dog.");
	Training training2 = new Training(LocalDate.now(), 10, Duration.ofMinutes(45), 1200,
		"I got lost in the woods.");
	restTemplate.postForEntity(url, training1, Training.class);
	restTemplate.postForEntity(url, training2, Training.class);

	ResponseEntity<Training[]> response = restTemplate.getForEntity(url + "/trainings/all", Training[].class);

	assertThat(response.getBody()).hasSize(2);
    }

}
