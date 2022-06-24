package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Training;

public interface TrainingRepository extends CrudRepository<Training, Integer> {

}
