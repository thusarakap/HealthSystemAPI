/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.model;

/**
 *
 * @author Thusaraka
 */

// Model class representing a patient, extending the Person class.
public class Patient extends Person {
    private String medicalHistory;
    private String currentHealthStatus;
    
    // Default constructor
    public Patient() {
        super();
    }
    
    // Constructor with parameters
    public Patient(int id, String name, String contactInformation, String address, String medicalHistory, String currentHealthStatus) {
        super(id, name, contactInformation, address);
        this.medicalHistory = medicalHistory;
        this.currentHealthStatus = currentHealthStatus;
    }
    
    // Getter for medical history
    public String getMedicalHistory() {
        return medicalHistory;
    }
    
    // Setter for medical history
    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
    
    // Getter for current health status
    public String getCurrentHealthStatus() {
        return currentHealthStatus;
    }
    
    // Setter for current health status
    public void setCurrentHealthStatus(String currentHealthStatus) {
        this.currentHealthStatus = currentHealthStatus;
    }
}