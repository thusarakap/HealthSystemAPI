/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.dao;

import com.thusarakap.healthsystemapi.exceptions.InvalidRequestException;
import com.thusarakap.healthsystemapi.exceptions.PersonNotFoundException;
import com.thusarakap.healthsystemapi.model.Patient;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Thusaraka
 */

public class PatientDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientDAO.class);
    private static final List<Patient> patientList = new ArrayList<>();
    private static final PersonDAO personDAO = new PersonDAO();

    // Static initializer to add sample data
    static {
        // Adding sample patients
        addPatient(new Patient(1, "John Doe", "john@example.com", "123 Main St", "Chronic illness", "Stable"));
        addPatient(new Patient(2, "Jane Smith", "jane@example.com", "456 Elm St", "Allergic rhinitis", "Improving"));
        addPatient(new Patient(3, "Michael Johnson", "michael@example.com", "789 Oak St", "Hypertension", "Critical"));
    }

    public static List<Patient> getAllPatients() {
        LOGGER.info("Getting all patients.");
        return patientList;
    }

    public static Patient getPatientById(int id) throws PersonNotFoundException {
        LOGGER.info("Getting patient by ID: {}", id);
        for (Patient patient : patientList) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        throw new PersonNotFoundException("Patient with ID " + id + " not found.");
    }

    public static void addPatient(Patient patient) throws InvalidRequestException {
        LOGGER.info("Adding new patient: {}", patient);
        // Check if the patient already exists
        if (patientList.contains(patient)) {
            throw new InvalidRequestException("Patient already exists.");
        }
        // Set the ID for the patient
        int newPersonId = personDAO.getNextPersonId();
        patient.setId(newPersonId);
        // Add the patient to the patientList
        patientList.add(patient);
        // Add the patient to the personList in PersonDAO
        personDAO.addPerson(patient);
    }

    public static void updatePatient(int id, Patient updatedPatient) throws PersonNotFoundException {
        LOGGER.info("Updating patient with ID: {}", id);
        boolean found = false;
        for (int i = 0; i < patientList.size(); i++) {
            Patient patient = patientList.get(i);
            if (patient.getId() == id) {
                patientList.set(i, updatedPatient);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new PersonNotFoundException("Patient with ID " + id + " not found.");
        }
    }

    public static void deletePatient(int id) throws PersonNotFoundException {
        LOGGER.info("Deleting patient with ID: {}", id);
        boolean removed = patientList.removeIf(patient -> patient.getId() == id);
        if (!removed) {
            throw new PersonNotFoundException("Patient with ID " + id + " not found.");
        }
        // Remove the patient from the personList in PersonDAO
        personDAO.deletePerson(id);
    }
}