/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.model;

/**
 *
 * @author Thusaraka
 */

public class Patient extends Person{
    private String medicalHistory;
    private String currentHealthStatus;
    
    public Patient(int id, String name, String contactInformation, String address, String medicalHistory, String currentHealthStatus) {
        super(id, name, contactInformation, address);
        this.medicalHistory = medicalHistory;
        this.currentHealthStatus = currentHealthStatus;
    }
    
    public String getMedicalHistory() {
        return medicalHistory;
    }
    
    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
    
    public String getCurrentHealthStatus() {
        return currentHealthStatus;
    }
    
    public void setCurrentHealthStatus(String currentHealthStatus) {
        this.currentHealthStatus = currentHealthStatus;
    }
}
