package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Training;
import com.example.service.TrainingService;

@Controller
public class TrainingController {

    @Autowired
    private TrainingService service;

    @GetMapping(value = { "/", "/index" })
    public String index(Model model) {
	model.addAttribute("trainings", service.getAllTrainings());
	return "index";
    }
    
    @GetMapping("edit")
    public String addTraining(Training training, Model model) {
	service.saveTraining(training);
	return "edit";
    }

    @GetMapping(value = "/trainings/{id}")
    public Training geTraining(@PathVariable("id") int id) {
	return this.service.getTraining(id);
    }

    @GetMapping(value = "/trainings/all")
    public Iterable<Training> getAllTrainings() {
	return service.getAllTrainings();
    }
}
