/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.dao;

import com.thusarakap.healthsystemapi.exception.BillNotFoundException;
import com.thusarakap.healthsystemapi.exception.InvalidRequestException;
import com.thusarakap.healthsystemapi.model.Billing;
import com.thusarakap.healthsystemapi.model.Doctor;
import com.thusarakap.healthsystemapi.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/*
 *
 * @author Thusaraka
 */

 // Data Access Object (DAO) class for handling operations related to billing. 
public class BillingDAO {
    // Initializing SLF4J logger 
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingDAO.class);

    // Initializing list to store billings 
    private static final List<Billing> billingList = new ArrayList<>();

    // Static initializer to add sample data 
    static {
        // Sample patients
        Patient patient1 = new Patient(1, "Peter", "peter@gmail.com", "123 Colombo", "Chronic illness", "Good");
        Patient patient2 = new Patient(2, "Anne Green", "anne@example.com", "456 Boston", "Allergic rhinitis", "Better");
        
        Doctor doctor1 = new Doctor(1, "Dr. Chamari Palliyeguru", "chamari@gmail.com", "789 Kandy St", "Cardiology", "0711977578");
        Doctor doctor2 = new Doctor(2, "Dr. Tom John", "tom@gmail.com", "456 colombo", "Pediatrics", "0713937578");

        // Adding sample billings
        billingList.add(new Billing(1, 10450.0, false, patient1, doctor1));
        billingList.add(new Billing(2, 10650.0, true, patient2, doctor2));
        billingList.add(new Billing(3, 20300.0, true, patient1, doctor1));
    }
    
    // Method to retrieve all billings 
    public List<Billing> getAllBillings() {
        LOGGER.info("Getting all billings.");
        return billingList;
    }

    // Method to retrieve a billing by ID 
    public Billing getBillingById(int id) throws BillNotFoundException {
        LOGGER.info("Getting billing by ID: {}", id);
        // Iterate through the list to find the billing with specified ID 
        for (Billing billing : billingList) {
            if (billing.getInvoiceId() == id) {
                return billing;
            }
        }
        // Throw exception if billing with specified ID is not found 
        throw new BillNotFoundException("Billing with ID " + id + " not found.");
    }

    // Method to add a new billing 
    public void addBilling(Billing billing) throws InvalidRequestException {
        LOGGER.info("Adding new billing: {}", billing);
        // Check if the billing already exists
        if (billingList.contains(billing)) {
            throw new InvalidRequestException("Billing already exists.");
        }
        // Generate a new ID for the bill and add to the list 
        billing.setInvoiceId(getNextInvoiceId());
        // Add the bill to the billingList
        billingList.add(billing);
    }

    // Method to get the next available invoice ID 
    public int getNextInvoiceId() {
        LOGGER.info("Getting next prescription ID.");
        // Initialize maxInvoiceId with a value lower than any possible invoice ID
        int maxInvoiceId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum invoice ID
        for (Billing billing : billingList) {
            int invoiceId = billing.getInvoiceId();
            if (invoiceId > maxInvoiceId) {
                maxInvoiceId = invoiceId;
            }
        }

        // Increment the maximum invoice ID to get the next available invoice ID
        return maxInvoiceId + 1;
    }

    // Method to update a billing by ID 
    public void updateBilling(int id, Billing updatedBilling) throws BillNotFoundException {
        LOGGER.info("Updating billing with ID: {}", id);
        boolean found = false;
        // Iterate through the list to find the billing with specified ID 
        for (int i = 0; i < billingList.size(); i++) {
            Billing billing = billingList.get(i);
            if (billing.getInvoiceId() == id) {
                billingList.set(i, updatedBilling);
                found = true;
                break;
            }
        }
        // Throw exception if billing with specified ID is not found 
        if (!found) {
            throw new BillNotFoundException("Billing with ID " + id + " not found.");
        }
    }

    // Method to delete a billing by ID 
    public void deleteBilling(int id) throws BillNotFoundException {
        LOGGER.info("Deleting billing with ID: {}", id);
        // Use lambda expression to remove billing from list 
        boolean removed = billingList.removeIf(billing -> billing.getInvoiceId() == id);
        // Throw exception if billing with specified ID is not found 
        if (!removed) {
            throw new BillNotFoundException("Billing with ID " + id + " not found.");
        }
    }
}