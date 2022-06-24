package com.example.domain;

import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDate date;
    private int distanceInKilometer;
    private Duration duration;
    private double kCalBurned;
    private String comment;

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

    public int getDistanceInKilometer() {
	return distanceInKilometer;
    }

    public void setDistanceInKilometer(int distanceInKilometer) {
	this.distanceInKilometer = distanceInKilometer;
    }

    public Duration getDuration() {
	return duration;
    }

    public void setDuration(Duration duration) {
	this.duration = duration;
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

}
