package com.example.controller;

import java.util.List;
import java.util.stream.StreamSupport;

import javax.persistence.EntityNotFoundException;
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
import com.example.repository.TrainingRepository;

@Controller
public class TrainingController {

    private static final String REDIRECT_TO_HOMEPAGE = "redirect:/index";

    @Autowired
    private TrainingRepository repository;

    @Autowired
    private Mapper mapper;

    @GetMapping(value = { "/", "/index" })
    public String index(Model model) {
	List<TrainingDTO> trainings = StreamSupport.stream(repository.findAll().spliterator(), false)
		.map(mapper::toDto)
		.toList();
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

	repository.save(mapper.toTraining(trainingDTO));
	return REDIRECT_TO_HOMEPAGE;
    }

    @GetMapping("/edit/{id}")
    public String updateTrainingForm(@PathVariable("id") int id, Model model) {
	TrainingDTO trainingdto = mapper.toDto(repository.findById(id).orElseThrow(EntityNotFoundException::new));
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

	repository.save(mapper.toTraining(trainingDTO));
	return REDIRECT_TO_HOMEPAGE;
    }

    @GetMapping("/delete/{id}")
    public String deleteTraining(@PathVariable("id") int id, Model model) {
	repository.deleteById(id);
	return REDIRECT_TO_HOMEPAGE;
    }
}
