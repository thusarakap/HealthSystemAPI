/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.model;

/**
 *
 * @author Thusaraka
 */

// Model class representing a prescription. 
public class Prescription {
    private int prescriptionId;
    private String medication;
    private String dosage;
    private String instructions;
    private String duration;
    private Patient patient;
    private Doctor doctor;

    // Default constructor 
    public Prescription() {
    }
    
    // Constructor with parameters 
    public Prescription(int prescriptionId, String medication, String dosage, String instructions, String duration, Patient patient, Doctor doctor) {
        this.prescriptionId = prescriptionId;
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
        this.duration = duration;
        this.patient = patient;
        this.doctor = doctor;
    }

    // Getter for prescription ID 
    public int getPrescriptionId() {
        return prescriptionId;
    }

    // Setter for prescription ID 
    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    // Getter for medication 
    public String getMedication() {
        return medication;
    }

    // Setter for medication 
    public void setMedication(String medication) {
        this.medication = medication;
    }

    // Getter for dosage 
    public String getDosage() {
        return dosage;
    }

    // Setter for dosage 
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    // Getter for instructions 
    public String getInstructions() {
        return instructions;
    }

    // Setter for instructions 
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    // Getter for duration 
    public String getDuration() {
        return duration;
    }

    // Setter for duration 
    public void setDuration(String duration) {
        this.duration = duration;
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