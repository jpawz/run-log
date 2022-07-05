package com.example.domain;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class TrainingDTO {

    private int id;

    @NotNull(message = "Choose date of the training")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Min(value = 0, message = "The distance cannot be set to less than zero.")
    private double distanceInKilometers;

    private double kCalBurned;
    
    private String comment;

    @Min(value = 0, message = "The hours cannot be set to less than zero.")
    private int durationHours;

    @Min(value = 0, message = "The minutes cannot be set to less than zero.")
    @Max(value = 59, message = "No more than 59 minutes can be set.")
    private int durationMinutes;

    @Min(value = 0, message = "The seconds cannot be set to less than zero.")
    @Max(value = 59, message = "No more than 59 seconds can be set.")
    private int durationSeconds;
    
    private double averageSpeed;

    public double getAverageSpeed() {
	return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
	this.averageSpeed = averageSpeed;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public LocalDate getDate() {
	return date;
    }

    public void setDate(LocalDate date) {
	this.date = date;
    }

    public double getDistanceInKilometers() {
	return distanceInKilometers;
    }

    public void setDistanceInKilometers(double distanceInKilometers) {
	this.distanceInKilometers = distanceInKilometers;
    }

    public double getkCalBurned() {
	return kCalBurned;
    }

    public void setkCalBurned(double kCalBurned) {
	this.kCalBurned = kCalBurned;
    }

    public String getComment() {
	return comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    public int getDurationHours() {
	return durationHours;
    }

    public void setDurationHours(int durationHours) {
	this.durationHours = durationHours;
    }

    public int getDurationMinutes() {
	return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
	this.durationMinutes = durationMinutes;
    }

    public int getDurationSeconds() {
	return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
	this.durationSeconds = durationSeconds;
    }

}
