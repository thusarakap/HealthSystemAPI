package com.thusarakap.healthsystemapi.resource;

import com.thusarakap.healthsystemapi.dao.PersonDAO;
import com.thusarakap.healthsystemapi.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);
    private PersonDAO personDAO = new PersonDAO();

    @GET
    public Response getAllPersons() {
        try {
            List<Person> persons = personDAO.getAllPersons();
            return Response.ok(persons).build();
        } catch (Exception e) {
            LOGGER.error("Error retrieving persons: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving persons").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") int id) {
        try {
            Person person = personDAO.getPersonById(id);
            if (person != null) {
                return Response.ok(person).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Person not found").build();
            }
        } catch (Exception e) {
            LOGGER.error("Error retrieving person: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving person").build();
        }
    }

    @POST
    public Response addPerson(Person person) {
        try {
            personDAO.addPerson(person);
            return Response.status(Response.Status.CREATED).entity(person).build();
        } catch (Exception e) {
            LOGGER.error("Error adding person: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding person").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") int id, Person updatedPerson) {
        try {
            personDAO.updatePerson(id, updatedPerson);
            return Response.ok().build();
        } catch (Exception e) {
            LOGGER.error("Error updating person: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating person").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") int id) {
        try {
            personDAO.deletePerson(id);
            return Response.ok().build();
        } catch (Exception e) {
            LOGGER.error("Error deleting person: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting person").build();
        }
    }
}
