package com.example.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class TrainingDTO {

    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private double distanceInKilometer;
    private double kCalBurned;
    private String comment;
    private int durationH;
    private int durationM;
    private int durationS;
    private double avgSpeed;

    public double getAvgSpeed() {
	return avgSpeed;
    }

    public void setAvgSpeed(double avgSpeed) {
	this.avgSpeed = avgSpeed;
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

    public double getDistanceInKilometer() {
	return distanceInKilometer;
    }

    public void setDistanceInKilometer(double distanceInKilometer) {
	this.distanceInKilometer = distanceInKilometer;
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

    public int getDurationH() {
	return durationH;
    }

    public void setDurationH(int durationH) {
	this.durationH = durationH;
    }

    public int getDurationM() {
	return durationM;
    }

    public void setDurationM(int durationM) {
	this.durationM = durationM;
    }

    public int getDurationS() {
	return durationS;
    }

    public void setDurationS(int durationS) {
	this.durationS = durationS;
    }

}
