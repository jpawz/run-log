package com.example.service;

import javax.persistence.EntityNotFoundException;

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

    public Training getTraining(int id) {
	return this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Iterable<Training> getAllTrainings() {
	return repository.findAll();
    }
    
    public void deleteTraining(Training training) {
	repository.delete(training);
    }

}
