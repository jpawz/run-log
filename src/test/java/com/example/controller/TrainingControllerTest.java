package com.example.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.domain.Training;
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

    @BeforeEach
    public void setUp() throws Exception {
	repository.deleteAll();
	this.url = "http://localhost:" + port + "/";
    }

    @Test
    void shouldOpenStartPage() throws Exception {
	mockMvc.perform(get(url))
		.andExpect(content().string(containsString("Training")));
    }

    @Test
    void shouldOpenAddNewTrainingPage() throws Exception {
	mockMvc.perform(get(url + "add"))
		.andExpect(content().string(containsString("Add new training")));
    }

    @Test
    void shouldOpenEditTrainingPage() throws Exception {
	Training training = repository.save(new Training(LocalDate.of(2022, 7, 4), 10, 3600, 800, ""));

	mockMvc.perform(get(url + "edit/{id}", training.getId()))
		.andExpect(content().string(containsString("Edit training")));
    }

    @Test
    void shouldAddNewTraining() throws Exception {
	int initialSize = (int) repository.findAll().spliterator().getExactSizeIfKnown();

	mockMvc.perform(post(url + "add")
		.param("date", "2022-07-03")
		.param("distanceInKilometer", "5")
		.param("durationH", "0")
		.param("durationM", "30")
		.param("durationS", "0")
		.param("kCalBurned", "600")
		.param("comment", "")
		.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/index"));

	assertThat(repository.findAll().spliterator().getExactSizeIfKnown()).isEqualTo(initialSize + 1);
    }

    @Test
    void shouldNotAddNewTrainingWithoutDate() throws Exception {
	mockMvc.perform(post(url + "add")
		.param("distanceInKilometer", "5")
		.param("durationH", "0")
		.param("durationM", "30")
		.param("durationS", "0")
		.param("kCalBurned", "600")
		.param("comment", "")
		.contentType(MediaType.APPLICATION_FORM_URLENCODED));

	assertThat(repository.findAll().spliterator().getExactSizeIfKnown()).isZero();
    }

    @Test
    void shouldEditExistingTrainingComment() throws Exception {
	repository.save(new Training(LocalDate.of(2022, 7, 4), 10, 3600, 800, ""));
	Training training = repository.findAll().iterator().next();
	int initialSize = (int) repository.findAll().spliterator().getExactSizeIfKnown();

	mockMvc.perform(post(url + "update/{id}", training.getId())
		.param("id", String.valueOf(training.getId()))
		.param("date", "2022-07-04")
		.param("distanceInKilometer", "7")
		.param("durationH", "1")
		.param("durationM", "0")
		.param("durationS", "0")
		.param("kCalBurned", "800")
		.param("comment", "The comment")
		.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/index"));

	assertThat(repository.findAll().spliterator().getExactSizeIfKnown()).isEqualTo(initialSize);
	assertThat(repository.findById(training.getId()).get().getComment()).isEqualTo("The comment");
    }

    @Test
    void shouldDeleteTraining() throws Exception {
	repository.save(new Training(LocalDate.of(2022, 7, 4), 10, 3600, 800, ""));
	int initialSize = (int) repository.findAll().spliterator().getExactSizeIfKnown();
	Training training = repository.findAll().iterator().next();

	mockMvc.perform(get(url + "delete/{id}", training.getId()))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/index"));

	assertThat(repository.findAll().spliterator().getExactSizeIfKnown()).isEqualTo(initialSize - 1);
    }
}
