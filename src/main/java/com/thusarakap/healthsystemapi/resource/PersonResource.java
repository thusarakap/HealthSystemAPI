package com.thusarakap.healthsystemapi.resource;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.thusarakap.healthsystemapi.dao.PersonDAO;
import com.thusarakap.healthsystemapi.exceptions.InvalidRequestException;
import com.thusarakap.healthsystemapi.exceptions.PersonNotFoundException;
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

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);

    private final PersonDAO personDAO;

    public PersonResource() {
        this.personDAO = new PersonDAO();
    }

    @GET
    public List<Person> getAllPersons() {
        LOGGER.info("Getting all persons.");
        return personDAO.getAllPersons();
    }

    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") int id) {
        LOGGER.info("Getting person by ID: {}", id);
        try {
            Person person = personDAO.getPersonById(id);
            return Response.ok(person).build();
        } catch (PersonNotFoundException e) {
            LOGGER.warn("Person with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response addPerson(Person person) {
        LOGGER.info("Adding new person: {}", person);
        try {
            personDAO.addPerson(person);
            return Response.status(Response.Status.CREATED).entity(person).build();
        } catch (InvalidRequestException e) {
            LOGGER.warn("Invalid request while adding person: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") int id, Person updatedPerson) {
        LOGGER.info("Updating person with ID: {}", id);
        try {
            personDAO.updatePerson(id, updatedPerson);
            return Response.status(Response.Status.OK).entity(updatedPerson).build();
        } catch (PersonNotFoundException e) {
            LOGGER.warn("Person with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") int id) {
        LOGGER.info("Deleting person with ID: {}", id);
        try {
            personDAO.deletePerson(id);
            return Response.status(Response.Status.OK).build();
        } catch (PersonNotFoundException e) {
            LOGGER.warn("Person with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}