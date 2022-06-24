package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Training;
import com.example.service.TrainingService;

@RestController
@RequestMapping("api")
public class TrainingController {

    @Autowired
    private TrainingService service;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Training addTraining(Training training) {
	return service.saveTraining(training);
    }
}
