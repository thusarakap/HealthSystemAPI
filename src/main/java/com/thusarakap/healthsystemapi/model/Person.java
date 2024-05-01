/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.model;

/**
 *
 * @author Thusaraka
 */
public class Person {
    private static int nextId = 1;
    private int id;
    private String name;
    private String contactInformation;
    private String address;
    
    public Person(String name, String contactInformation, String address) {
        this.id = nextId++;
        this.name = name;
        this.contactInformation = contactInformation;
        this.address = address;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getContactInformation() {
        return contactInformation;
    }
    
    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void address(String address) {
        this.address = address;
    }
}

