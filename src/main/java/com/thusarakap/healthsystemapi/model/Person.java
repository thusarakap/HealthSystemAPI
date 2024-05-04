/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.model;

/**
 *
 * @author Thusaraka
 */

// Model class representing a person.
public class Person {
    private int id;
    private String name;
    private String contactInformation;
    private String address;
    
    // Default constructor
    public Person() {
    }
    
    // Constructor with parameters
    public Person(int id, String name, String contactInformation, String address) {
        this.id = id;
        this.name = name;
        this.contactInformation = contactInformation;
        this.address = address;
    }
    
    // Getter for ID
    public int getId() {
        return id;
    }
    
    // Setter for ID
    public void setId(int id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }
    
    // Setter for name
    public void setName(String name) {
        this.name = name;
    }
    
    // Getter for contact information
    public String getContactInformation() {
        return contactInformation;
    }
    
    // Setter for contact information
    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }
    
    // Getter for address
    public String getAddress() {
        return address;
    }
    
    // Setter for address
    public void setAddress(String address) {
        this.address = address;
    }
}