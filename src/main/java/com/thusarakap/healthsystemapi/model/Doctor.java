/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.model;

/**
 *
 * @author Thusaraka
 */

// Model class representing a doctor, extending the Person class.
public class Doctor extends Person {
    private String specialisation;
    private String contactDetails;
    
    // Default constructor
    public Doctor() {
        super();
    }
    
    // Constructor with parameters
    public Doctor(int id, String name, String contactInformation, String address, String specialisation, String contactDetails) {
        super(id, name, contactInformation, address);
        this.specialisation = specialisation;
        this.contactDetails = contactDetails;
    }
    
    // Getter for specialisation
    public String getSpecialisation() {
        return specialisation;
    }
    
    // Setter for specialisation
    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
    
    // Getter for contact details
    public String getContactDetails() {
        return contactDetails;
    }
    
    // Setter for contact details
    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
}
