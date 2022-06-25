package com.example.mapper;

import org.springframework.stereotype.Component;

import com.example.domain.Training;
import com.example.domain.TrainingDTO;

@Component
public class Mapper {

    public TrainingDTO toDto(Training training) {
	int durationS = (int) (training.getDuration() % 60);
	int durationM = (int) ((training.getDuration() / 60) % 60);
	int durationH = (int) ((training.getDuration() / 60) / 60);

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
	long duration = (trainingDTO.getDurationH() * 60 * 60) + (trainingDTO.getDurationM() * 60)
		+ trainingDTO.getDurationS();
	Training training = new Training();
	training.setDuration(duration);
	training.setComment(trainingDTO.getComment());
	training.setDate(trainingDTO.getDate());
	training.setDistanceInKilometer(trainingDTO.getDistanceInKilometer());
	training.setkCalBurned(trainingDTO.getkCalBurned());

	return training;
    }

}
