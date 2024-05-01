/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.dao;

/**
 *
 * @author Thusaraka
 */

import com.thusarakap.healthsystemapi.model.Appointment;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    private static List<Appointment> appointmentList = new ArrayList<>();

    // Method to add a new appointment
    public void addAppointment(Appointment appointment) {
        appointmentList.add(appointment);
    }

    // Method to retrieve all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentList;
    }

    // Method to retrieve appointments for a specific date
    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        List<Appointment> appointmentsOnDate = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            if (appointment.getDate().equals(date)) {
                appointmentsOnDate.add(appointment);
            }
        }
        return appointmentsOnDate;
    }

    // Method to retrieve appointments for a specific doctor
    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
        List<Appointment> doctorAppointments = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            if (appointment.getDoctor().getId() == doctorId) {
                doctorAppointments.add(appointment);
            }
        }
        return doctorAppointments;
    }

    // Method to retrieve appointments for a specific patient
    public List<Appointment> getAppointmentsByPatient(int patientId) {
        List<Appointment> patientAppointments = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            if (appointment.getPatient().getId() == patientId) {
                patientAppointments.add(appointment);
            }
        }
        return patientAppointments;
    }

    // Method to update an existing appointment
    public void updateAppointment(int id, LocalDate date, LocalTime time) {
        for (Appointment appointment : appointmentList) {
            if (appointment.getId() == id) {
                appointment.setDate(date);
                appointment.setTime(time);
                return;
            }
        }
    }

    // Method to delete an appointment
    public void deleteAppointment(int id) {
        appointmentList.removeIf(appointment -> appointment.getId() == id);
    }
}
