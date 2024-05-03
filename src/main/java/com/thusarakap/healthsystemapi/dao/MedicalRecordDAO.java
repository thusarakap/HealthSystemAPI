package com.thusarakap.healthsystemapi.dao;

import com.thusarakap.healthsystemapi.exceptions.InvalidRequestException;
import com.thusarakap.healthsystemapi.exceptions.PersonNotFoundException;
import com.thusarakap.healthsystemapi.model.MedicalRecord;
import com.thusarakap.healthsystemapi.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordDAO.class);

    private static final List<MedicalRecord> medicalRecordList = new ArrayList<>();

    static {
        // Sample patients (You need to replace these with actual patient objects)
        Patient patient1 = new Patient(1, "John Doe", "john@example.com", "123 Main St", "Chronic illness", "Stable");
        Patient patient2 = new Patient(2, "Jane Smith", "jane@example.com", "456 Elm St", "Allergic rhinitis", "Improving");
        Patient patient3 = new Patient(3, "Michael Johnson", "michael@example.com", "789 Oak St", "Hypertension", "Critical");

        // Adding sample medical records
        medicalRecordList.add(new MedicalRecord(1, patient1));
        medicalRecordList.add(new MedicalRecord(2, patient2));
        medicalRecordList.add(new MedicalRecord(3, patient3));
    }
    
    public List<MedicalRecord> getAllMedicalRecords() {
        LOGGER.info("Getting all medical records.");
        return medicalRecordList;
    }

    public MedicalRecord getMedicalRecordById(int id) throws PersonNotFoundException {
        LOGGER.info("Getting medical record by ID: {}", id);
        for (MedicalRecord medicalRecord : medicalRecordList) {
            if (medicalRecord.getMedicalRecordId() == id) {
                return medicalRecord;
            }
        }
        throw new PersonNotFoundException("Medical record with ID " + id + " not found.");
    }

    public void addMedicalRecord(MedicalRecord medicalRecord) throws InvalidRequestException {
        LOGGER.info("Adding new medical record: {}", medicalRecord);
        // Check if the medical record already exists
        if (medicalRecordList.contains(medicalRecord)) {
            throw new InvalidRequestException("Medical record already exists.");
        }
        // Add the medical record to the medicalRecordList
        medicalRecordList.add(medicalRecord);
    }

    public void updateMedicalRecord(int id, MedicalRecord updatedMedicalRecord) throws PersonNotFoundException {
        LOGGER.info("Updating medical record with ID: {}", id);
        boolean found = false;
        for (int i = 0; i < medicalRecordList.size(); i++) {
            MedicalRecord medicalRecord = medicalRecordList.get(i);
            if (medicalRecord.getMedicalRecordId() == id) {
                medicalRecordList.set(i, updatedMedicalRecord);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new PersonNotFoundException("Medical record with ID " + id + " not found.");
        }
    }

    public void deleteMedicalRecord(int id) throws PersonNotFoundException {
        LOGGER.info("Deleting medical record with ID: {}", id);
        boolean removed = medicalRecordList.removeIf(medicalRecord -> medicalRecord.getMedicalRecordId() == id);
        if (!removed) {
            throw new PersonNotFoundException("Medical record with ID " + id + " not found.");
        }
    }
    
    public void addDiagnosis(int medicalRecordId, String diagnosis) throws PersonNotFoundException {
        LOGGER.info("Adding diagnosis for medical record with ID {}: {}", medicalRecordId, diagnosis);
        MedicalRecord medicalRecord = getMedicalRecordById(medicalRecordId);
        medicalRecord.addDiagnosis(diagnosis);
    }

    public void addTreatment(int medicalRecordId, String treatment) throws PersonNotFoundException {
        LOGGER.info("Adding treatment for medical record with ID {}: {}", medicalRecordId, treatment);
        MedicalRecord medicalRecord = getMedicalRecordById(medicalRecordId);
        medicalRecord.addTreatment(treatment);
    }
    
    public void addAdditionalNotes(int medicalRecordId, String note) throws PersonNotFoundException {
        LOGGER.info("Adding additional note for medical record with ID {}: {}", medicalRecordId, note);
        MedicalRecord medicalRecord = getMedicalRecordById(medicalRecordId);
        medicalRecord.addAdditionalNotes(note);
    }
}