/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.dao;

import com.thusarakap.healthsystemapi.exception.InvalidRequestException;
import com.thusarakap.healthsystemapi.exception.MedicalRecordNotFoundException;
import com.thusarakap.healthsystemapi.model.MedicalRecord;
import com.thusarakap.healthsystemapi.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thusaraka
 */

 // Data Access Object (DAO) class for handling operations related to medical records. 
public class MedicalRecordDAO {
    // Initializing SLF4J logger 
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordDAO.class);

    // Initializing list to store medical records 
    private static final List<MedicalRecord> medicalRecordList = new ArrayList<>();

    static {
        // Sample patients 
        Patient patient1 = new Patient(1, "Peter", "peter@gmail.com", "123 Colombo", "Chronic illness", "Good");
        Patient patient2 = new Patient(2, "Anne Green", "anne@example.com", "456 Boston", "Allergic rhinitis", "Better");
        Patient patient3 = new Patient(3, "Michael Jordan", "michael@gmail.com", "789 California", "Hypertension", "Critical");

        // Adding sample medical records
        medicalRecordList.add(new MedicalRecord(1, patient1));
        medicalRecordList.add(new MedicalRecord(2, patient2));
        medicalRecordList.add(new MedicalRecord(3, patient3));
    }

    // Method to retrieve all medical records 
    public List<MedicalRecord> getAllMedicalRecords() {
        LOGGER.info("Getting all medical records.");
        return medicalRecordList;
    }

    // Method to retrieve a medical record by ID 
    public MedicalRecord getMedicalRecordById(int id) throws MedicalRecordNotFoundException {
        LOGGER.info("Getting medical record by ID: {}", id);
        // Iterate through the list to find the medical record with specified ID 
        for (MedicalRecord medicalRecord : medicalRecordList) {
            if (medicalRecord.getMedicalRecordId() == id) {
                return medicalRecord;
            }
        }
        // Throw exception if medical record with specified ID is not found 
        throw new MedicalRecordNotFoundException("Medical record with ID " + id + " not found.");
    }
    
    // Method to add a new medical record 
    public void addMedicalRecord(MedicalRecord medicalRecord) throws InvalidRequestException {
        LOGGER.info("Adding new medical record: {}", medicalRecord);
        // Check if the medical record already exists
        if (medicalRecordList.contains(medicalRecord)) {
            throw new InvalidRequestException("Medical record already exists.");
        }
        // Generate a new ID for the medical record and add to the list 
        medicalRecord.setMedicalRecordId(getNextMedicalRecordId());
        medicalRecordList.add(medicalRecord);
    }

    // Method to get the next available prescription ID 
    public int getNextMedicalRecordId() {
        LOGGER.info("Getting next medical record ID.");
        // Initialize maxMedicalRecordId with a value lower than any possible medical record ID
        int maxMedicalRecordId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum medical record ID
        for (MedicalRecord medicalRecord : medicalRecordList) {
            int medicalRecordId = medicalRecord.getMedicalRecordId();
            if (medicalRecordId > maxMedicalRecordId) {
                maxMedicalRecordId = medicalRecordId;
            }
        }

        // Increment the maximum medical record ID to get the next available medical record ID
        return maxMedicalRecordId + 1;
    }

    // Method to update a medical record by ID 
    public void updateMedicalRecord(int id, MedicalRecord updatedMedicalRecord) throws MedicalRecordNotFoundException {
        LOGGER.info("Updating medical record with ID: {}", id);
        boolean found = false;
        // Iterate through the list to find the medical record with specified ID 
        for (int i = 0; i < medicalRecordList.size(); i++) {
            MedicalRecord medicalRecord = medicalRecordList.get(i);
            if (medicalRecord.getMedicalRecordId() == id) {
                medicalRecordList.set(i, updatedMedicalRecord);
                found = true;
                break;
            }
        }
        // Throw exception if medical record with specified ID is not found 
        if (!found) {
            throw new MedicalRecordNotFoundException("Medical record with ID " + id + " not found.");
        }
    }

    // Method to delete a medical record by ID 
    public void deleteMedicalRecord(int id) throws MedicalRecordNotFoundException {
        LOGGER.info("Deleting medical record with ID: {}", id);
        // Use lambda expression to remove medical record from list 
        boolean removed = medicalRecordList.removeIf(medicalRecord -> medicalRecord.getMedicalRecordId() == id);
        // Throw exception if medical record with specified ID is not found 
        if (!removed) {
            throw new MedicalRecordNotFoundException("Medical record with ID " + id + " not found.");
        }
    }

    // Method to add a diagnosis for a medical record 
    public void addDiagnosis(int medicalRecordId, String diagnosis) throws MedicalRecordNotFoundException {
        LOGGER.info("Adding diagnosis for medical record with ID {}: {}", medicalRecordId, diagnosis);
        MedicalRecord medicalRecord = getMedicalRecordById(medicalRecordId);
        medicalRecord.addDiagnosis(diagnosis);
    }

    // Method to add a treatment for a medical record 
    public void addTreatment(int medicalRecordId, String treatment) throws MedicalRecordNotFoundException {
        LOGGER.info("Adding treatment for medical record with ID {}: {}", medicalRecordId, treatment);
        MedicalRecord medicalRecord = getMedicalRecordById(medicalRecordId);
        medicalRecord.addTreatment(treatment);
    }

    // Method to add additional notes for a medical record 
    public void addAdditionalNotes(int medicalRecordId, String note) throws MedicalRecordNotFoundException {
        LOGGER.info("Adding additional note for medical record with ID {}: {}", medicalRecordId, note);
        MedicalRecord medicalRecord = getMedicalRecordById(medicalRecordId);
        medicalRecord.addAdditionalNotes(note);
    }
}