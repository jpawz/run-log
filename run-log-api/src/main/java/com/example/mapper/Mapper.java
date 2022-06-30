package com.example.mapper;

import org.springframework.stereotype.Component;

import com.example.domain.Training;
import com.example.domain.TrainingDTO;

@Component
public class Mapper {

    public TrainingDTO toDto(Training training) {
	int durationS = toSeconds((int) training.getDuration());
	int durationM = toMinutes((int) training.getDuration());
	int durationH = toHours((int) training.getDuration());

	TrainingDTO dto = new TrainingDTO();

	dto.setId(training.getId());
	dto.setComment(training.getComment());
	dto.setDate(training.getDate());
	dto.setDistanceInKilometer(training.getDistanceInKilometer());
	dto.setDurationH(durationH);
	dto.setDurationM(durationM);
	dto.setDurationS(durationS);
	dto.setkCalBurned(training.getkCalBurned());
	dto.setAvgSpeed(60 * 60 * training.getDistanceInKilometer() / training.getDuration());

	return dto;
    }

    public Training toTraining(TrainingDTO trainingDTO) {
	long duration = toSeconds(trainingDTO.getDurationH(), trainingDTO.getDurationM(),
		trainingDTO.getDurationS());
	Training training = new Training();
	training.setId(trainingDTO.getId());
	training.setDuration(duration);
	training.setComment(trainingDTO.getComment());
	training.setDate(trainingDTO.getDate());
	training.setDistanceInKilometer(trainingDTO.getDistanceInKilometer());
	training.setkCalBurned(trainingDTO.getkCalBurned());

	return training;
    }

    private long toSeconds(int hours, int minutes, int seconds) {
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
