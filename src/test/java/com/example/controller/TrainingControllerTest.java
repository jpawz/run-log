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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.domain.Training;
import com.example.repository.TrainingRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TrainingControllerTest {

    private String url;
    private MultiValueMap<String, String> params;

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrainingRepository repository;

    @BeforeEach
    public void setUp() throws Exception {
	params = new LinkedMultiValueMap<>();
	params.add("date", "2022-07-03");
	params.add("distanceInKilometers", "5");
	params.add("durationHours", "0");
	params.add("durationMinutes", "30");
	params.add("durationSeconds", "0");
	params.add("kCalBurned", "600");
	params.add("comment", "");

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

	mockMvc.perform(post(url + "add").params(params)
		.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/index"));

	assertThat(repository.findAll().spliterator().getExactSizeIfKnown()).isEqualTo(initialSize + 1);
    }

    @Test
    void shouldAddNewTrainingWithFloatingPointDistance() throws Exception {
	int initialSize = (int) repository.findAll().spliterator().getExactSizeIfKnown();

	params.set("distanceInKilometer", "5.02");

	mockMvc.perform(post(url + "add")
		.params(params)
		.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/index"));

	assertThat(repository.findAll().spliterator().getExactSizeIfKnown()).isEqualTo(initialSize + 1);
    }

    @Test
    void shouldNotAddNewTrainingWithoutDate() throws Exception {
	params.remove("date");

	mockMvc.perform(post(url + "add")
		.params(params)
		.contentType(MediaType.APPLICATION_FORM_URLENCODED));

	assertThat(repository.findAll().spliterator().getExactSizeIfKnown()).isZero();
    }

    @Test
    void shouldEditExistingTrainingComment() throws Exception {
	repository.save(new Training(LocalDate.of(2022, 7, 4), 10, 3600, 800, ""));
	Training training = repository.findAll().iterator().next();
	int initialSize = (int) repository.findAll().spliterator().getExactSizeIfKnown();
	params.add("id", String.valueOf(training.getId()));
	params.set("comment", "The comment");

	mockMvc.perform(post(url + "update/{id}", training.getId())
		.params(params)
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
