/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.dao;

/**
 *
 * @author Thusaraka
 */

import com.thusarakap.healthsystemapi.model.Doctor;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    private static List<Doctor> doctorsList = new ArrayList<>();

    // Pre-populate with some sample doctors
    static {
        doctorsList.add(new Doctor("Dr. John Doe", "General Medicine", "john@example.com", "john@example.com", "john@example.com"));
        doctorsList.add(new Doctor("Dr. Jane Smith", "Pediatrics", "jane@example.com", "john@example.com", "john@example.com"));
        // Add more doctors as needed
    }

    public List<Doctor> getAllDoctors() {
        return doctorsList;
    }

    public Doctor getDoctorById(int id) {
        for (Doctor doctor : doctorsList) {
            if (doctor.getId() == id) { 
                return doctor;
            }
        }
        return null;
    }

    public void addDoctor(Doctor doctor) {
        doctorsList.add(doctor);
    }

    public void updateDoctor(Doctor updatedDoctor) {
        for (int i = 0; i < doctorsList.size(); i++) {
            Doctor doctor = doctorsList.get(i);
            if (doctor.getId() == updatedDoctor.getId()) {
                doctorsList.set(i, updatedDoctor);
                return; 
            }
        }
    }

    public void deleteDoctor(int id) {
        doctorsList.removeIf(doctor -> doctor.getId() == id);
    }
}
