/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.dao;

/**
 *
 * @author Thusaraka
 */

import com.thusarakap.healthsystemapi.model.MedicalRecord;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {
    private static List<MedicalRecord> medicalRecordList = new ArrayList<>();

    // Method to add a new medical record
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordList.add(medicalRecord);
    }

    // Method to retrieve all medical records
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordList;
    }

    // Method to retrieve a medical record by ID
    public MedicalRecord getMedicalRecordById(int id) {
        for (MedicalRecord medicalRecord : medicalRecordList) {
            if (medicalRecord.getPatient().getId() == id) {
                return medicalRecord;
            }
        }
        return null; // Return null if medical record with given ID is not found
    }

    // Method to update an existing medical record
    public void updateMedicalRecord(int id, MedicalRecord updatedMedicalRecord) {
        for (int i = 0; i < medicalRecordList.size(); i++) {
            MedicalRecord medicalRecord = medicalRecordList.get(i);
            if (medicalRecord.getPatient().getId() == id) {
                medicalRecordList.set(i, updatedMedicalRecord);
                return;
            }
        }
    }

    // Method to delete a medical record by ID
    public void deleteMedicalRecord(int id) {
        medicalRecordList.removeIf(medicalRecord -> medicalRecord.getPatient().getId() == id);
    }
}
