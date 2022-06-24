package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Training;
import com.example.repository.TrainingRepository;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository repository;

    public Training saveTraining(Training training) {
	return this.repository.save(training);
    }

}
