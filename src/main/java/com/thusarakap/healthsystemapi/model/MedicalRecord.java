/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thusaraka
 */

// Model class representing a medical record.
public class MedicalRecord {
    private int medicalRecordId;
    private Patient patient;
    private List<String> diagnoses;
    private List<String> treatments;
    private List<String> additionalNotes;

    // Default constructor
    public MedicalRecord() {
    }
    
    // Constructor with parameters
    public MedicalRecord(int medicalRecordId, Patient patient) {
        this.medicalRecordId = medicalRecordId;
        this.patient = patient;
        this.diagnoses = new ArrayList<>();
        this.treatments = new ArrayList<>();
        this.additionalNotes = new ArrayList<>();
    }

    // Getter for medical record ID
    public int getMedicalRecordId() {
        return medicalRecordId;
    }

    // Setter for medical record ID
    public void setMedicalRecordId(int medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    // Getter for patient
    public Patient getPatient() {
        return patient;
    }

    // Setter for patient
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    // Method to add diagnosis
    public void addDiagnosis(String diagnosis) {
        diagnoses.add(diagnosis);
    }
    
    // Getter for diagnoses
    public List<String> getDiagnoses() {
        return diagnoses;
    }
    
    // Method to add treatment
    public void addTreatment(String treatment) {
        treatments.add(treatment);
    }
    
    // Getter for treatments
    public List<String> getTreatments() {
        return treatments;
    }
  
    // Method to add additional notes
    public void addAdditionalNotes(String note) {
        additionalNotes.add(note);
    }
    
    // Getter for additional notes
    public List<String> getAdditionalNotes() {
        return additionalNotes;
    }
}