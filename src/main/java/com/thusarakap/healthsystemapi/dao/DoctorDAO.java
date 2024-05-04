/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.dao;

import com.thusarakap.healthsystemapi.exception.InvalidRequestException;
import com.thusarakap.healthsystemapi.exception.PersonNotFoundException;
import com.thusarakap.healthsystemapi.model.Doctor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thusaraka
 */

 /* Data Access Object (DAO) class for handling operations related to doctors */
public class DoctorDAO {
    /* Initializing SLF4J logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorDAO.class);

    /* Initializing list to store doctors */
    private static final List<Doctor> doctorList = new ArrayList<>();

    /* Creating instance of PersonDAO for managing person data */
    private static final PersonDAO personDAO = new PersonDAO();

    /* Static initializer to add sample data */
    static {
        // Adding sample doctors
        addDoctor(new Doctor(1, "Dr. John Doe", "john@example.com", "123 Main St", "General Medicine", "123-456-7890"));
        addDoctor(new Doctor(2, "Dr. Jane Smith", "jane@example.com", "456 Elm St", "Pediatrics", "987-654-3210"));
        addDoctor(new Doctor(3, "Dr. Michael Johnson", "michael@example.com", "789 Oak St", "Cardiology", "456-789-0123"));
    }

    /* Method to retrieve all doctors */
    public static List<Doctor> getAllDoctors() {
        LOGGER.info("Getting all doctors.");
        return doctorList;
    }

    /* Method to retrieve a doctor by ID */
    public static Doctor getDoctorById(int id) throws PersonNotFoundException {
        LOGGER.info("Getting doctor by ID: {}", id);
        /* Iterate through the list to find the doctor with specified ID */
        for (Doctor doctor : doctorList) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        /* Throw exception if doctor with specified ID is not found */
        throw new PersonNotFoundException("Doctor with ID " + id + " not found.");
    }

    /* Method to add a new doctor */
    public static void addDoctor(Doctor doctor) throws InvalidRequestException {
        LOGGER.info("Adding new doctor: {}", doctor);
        // Check if the doctor already exists
        if (doctorList.contains(doctor)) {
            throw new InvalidRequestException("Doctor already exists.");
        }
        // Set the ID for the doctor
        int newPersonId = personDAO.getNextPersonId();
        doctor.setId(newPersonId);
        // Add the doctor to the doctorList
        doctorList.add(doctor);
        // Add the doctor to the personList in PersonDAO
        personDAO.addPerson(doctor);
    }

    /* Method to update a doctor by ID */
    public static void updateDoctor(int id, Doctor updatedDoctor) throws PersonNotFoundException {
        LOGGER.info("Updating doctor with ID: {}", id);
        boolean found = false;
        /* Iterate through the list to find the doctor with specified ID */
        for (int i = 0; i < doctorList.size(); i++) {
            Doctor doctor = doctorList.get(i);
            if (doctor.getId() == id) {
                doctorList.set(i, updatedDoctor);
                found = true;
                break;
            }
        }
        /* Throw exception if doctor with specified ID is not found */
        if (!found) {
            throw new PersonNotFoundException("Doctor with ID " + id + " not found.");
        }
    }

    /* Method to delete a doctor by ID */
    public static void deleteDoctor(int id) throws PersonNotFoundException {
        LOGGER.info("Deleting doctor with ID: {}", id);
        /* Use lambda expression to remove doctor from list */
        boolean removed = doctorList.removeIf(doctor -> doctor.getId() == id);
        /* Throw exception if doctor with specified ID is not found */
        if (!removed) {
            throw new PersonNotFoundException("Doctor with ID " + id + " not found.");
        }
        // Remove the doctor from the personList in PersonDAO
        personDAO.deletePerson(id);
    }
}