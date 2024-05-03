/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.dao;

import com.thusarakap.healthsystemapi.exceptions.InvalidRequestException;
import com.thusarakap.healthsystemapi.exceptions.PersonNotFoundException;
import com.thusarakap.healthsystemapi.model.Billing;
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

public class BillingDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingDAO.class);

    private static final List<Billing> billingList = new ArrayList<>();

    static {
        // Sample patients (You need to replace these with actual patient and doctor objects)
        Patient patient1 = new Patient(1, "John Doe", "john@example.com", "123 Main St", "Chronic illness", "Stable");
        Patient patient2 = new Patient(2, "Jane Smith", "jane@example.com", "456 Elm St", "Allergic rhinitis", "Improving");
        Doctor doctor1 = new Doctor(2, "Dr. John Doe", "john@example.com", "123 Main St", "General Medicine", "123-456-7890");
        Doctor doctor2 = new Doctor(2, "Dr. Jane Smith", "jane@example.com", "456 Elm St", "Pediatrics", "987-654-3210");

        // Adding sample billings
        billingList.add(new Billing(1, 150.0, false, patient1, doctor1));
        billingList.add(new Billing(2, 200.0, true, patient2, doctor2));
        billingList.add(new Billing(3, 100.0, true, patient1, doctor1));
    }
    public List<Billing> getAllBillings() {
        LOGGER.info("Getting all billings.");
        return billingList;
    }

    public Billing getBillingById(int id) throws PersonNotFoundException {
        LOGGER.info("Getting billing by ID: {}", id);
        for (Billing billing : billingList) {
            if (billing.getInvoiceId() == id) {
                return billing;
            }
        }
        throw new PersonNotFoundException("Billing with ID " + id + " not found.");
    }

    public void addBilling(Billing billing) throws InvalidRequestException {
        LOGGER.info("Adding new billing: {}", billing);
        // Check if the billing already exists
        if (billingList.contains(billing)) {
            throw new InvalidRequestException("Billing already exists.");
        }
        // Add the billing to the billingList
        billingList.add(billing);
    }

    public void updateBilling(int id, Billing updatedBilling) throws PersonNotFoundException {
        LOGGER.info("Updating billing with ID: {}", id);
        boolean found = false;
        for (int i = 0; i < billingList.size(); i++) {
            Billing billing = billingList.get(i);
            if (billing.getInvoiceId() == id) {
                billingList.set(i, updatedBilling);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new PersonNotFoundException("Billing with ID " + id + " not found.");
        }
    }

    public void deleteBilling(int id) throws PersonNotFoundException {
        LOGGER.info("Deleting billing with ID: {}", id);
        boolean removed = billingList.removeIf(billing -> billing.getInvoiceId() == id);
        if (!removed) {
            throw new PersonNotFoundException("Billing with ID " + id + " not found.");
        }
    }
}