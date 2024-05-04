/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.model;

/**
 *
 * @author Thusaraka
 */

/* Model class representing billing information. */
public class Billing {
    private int invoiceId;
    private double amount;
    private boolean paymentStatus;
    private Patient patient;
    private Doctor doctor;

    /* Default constructor */
    public Billing() {
    }
    
    /* Constructor with parameters */
    public Billing(int invoiceId, double amount, boolean paymentStatus, Patient patient, Doctor doctor) {
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.patient = patient;
        this.doctor = doctor;
    }

    /* Getter for invoice ID */
    public int getInvoiceId() {
        return invoiceId;
    }

    /* Setter for invoice ID */
    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    /* Getter for amount */
    public double getAmount() {
        return amount;
    }

    /* Setter for amount */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /* Getter for payment status */
    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    /* Setter for payment status */
    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /* Getter for patient */
    public Patient getPatient() {
        return patient;
    }

    /* Setter for patient */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /* Getter for doctor */
    public Doctor getDoctor() {
        return doctor;
    }

    /* Setter for doctor */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}