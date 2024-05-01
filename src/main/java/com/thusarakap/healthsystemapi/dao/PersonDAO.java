/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.dao;

import java.util.List;
import com.thusarakap.healthsystemapi.model.Person;
import java.util.ArrayList;

/**
 *
 * @author Thusaraka
 */
public class PersonDAO {
    private List<Person> personList;
    
    public PersonDAO() {
        this.personList = new ArrayList<>();
    }
    
    public void addPerson(Person person) {
        personList.add(person);
    }
    
    public List<Person> getAllPersons() {
        return new ArrayList<>(personList);
    }
    
    public Person getPersonById(int id) {
        for (Person person : personList) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }
    
    public void updatePerson(int id, Person updatedPerson) {
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            if (person.getId() == id) {
                personList.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Person with ID " + id + " not found");
    }
    
    public void deletePerson(int id) {
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            if (person.getId() == id) {
                personList.remove(i);
                return;                
            }            
        }
        throw new IllegalArgumentException("Person with ID " + id + " not found");
    }
}
