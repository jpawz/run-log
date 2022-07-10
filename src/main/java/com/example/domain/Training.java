package com.example.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Class that represents a training entity. The class contains fields such as
 * date of training, distance, duration, calories and comments.
 */
@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private double distanceInKilometers;
    private int duration;
    private double kCalBurned;
    private String comment;

    /**
     * Empty constructor required for Hibernate.
     */
    public Training() {

    }

    /**
     * Creates a training object.
     * 
     * @param date                 - date of training (yyyy-MM-dd)
     * @param distanceInKilometers - distance traveled
     * @param duration             - duration of training in seconds
     * @param kCalBurned           - calories burned during training
     * @param comment              - comment on training
     */
    public Training(LocalDate date, double distanceInKilometers, int duration, double kCalBurned, String comment) {
	super();
	this.date = date;
	this.distanceInKilometers = distanceInKilometers;
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

    public double getDistanceInKilometers() {
	return distanceInKilometers;
    }

    public void setDistanceInKilometers(double distanceInKilometers) {
	this.distanceInKilometers = distanceInKilometers;
    }

    public int getDuration() {
	return duration;
    }

    public void setDuration(int seconds) {
	this.duration = seconds;
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
