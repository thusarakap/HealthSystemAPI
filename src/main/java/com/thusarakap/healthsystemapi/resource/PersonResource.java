package com.thusarakap.healthsystemapi.resource;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.thusarakap.healthsystemapi.dao.PersonDAO;
import com.thusarakap.healthsystemapi.exception.InvalidRequestException;
import com.thusarakap.healthsystemapi.exception.PersonNotFoundException;
import com.thusarakap.healthsystemapi.model.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Thusaraka
 */

/* Resource class to handle HTTP requests related to persons. */
@Path("/persons")
/* To be able to send and get json outputs and inputs */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource { 
    /* Initializing SLF4J logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);

    private final PersonDAO personDAO;

    /* Constructor to initialize PersonDAO */
    public PersonResource() {
        this.personDAO = new PersonDAO();
    }

  
    /* GET method to retrieve all persons. */
    @GET
    public List<Person> getAllPersons() {
        /* Logger to display "Getting all persons." message */
        LOGGER.info("Getting all persons.");
        /* @return List of all persons. */
        return personDAO.getAllPersons();
    }

    /* GET method to retrieve all persons. */
    @GET
    /* ID parameter for path to get the id of the person to retrive the details from */
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") int id) {
        LOGGER.info("Getting person by ID: {}", id);
        /* Try block to catch any PersonNotFoundExceptions */
        try {
            Person person = personDAO.getPersonById(id);
            return Response.ok(person).build();
        /* Exception handling if person was not found */    
        } catch (PersonNotFoundException e) {
            /* Logs ID of person that wasnt found */  
            LOGGER.warn("Person with ID {} not found.", id);
            /* If person not found, returns 404 Not Found HTTP response */  
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /* POST method to add a new person. */
    @POST
    public Response addPerson(Person person) {
        LOGGER.info("Adding new person: {}", person);
        /* Try block to catch any exceptions */
        try {
            /* Adding a person using addPerson method from PersonDAO */
            personDAO.addPerson(person);
            /* If successful, returns 201 Created HTTP response */
            return Response.status(Response.Status.CREATED).entity(person).build();
            /* Exception handling if unable to add a new person */    
        } catch (InvalidRequestException e) {
            /* Logging the exception occured while adding a new person */
            LOGGER.warn("Invalid request while adding person: {}", e.getMessage());
            /* If exception occurred, returns 400 Bad Request HTTP response */
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /* Put method to update a person by ID. */
    @PUT
    /* ID parameter for path to get the id of the person to update */
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") int id, Person updatedPerson) {
        LOGGER.info("Updating person with ID: {}", id);
        try {
            /* Updating a person using updatePerson method from PersonDAO */
            personDAO.updatePerson(id, updatedPerson);
            /* If successful, returns 200 OK HTTP response */
            return Response.status(Response.Status.OK).entity(updatedPerson).build();
            /* Exception handling if person was not found */    
        } catch (PersonNotFoundException e) {
            /* Logs ID of person that wasnt found */  
            LOGGER.warn("Person with ID {} not found.", id);
            /* If person not found, returns 404 Not Found HTTP response */  
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /* DELETE method to delete a person by ID. */
    @DELETE
    /* ID parameter for path to get the id of the person to delete */
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") int id) {
        LOGGER.info("Deleting person with ID: {}", id);
        /* Try block to catch any exceptions */
        try {
            /* Deleting the person using deletePerson method from PersonDAO */
            personDAO.deletePerson(id);
            /* If successful, returns 200 OK HTTP response */
            return Response.status(Response.Status.OK).build();
        /* Exception handling if person was not found */  
        } catch (PersonNotFoundException e) {
            /* Logs ID of person that wasnt found */ 
            LOGGER.warn("Person with ID {} not found.", id);
            /* If exception occurred, returns 404 Not Found HTTP response */
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
 
}