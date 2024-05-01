/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.dao;

/**
 *
 * @author Thusaraka
 */

import com.thusarakap.healthsystemapi.model.Billing;
import java.util.ArrayList;
import java.util.List;

public class BillingDAO {
    private static List<Billing> billingList = new ArrayList<>();

    // Method to add a new billing record
    public void addBilling(Billing billing) {
        billingList.add(billing);
    }

    // Method to retrieve all billing records
    public List<Billing> getAllBillings() {
        return billingList;
    }

    // Method to retrieve billing records for a specific patient
    public List<Billing> getBillingsByPatient(int patientId) {
        List<Billing> patientBillings = new ArrayList<>();
        for (Billing billing : billingList) {
            if (billing.getPatient().getId() == patientId) {
                patientBillings.add(billing);
            }
        }
        return patientBillings;
    }

    // Method to retrieve billing records for a specific doctor
    public List<Billing> getBillingsByDoctor(int doctorId) {
        List<Billing> doctorBillings = new ArrayList<>();
        for (Billing billing : billingList) {
            if (billing.getDoctor().getId() == doctorId) {
                doctorBillings.add(billing);
            }
        }
        return doctorBillings;
    }

    // Method to update an existing billing record
    public void updateBilling(int invoiceNumber, double amount, boolean paymentStatus) {
        for (Billing billing : billingList) {
            if (billing.getInvoiceNumber() == invoiceNumber) {
                billing.setAmount(amount);
                billing.setPaymentStatus(paymentStatus);
                return;
            }
        }
    }

    // Method to delete a billing record by invoice number
    public void deleteBilling(int invoiceNumber) {
        billingList.removeIf(billing -> billing.getInvoiceNumber() == invoiceNumber);
    }
}
