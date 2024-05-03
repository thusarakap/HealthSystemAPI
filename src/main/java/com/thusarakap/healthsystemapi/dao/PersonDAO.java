/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.dao;

import com.thusarakap.healthsystemapi.exceptions.PersonNotFoundException;
import com.thusarakap.healthsystemapi.exceptions.InvalidRequestException;
import com.thusarakap.healthsystemapi.model.Person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Thusaraka
 */


public class PersonDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDAO.class);
    private static final List<Person> personList = new ArrayList<>();

    // Static initializer to add sample data
    static {
        personList.add(new Person(1, "John Doe", "john@example.com", "123 Main St"));
        personList.add(new Person(2, "Jane Smith", "jane@example.com", "456 Elm St"));
        personList.add(new Person(3, "Michael Johnson", "michael@example.com", "789 Oak St"));
    }

    public List<Person> getAllPersons() {
        LOGGER.info("Getting all persons.");
        return personList;
    }

    public Person getPersonById(int id) throws PersonNotFoundException {
        LOGGER.info("Getting person by ID: {}", id);
        for (Person person : personList) {
            if (person.getId() == id) {
                return person;
            }
        }
        throw new PersonNotFoundException("Person with id " + id + " not found");
    }

    public void addPerson(Person person) throws InvalidRequestException {
        LOGGER.info("Adding new person: {}", person);
        if (personList.contains(person)) {
            throw new InvalidRequestException("Person already exists");
        }
        int newPersonId = getNextPersonId();
        person.setId(newPersonId);
        personList.add(person);
    }

    public int getNextPersonId() {
        LOGGER.info("Getting next person ID.");
        // Initialize maxPersonId with a value lower than any possible person ID
        int maxPersonId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum person ID
        for (Person person : personList) {
            int personId = person.getId();
            if (personId > maxPersonId) {
                maxPersonId = personId;
            }
        }

        // Increment the maximum person ID to get the next available person ID
        return maxPersonId + 1;
    }

    public void updatePerson(int id, Person updatedPerson) throws PersonNotFoundException {
        LOGGER.info("Updating person with ID: {}", id);
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            if (person.getId() == id) {
                // Preserve the ID from URI
                updatedPerson.setId(id);
                // Update only the specified fields
                if (updatedPerson.getName() != null) {
                    person.setName(updatedPerson.getName());
                }
                if (updatedPerson.getContactInformation() != null) {
                    person.setContactInformation(updatedPerson.getContactInformation());
                }
                if (updatedPerson.getAddress() != null) {
                    person.setAddress(updatedPerson.getAddress());
                }
                personList.set(i, person);
                return;
            }
        }
        throw new PersonNotFoundException("Person with id " + id + " not found");
    }

    public void deletePerson(int id) throws PersonNotFoundException {
        LOGGER.info("Deleting person with ID: {}", id);
        boolean removed = personList.removeIf(person -> person.getId() == id);
        if (!removed) {
            throw new PersonNotFoundException("Person with ID " + id + " not found");
        }
    }
}