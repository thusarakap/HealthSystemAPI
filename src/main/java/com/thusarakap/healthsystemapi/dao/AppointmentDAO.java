/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.dao;

import com.thusarakap.healthsystemapi.exceptions.InvalidRequestException;
import com.thusarakap.healthsystemapi.exceptions.AppointmentNotFoundException;
import com.thusarakap.healthsystemapi.model.Appointment;
import com.thusarakap.healthsystemapi.model.Doctor;
import com.thusarakap.healthsystemapi.model.Patient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Thusaraka
 */

public class AppointmentDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentDAO.class);

    private static final List<Appointment> appointmentList = new ArrayList<>();

    static {
        Patient patient1 = new Patient(1, "John Doe", "john@example.com", "123 Main St", "Chronic illness", "Stable");
        Patient patient2 = new Patient(2, "Jane Smith", "jane@example.com", "456 Elm St", "Allergic rhinitis", "Improving");
        Patient patient3 = new Patient(3, "Michael Johnson", "michael@example.com", "789 Oak St", "Hypertension", "Critical");
        Doctor doctor1 = new Doctor(2, "Dr. John Doe", "john@example.com", "123 Main St", "General Medicine", "123-456-7890");
        Doctor doctor2 = new Doctor(2, "Dr. Jane Smith", "jane@example.com", "456 Elm St", "Pediatrics", "987-654-3210");
        Doctor doctor3 = new Doctor(2, "Dr. Michael Johnson", "michael@example.com", "789 Oak St", "Cardiology", "456-789-0123");
        
        // Adding sample appointments
        appointmentList.add(new Appointment(1, LocalDate.of(2024, 5, 10), LocalTime.of(10, 0), patient1, doctor1));
        appointmentList.add(new Appointment(2, LocalDate.of(2024, 5, 12), LocalTime.of(14, 30), patient2, doctor2));
        appointmentList.add(new Appointment(3, LocalDate.of(2024, 5, 15), LocalTime.of(9, 0), patient3, doctor3));
    }

    public List<Appointment> getAllAppointments() {
        LOGGER.info("Getting all appointments.");
        return appointmentList;
    }

    public Appointment getAppointmentById(int id) throws AppointmentNotFoundException {
        LOGGER.info("Getting appointment by ID: {}", id);
        for (Appointment appointment : appointmentList) {
            if (appointment.getId() == id) {
                return appointment;
            }
        }
        throw new AppointmentNotFoundException("Appointment with ID " + id + " not found.");
    }
    
    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
        List<Appointment> doctorAppointments = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            if (appointment.getDoctor().getId() == doctorId) {
                doctorAppointments.add(appointment);
            }
        }
        return doctorAppointments;
    }
    
    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        List<Appointment> patientAppointments = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            if (appointment.getPatient().getId() == patientId) {
                patientAppointments.add(appointment);
            }
        }
        return patientAppointments;
    }

    public void addAppointment(Appointment appointment) throws InvalidRequestException {
        LOGGER.info("Adding new appointment: {}", appointment);
        // Check if the appointment already exists
        if (appointmentList.contains(appointment)) {
            throw new InvalidRequestException("Appointment already exists.");
        }
        // Set the ID for the appointment
        appointment.setId(getNextAppointmentId());
        // Add the appointment to the appointmentList
        appointmentList.add(appointment);
    }

    public void updateAppointment(int id, Appointment updatedAppointment) throws AppointmentNotFoundException {
        LOGGER.info("Updating appointment with ID: {}", id);
        boolean found = false;
        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment appointment = appointmentList.get(i);
            if (appointment.getId() == id) {
                appointmentList.set(i, updatedAppointment);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new AppointmentNotFoundException("Appointment with ID " + id + " not found.");
        }
    }

    public void deleteAppointment(int id) throws AppointmentNotFoundException {
        LOGGER.info("Deleting appointment with ID: {}", id);
        boolean removed = appointmentList.removeIf(appointment -> appointment.getId() == id);
        if (!removed) {
            throw new AppointmentNotFoundException("Appointment with ID " + id + " not found.");
        }
    }
    
    private int getNextAppointmentId() {
        // Initialize maxAppointmentId with a value lower than any possible appointmentId
        int maxAppointmentId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum appointmentId
        for (Appointment appointment : appointmentList) {
            int appointmentId = appointment.getId();
            if (appointmentId > maxAppointmentId) {
                maxAppointmentId = appointmentId;
            }
        }

        // Increment the maximum appointmentId to get the next available appointmentId
        return maxAppointmentId + 1;
    }
}