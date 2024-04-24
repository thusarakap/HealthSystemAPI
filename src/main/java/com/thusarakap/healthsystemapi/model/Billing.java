/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.model;

/**
 *
 * @author Thusaraka
 */
public class Billing {
    private int invoiceNumber;
    private double amount;
    private boolean paymentStatus;
    private Patient patient;
    private Doctor doctor;

    public Billing(int invoiceNumber, double amount, boolean paymentStatus, Patient patient, Doctor doctor) {
        this.invoiceNumber = invoiceNumber;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.patient = patient;
        this.doctor = doctor;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
