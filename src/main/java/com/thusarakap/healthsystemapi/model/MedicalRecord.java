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

public class MedicalRecord {
    private int medicalRecordId;
    private Patient patient;
    private List<String> diagnoses;
    private List<String> treatments;
    private List<String> additionalNotes;

    public MedicalRecord() {
        // Default constructor needed for Jackson deserialization
    }
    
    public MedicalRecord(int medicalRecordId, Patient patient) {
        this.medicalRecordId = medicalRecordId;
        this.patient = patient;
        this.diagnoses = new ArrayList<>();
        this.treatments = new ArrayList<>();
        this.additionalNotes = new ArrayList<>();
    }

    public int getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(int medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public void addDiagnosis(String diagnosis) {
        diagnoses.add(diagnosis);
    }
    
    public List<String> getDiagnoses() {
        return diagnoses;
    }
    
     public void addTreatment(String treatment) {
        treatments.add(treatment);
    }
    
    public List<String> getTreatments() {
        return treatments;
    }
  
    public void addAdditionalNotes(String note) {
        additionalNotes.add(note);
    }
    
    public List<String> getAdditonalNotes() {
        return additionalNotes;
    }
}
