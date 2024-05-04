/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.dao;

import com.thusarakap.healthsystemapi.exception.InvalidRequestException;
import com.thusarakap.healthsystemapi.exception.PersonNotFoundException;
import com.thusarakap.healthsystemapi.model.Doctor;
import com.thusarakap.healthsystemapi.model.Patient;
import com.thusarakap.healthsystemapi.model.Prescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thusaraka
 */

// Data Access Object (DAO) for handling Prescription data. 
public class PrescriptionDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionDAO.class);

    private static final List<Prescription> prescriptionList = new ArrayList<>();

    // Static initializer to add sample data 
    static {
        // Sample patients 
        Patient patient1 = new Patient(1, "John Doe", "john@example.com", "123 Main St", "Chronic illness", "Stable");
        Patient patient2 = new Patient(2, "Jane Smith", "jane@example.com", "456 Elm St", "Allergic rhinitis", "Improving");
        Patient patient3 = new Patient(3, "Michael Johnson", "michael@example.com", "789 Oak St", "Hypertension", "Critical");

        // Sample Doctors
        Doctor doctor1 = new Doctor(2, "Dr. John Doe", "john@example.com", "123 Main St", "General Medicine", "123-456-7890");
        Doctor doctor2 = new Doctor(2, "Dr. Jane Smith", "jane@example.com", "456 Elm St", "Pediatrics", "987-654-3210");
        Doctor doctor3 = new Doctor(2, "Dr. Michael Johnson", "michael@example.com", "789 Oak St", "Cardiology", "456-789-0123");
        
        // Adding sample prescriptions
        prescriptionList.add(new Prescription(1, "Medication A", "10mg", "Take with water", "2 weeks", patient1, doctor1));
        prescriptionList.add(new Prescription(2, "Medication B", "5mg", "Take before meals", "1 month", patient2, doctor2));
        prescriptionList.add(new Prescription(3, "Medication C", "20mg", "Take twice daily", "3 weeks", patient3, doctor3));
    }

    // Method to get all prescriptions 
    public List<Prescription> getAllPrescriptions() {
        LOGGER.info("Getting all prescriptions.");
        return prescriptionList;
    }

    // Method to get prescription by ID 
    public Prescription getPrescriptionById(int prescriptionId) throws PersonNotFoundException {
        LOGGER.info("Getting prescription by ID: {}", prescriptionId);
        for (Prescription prescription : prescriptionList) {
            if (prescription.getPrescriptionId() == prescriptionId) {
                return prescription;
            }
        }
        throw new PersonNotFoundException("Prescription with ID " + prescriptionId + " not found.");
    }

    // Method to add a new prescription 
    public void addPrescription(Prescription prescription) throws InvalidRequestException {
        LOGGER.info("Adding new prescription: {}", prescription);
        // Check if the prescription already exists
        if (prescriptionList.contains(prescription)) {
            throw new InvalidRequestException("Prescription already exists.");
        }
        // Generate a new ID for the prescription and add to the list 
        prescription.setPrescriptionId(getNextPrescriptionId());
        // Add the prescription to the prescriptionList
        prescriptionList.add(prescription);
        LOGGER.info("Added prescription: {}", getNextPrescriptionId());
    }

    // Method to get the next available prescription ID 
    public int getNextPrescriptionId() {
        LOGGER.info("Getting next prescription ID.");
        // Initialize maxPrescriptionId with a value lower than any possible prescription ID
        int maxPrescriptionId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum prescription ID
        for (Prescription prescription : prescriptionList) {
            int prescriptionId = prescription.getPrescriptionId();
            if (prescriptionId > maxPrescriptionId) {
                maxPrescriptionId = prescriptionId;
            }
        }

        // Increment the maximum prescription ID to get the next available prescription ID
        return maxPrescriptionId + 1;
    }

    // Method to update a prescription 
    public void updatePrescription(int prescriptionId, Prescription updatedPrescription) throws PersonNotFoundException {
        LOGGER.info("Updating prescription with ID: {}", prescriptionId);
        boolean found = false;
        for (int i = 0; i < prescriptionList.size(); i++) {
            Prescription prescription = prescriptionList.get(i);
            if (prescription.getPrescriptionId() == prescriptionId) {
                prescriptionList.set(i, updatedPrescription);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new PersonNotFoundException("Prescription with ID " + prescriptionId + " not found.");
        }
    }

    // Method to delete a prescription 
    public void deletePrescription(int prescriptionId) throws PersonNotFoundException {
        LOGGER.info("Deleting prescription with ID: {}", prescriptionId);
        boolean removed = prescriptionList.removeIf(prescription -> prescription.getPrescriptionId() == prescriptionId);
        if (!removed) {
            throw new PersonNotFoundException("Prescription with ID " + prescriptionId + " not found.");
        }
    }
}