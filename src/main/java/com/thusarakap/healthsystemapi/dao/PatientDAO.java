/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.dao;

import com.thusarakap.healthsystemapi.model.Patient;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thusaraka
 */

public class PatientDAO {
    private static List<Patient> patientList = new ArrayList<>();

    // Pre-populate with some sample patients
    static {
        patientList.add(new Patient("John Doe", "john@example.com", "123 Main St", "No significant medical history", "Stable"));
        patientList.add(new Patient("Jane Smith", "jane@example.com", "456 Elm St", "Allergies: None", "Good"));
        // Add more patients as needed
    }

    public List<Patient> getAllPatients() {
        return patientList;
    }

    public Patient getPatientById(int id) {
        for (Patient patient : patientList) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    public void addPatient(Patient patient) {
        patientList.add(patient);
    }

    public void updatePatient(Patient updatedPatient) {
        for (int i = 0; i < patientList.size(); i++) {
            Patient patient = patientList.get(i);
            if (patient.getId() == updatedPatient.getId()) {
                patientList.set(i, updatedPatient);
                return;
            }
        }
    }

    public void deletePatient(int id) {
        patientList.removeIf(patient -> patient.getId() == id);
    }
}
