package com.example.mapper;

import org.springframework.stereotype.Component;

import com.example.domain.Training;
import com.example.domain.TrainingDTO;

/**
 * Converts {@link Training} to {@link TrainingDTO} and vice versa.
 *
 */
@Component
public class Mapper {

    public TrainingDTO toDto(Training training) {
	int durationSeconds = toSeconds(training.getDuration());
	int durationMinutes = toMinutes(training.getDuration());
	int durationHours = toHours(training.getDuration());

	TrainingDTO dto = new TrainingDTO();

	dto.setId(training.getId());
	dto.setComment(training.getComment());
	dto.setDate(training.getDate());
	dto.setDistanceInKilometers(training.getDistanceInKilometers());
	dto.setDurationHours(durationHours);
	dto.setDurationMinutes(durationMinutes);
	dto.setDurationSeconds(durationSeconds);
	dto.setkCalBurned(training.getkCalBurned());
	dto.setAverageSpeed(60 * 60 * training.getDistanceInKilometers() / training.getDuration());

	return dto;
    }

    public Training toTraining(TrainingDTO trainingDTO) {
	int duration = toSeconds(trainingDTO.getDurationHours(), trainingDTO.getDurationMinutes(),
		trainingDTO.getDurationSeconds());
	Training training = new Training();
	training.setId(trainingDTO.getId());
	training.setDuration(duration);
	training.setComment(trainingDTO.getComment());
	training.setDate(trainingDTO.getDate());
	training.setDistanceInKilometers(trainingDTO.getDistanceInKilometers());
	training.setkCalBurned(trainingDTO.getkCalBurned());

	return training;
    }

    private int toSeconds(int hours, int minutes, int seconds) {
	return (hours * 60 * 60) + (minutes * 60) + seconds;
    }

    private int toSeconds(int seconds) {
	return seconds % 60;
    }

    private int toMinutes(int seconds) {
	return (seconds / 60) % 60;
    }

    private int toHours(int seconds) {
	return (seconds / 60) / 60;
    }

}
