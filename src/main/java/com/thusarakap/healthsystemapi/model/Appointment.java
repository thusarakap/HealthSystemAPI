/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.model;

/**
 *
 * @author Thusaraka
 */

// Model class representing an appointment.
public class Appointment {
    private int appointmentId;
    private String date;
    private String time;
    private Patient patient;
    private Doctor doctor;
    
    // Default constructor
    public Appointment() {
    }
    
    // Constructor with parameters
    public Appointment(int appointmentId, String date, String time, Patient patient, Doctor doctor) {
        this.appointmentId = appointmentId;
        this.date = date; 
        this.time = time;
        this.patient = patient;
        this.doctor = doctor;
    }

    // Getter for appointment ID
    public int getAppointmentId() {
        return appointmentId;
    }

    // Setter for appointment ID
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    // Getter for appointment date
    public String getDate() {
        return date;
    }

    // Setter for appointment date
    public void setDate(String date) {
        this.date = date;
    }

    // Getter for appointment time
    public String getTime() {
        return time;
    }

    // Setter for appointment time
    public void setTime(String time) {
        this.time = time;
    }

    // Getter for patient
    public Patient getPatient() {
        return patient;
    }

    // Setter for patient
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    // Getter for doctor
    public Doctor getDoctor() {
        return doctor;
    }

    // Setter for doctor
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }    
}
