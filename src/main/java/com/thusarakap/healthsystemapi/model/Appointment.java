/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Thusaraka
 */

/* Model class representing an appointment. */
public class Appointment {
    private int id;
    private LocalDate date;
    private LocalTime time;
    private Patient patient;
    private Doctor doctor;
    
    /* Default constructor */
    public Appointment() {
    }
    
    /* Constructor with parameters */
    public Appointment(int id, LocalDate date, LocalTime time, Patient patient, Doctor doctor) {
        this.id = id;
        this.date = date; 
        this.time = time;
        this.patient = patient;
        this.doctor = doctor;
    }

    /* Getter for appointment ID */
    public int getId() {
        return id;
    }

    /* Setter for appointment ID */
    public void setId(int id) {
        this.id = id;
    }

    /* Getter for appointment date */
    public LocalDate getDate() {
        return date;
    }

    /* Setter for appointment date */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /* Getter for appointment time */
    public LocalTime getTime() {
        return time;
    }

    /* Setter for appointment time */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /* Getter for patient */
    public Patient getPatient() {
        return patient;
    }

    /* Setter for patient */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /* Getter for doctor */
    public Doctor getDoctor() {
        return doctor;
    }

    /* Setter for doctor */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }    
}
