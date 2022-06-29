package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.domain.TrainingDTO;
import com.example.mapper.Mapper;
import com.example.service.TrainingService;

@Controller
public class TrainingController {

    @Autowired
    private TrainingService service;

    @Autowired
    private Mapper mapper;

    @GetMapping(value = { "/", "/index" })
    public String index(Model model) {
	List<TrainingDTO> trainings = StreamSupport.stream(service.getAllTrainings().spliterator(), false)
		.map(mapper::toDto)
		.collect(Collectors.toList());
	model.addAttribute("trainings", trainings);
	return "index";
    }

    @GetMapping("/add")
    public String addTrainingForm(TrainingDTO trainingdto, Model model) {
	model.addAttribute("trainingDTO", trainingdto);
	return "add";
    }

    @PostMapping("/add")
    public String addNewTraining(@Valid TrainingDTO trainingDTO, BindingResult bindingResult, Model model) {
	if (bindingResult.hasErrors()) {
	    model.addAttribute("trainingDTO", trainingDTO);
	    return "add";
	}

	service.saveTraining(mapper.toTraining(trainingDTO));
	return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String updateTrainingForm(@PathVariable("id") int id, Model model) {
	TrainingDTO trainingdto = mapper.toDto(service.getTraining(id));
	model.addAttribute("trainingDTO", trainingdto);
	return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateTraining(@PathVariable("id") int id, @Valid TrainingDTO trainingDTO,
	    BindingResult bindingResult, Model model) {
	if (bindingResult.hasErrors()) {
	    model.addAttribute("trainingDTO", trainingDTO);
	    return "edit";
	}

	service.saveTraining(mapper.toTraining(trainingDTO));
	return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteTraining(@PathVariable("id") int id, Model model) {
	service.deleteTrainingById(id);
	return "redirect:/index";
    }
}
