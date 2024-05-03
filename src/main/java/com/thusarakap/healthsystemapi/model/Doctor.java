/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.model;

/**
 *
 * @author Thusaraka
 */

public class Doctor extends Person{
    private String specialisation;
    private String contactDetails;
    
    public Doctor() {
        // Default constructor needed for Jackson deserialization
    }
    
    public Doctor(int id, String name, String contactInformation, String address, String specialisation, String contactDetails) {
        super(id, name, contactInformation, address);
        this.specialisation = specialisation;
        this.contactDetails = contactDetails;
    }
    
    public String getSpecialisation() {
        return specialisation;
    }
    
    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
    
    public String getContactDetails() {
        return contactDetails;
    }
    
    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
}

