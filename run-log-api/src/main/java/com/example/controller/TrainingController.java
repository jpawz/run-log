package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/add")
    public String addTrainingForm(Training training) {
	return "add";
    }

    @GetMapping("/edit/{id}")
    public String editTrainingForm(@PathVariable("id") int id, Model model) {
	Training training = service.getTraining(id);
	model.addAttribute("training", training);
	return "edit";
    }

    @PostMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Training training, Model model) {
	service.saveTraining(training);

	return "redirect:/index";
    }

    @PostMapping("/add")
    public String addTraining(Training training, Model model) {
	service.saveTraining(training);
	return "redirect:/index";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteTraiing(@PathVariable("id") int id, Model model) {
        Training training = service.getTraining(id);
        service.deleteTraining(training);
        
        return "redirect:/index";
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
