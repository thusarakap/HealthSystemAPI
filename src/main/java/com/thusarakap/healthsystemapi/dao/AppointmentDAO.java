/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.dao;

import com.thusarakap.healthsystemapi.exception.InvalidRequestException;
import com.thusarakap.healthsystemapi.exception.AppointmentNotFoundException;
import com.thusarakap.healthsystemapi.model.Appointment;
import com.thusarakap.healthsystemapi.model.Doctor;
import com.thusarakap.healthsystemapi.model.Patient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thusaraka
 */

// Data Access Object (DAO) class for handling operations related to appointments. 
public class AppointmentDAO {
    // Initializing SLF4J logger 
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentDAO.class);

    // Initializing list to store appointments 
    private static final List<Appointment> appointmentList = new ArrayList<>();

    // Static initializer to add sample data 
    static {
        Patient patient1 = new Patient(1, "Peter", "peter@gmail.com", "123 Colombo", "Chronic illness", "Good");
        Patient patient2 = new Patient(2, "Anne Green", "anne@example.com", "456 Boston", "Allergic rhinitis", "Better");
        Patient patient3 = new Patient(3, "Michael Jordan", "michael@gmail.com", "789 California", "Hypertension", "Critical");
        
        Doctor doctor1 = new Doctor(1, "Dr. Chamari Palliyeguru", "chamari@gmail.com", "789 Kandy St", "Cardiology", "0711977578");
  
        // Adding sample appointments
        appointmentList.add(new Appointment(1, "29-04-2024", "10:00", patient1, doctor1));
        appointmentList.add(new Appointment(2, "23-04-2024", "10:30", patient2, doctor1));
        appointmentList.add(new Appointment(3, "01-05-2024", "13:00", patient3, doctor1));
    }

    // Method to retrieve all appointments 
    public List<Appointment> getAllAppointments() {
        LOGGER.info("Getting all appointments.");
        return appointmentList;
    }

    // Method to retrieve an appointment by ID 
    public Appointment getAppointmentById(int appointmentId) throws AppointmentNotFoundException {
        LOGGER.info("Getting appointment by ID: {}", appointmentId);
        // Iterate through the list to find the appointment with specified ID 
        for (Appointment appointment : appointmentList) {
            if (appointment.getAppointmentId() == appointmentId) {
                return appointment;
            }
        }
        // Throw exception if appointment with specified ID is not found 
        throw new AppointmentNotFoundException("Appointment with ID " + appointmentId + " not found.");
    }
    
    // Method to retrieve appointments by doctor ID 
    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
        List<Appointment> doctorAppointments = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            if (appointment.getDoctor().getId() == doctorId) {
                doctorAppointments.add(appointment);
            }
        }
        return doctorAppointments;
    }
    
    // Method to retrieve appointments by patient ID 
    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        List<Appointment> patientAppointments = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            if (appointment.getPatient().getId() == patientId) {
                patientAppointments.add(appointment);
            }
        }
        return patientAppointments;
    }

    // Method to add a new appointment 
    public void addAppointment(Appointment appointment) throws InvalidRequestException {
        LOGGER.info("Adding new appointment: {}", appointment);
        // Check if the appointment already exists
        if (appointmentList.contains(appointment)) {
            throw new InvalidRequestException("Appointment already exists.");
        }
        // Generate a new ID for the appointment and add to the list 
        appointment.setAppointmentId(getNextAppointmentId());
        // Add the appointment to the appointmentList
        appointmentList.add(appointment);
        LOGGER.info("Added appointment: {}", getNextAppointmentId());
    }
    
    // Method to get the next available appointment ID 
    private int getNextAppointmentId() {
        LOGGER.info("Getting next appointment ID.");
        // Initialize maxAppointment ID with a value lower than any possible appointment ID
        int maxAppointmentId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum appointment ID
        for (Appointment appointment : appointmentList) {
            int appointmentId = appointment.getAppointmentId();
            if (appointmentId > maxAppointmentId) {
                maxAppointmentId = appointmentId;
            }
        }

        // Increment the maximum appointment ID to get the next available appointment ID
        return maxAppointmentId + 1;
    }

    // Method to update an appointment by ID 
    public void updateAppointment(int appointmentId, Appointment updatedAppointment) throws AppointmentNotFoundException {
        LOGGER.info("Updating appointment with ID: {}", appointmentId);
        boolean found = false;
        /* Iterate through the list to find the appointment with specified ID */
        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment appointment = appointmentList.get(i);
            if (appointment.getAppointmentId() == appointmentId) {
                appointmentList.set(i, updatedAppointment);
                found = true;
                break;
            }
        }
        // Throw exception if appointment with specified ID is not found 
        if (!found) {
            throw new AppointmentNotFoundException("Appointment with ID " + appointmentId + " not found.");
        }
    }

    // Method to delete an appointment by ID 
    public void deleteAppointment(int appointmentId) throws AppointmentNotFoundException {
        LOGGER.info("Deleting appointment with ID: {}", appointmentId);
        // Use lambda expression to remove appointment from list 
        boolean removed = appointmentList.removeIf(appointment -> appointment.getAppointmentId() == appointmentId);
        // Throw exception if appointment with specified ID is not found 
        if (!removed) {
            throw new AppointmentNotFoundException("Appointment with ID " + appointmentId + " not found.");
        }
    }
    
}