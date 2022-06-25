package com.example.domain;

import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private double distanceInKilometer;
    private long duration;
    private double kCalBurned;
    private String comment;

    public Training() {

    }

    public Training(LocalDate date, double distanceInKilometer, long duration, double kCalBurned, String comment) {
	super();
	this.date = date;
	this.distanceInKilometer = distanceInKilometer;
	this.duration = duration;
	this.kCalBurned = kCalBurned;
	this.comment = comment;
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

    public long getDuration() {
	return duration;
    }

    public void setDuration(long duration) {
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
