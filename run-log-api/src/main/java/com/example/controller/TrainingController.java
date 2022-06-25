package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.domain.Training;
import com.example.domain.TrainingDTO;
import com.example.mapper.Mapper;
import com.example.service.TrainingService;

@Controller
public class TrainingController {

    @Autowired
    private TrainingService service;

    @Autowired
    Mapper mapper;

    @GetMapping(value = { "/", "/index" })
    public String index(Model model) {
	List<TrainingDTO> trainings = StreamSupport.stream(service.getAllTrainings().spliterator(), false)
		.map(mapper::toDto)
		.collect(Collectors.toList());
	model.addAttribute("trainings", trainings);
	return "index";
    }

    @GetMapping("/add")
    public String addTrainingdtoForm(TrainingDTO trainingdto, Model model) {
	model.addAttribute("trainingdto", trainingdto);
	return "add";
    }

    @GetMapping("/edit/{id}")
    public String editTrainingForm(@PathVariable("id") int id, Model model) {
	TrainingDTO trainingdto = mapper.toDto(service.getTraining(id));
	model.addAttribute("trainingdto", trainingdto);
	return "edit";
    }

    @PostMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, TrainingDTO trainingdto, Model model) {
	service.saveTraining(mapper.toTraining(trainingdto));

	return "redirect:/index";
    }

    @PostMapping("/add")
    public String addTrainingdto(TrainingDTO trainingdto, Model model) {
	service.saveTraining(mapper.toTraining(trainingdto));
	return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteTraiing(@PathVariable("id") int id, Model model) {
	Training training = service.getTraining(id);
	service.deleteTraining(training);

	return "redirect:/index";
    }
}
