package com.example.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.example.domain.Training;
import com.example.domain.TrainingDTO;

class MapperTest {

    int oneHour = 3600;
    int oneMinute = 60;

    @Test
    void shoudProperlyCalculateSplitDurationIntoHoursMinutesSeconds() {
	Training training = new Training();

	training.setDuration(oneHour + 2 * oneMinute + 5); // one hour, two minutes, 5 seconds
	Mapper mapper = new Mapper();

	TrainingDTO dto = mapper.toDto(training);

	assertThat(dto.getDurationH()).isEqualTo(1);
	assertThat(dto.getDurationM()).isEqualTo(2);
	assertThat(dto.getDurationS()).isEqualTo(5);
    }

    @Test
    void shoudProperlyCombineHoursMinutesSecondsIntoSecondsDuration() {
	Training training = new Training();
	TrainingDTO trainingDto = new TrainingDTO();
	trainingDto.setDurationH(2);
	trainingDto.setDurationM(0);
	trainingDto.setDurationS(10);

	Mapper mapper = new Mapper();
	training = mapper.toTraining(trainingDto);

	assertThat(training.getDuration()).isEqualTo(2 * oneHour + 10);
    }

    @Test
    void shouldProperlyCalculateAvgSpeed() {
	Training training = new Training();
	training.setDuration(2 * oneHour + 0 * oneMinute + 0); // two hour, 0 minutes, 0 seconds
	training.setDistanceInKilometer(10);
	Mapper mapper = new Mapper();

	TrainingDTO dto = mapper.toDto(training);

	assertThat(dto.getAvgSpeed()).isEqualTo(5);
    }
}
