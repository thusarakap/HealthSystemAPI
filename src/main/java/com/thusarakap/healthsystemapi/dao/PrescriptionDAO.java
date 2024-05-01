/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.dao;

/**
 *
 * @author Thusaraka
 */

import com.thusarakap.healthsystemapi.model.Prescription;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {
    private static List<Prescription> prescriptionList = new ArrayList<>();

    // Method to add a new prescription
    public void addPrescription(Prescription prescription) {
        prescriptionList.add(prescription);
    }

    // Method to retrieve all prescriptions
    public List<Prescription> getAllPrescriptions() {
        return prescriptionList;
    }

    // Method to retrieve prescriptions for a specific patient
    public List<Prescription> getPrescriptionsByPatient(int patientId) {
        List<Prescription> patientPrescriptions = new ArrayList<>();
        for (Prescription prescription : prescriptionList) {
            if (prescription.getPatient().getId() == patientId) {
                patientPrescriptions.add(prescription);
            }
        }
        return patientPrescriptions;
    }

    // Method to retrieve prescriptions for a specific doctor
    public List<Prescription> getPrescriptionsByDoctor(int doctorId) {
        List<Prescription> doctorPrescriptions = new ArrayList<>();
        for (Prescription prescription : prescriptionList) {
            if (prescription.getDoctor().getId() == doctorId) {
                doctorPrescriptions.add(prescription);
            }
        }
        return doctorPrescriptions;
    }

    // Method to update an existing prescription
    public void updatePrescription(int id, String medication, String dosage, String instructions, String duration) {
        for (Prescription prescription : prescriptionList) {
            if (prescription.getId() == id) {
                prescription.setMedication(medication);
                prescription.setDosage(dosage);
                prescription.setInstructions(instructions);
                prescription.setDuration(duration);
                return;
            }
        }
    }

    // Method to delete a prescription
    public void deletePrescription(int id) {
        prescriptionList.removeIf(prescription -> prescription.getId() == id);
    }
}
