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

public class Appointment {
    private int id;
    private LocalDate date;
    private LocalTime time;
    private Patient patient;
    private Doctor doctor;
    
    public Appointment() {
        // Default constructor needed for Jackson deserialization
    }
    
    public Appointment(int id, LocalDate date, LocalTime time, Patient patient, Doctor doctor) {
        this.id = id;
        this.date = date; 
        this.time = time;
        this.patient = patient;
        this.doctor = doctor;
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }    
}

