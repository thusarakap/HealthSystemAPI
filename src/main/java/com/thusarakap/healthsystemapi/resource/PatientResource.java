/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.resource;

import com.thusarakap.healthsystemapi.dao.PatientDAO;
import com.thusarakap.healthsystemapi.exception.InvalidRequestException;
import com.thusarakap.healthsystemapi.exception.PersonNotFoundException;
import com.thusarakap.healthsystemapi.model.Patient;

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

// Resource class to handle HTTP requests related to patients. 
@Path("/patients")
// To be able to send and get json outputs and inputs 
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {
    // Initializing SLF4J logger 
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientResource.class);

    // Initializing PatientDAO instance 
    private final PatientDAO patientDAO;

    // Constructor to initialize PatientDAO 
    public PatientResource() {
        this.patientDAO = new PatientDAO();
    }

    // GET method to retrieve all patients. 
    @GET
    public Response getAllPatients() {
        // Log to indicate that all patients are being retrieved 
        LOGGER.info("Getting all patients.");
        try {
            // Retrieve all patients from DAO 
            List<Patient> patients = patientDAO.getAllPatients();
            // Return retrieved patients with 200 OK HTTP status 
            return Response.ok(patients).build();
        } catch (Exception e) {
            // Log error if exception occurs during retrieval 
            LOGGER.error("Error getting all patients: {}", e.getMessage(), e);
            // Return 500 Internal Server Error response with error message 
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error getting all patients.").build();
        }
    }

    // GET method to retrieve a patient by ID. 
    @GET
    @Path("/{id}")
    public Response getPatientById(@PathParam("id") int id) {
        // Log to indicate that a patient is being retrieved by ID 
        LOGGER.info("Getting patient by ID: {}", id);
        try {
            // Retrieve patient with specified ID from DAO 
            Patient patient = patientDAO.getPatientById(id);
            // Return retrieved patient with 200 OK HTTP status 
            return Response.ok(patient).build();
        } catch (PersonNotFoundException e) {
            // Log warning if patient with specified ID is not found 
            LOGGER.warn("Patient with ID {} not found.", id);
            // Return 404 Not Found response with error message 
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            // Log error if exception occurs during retrieval 
            LOGGER.error("Error getting patient with ID {}: {}", id, e.getMessage(), e);
            // Return 500 Internal Server Error response with error message 
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error getting patient with ID " + id).build();
        }
    }

    // POST method to add a new patient. 
    @POST
    public Response addPatient(Patient patient) {
        // Log to indicate that a new patient is being added 
        LOGGER.info("Adding new patient: {}", patient);
        try {
            // Add new patient to DAO 
            patientDAO.addPatient(patient);
            // Return 201 Created response with added patient 
            return Response.status(Response.Status.CREATED).entity(patient).build();
        } catch (InvalidRequestException e) {
            // Log warning if invalid request occurs while adding patient 
            LOGGER.warn("Invalid request while adding patient: {}", e.getMessage());
            // Return 400 Bad Request response with error message 
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            // Log error if exception occurs during addition 
            LOGGER.error("Error adding new patient: {}", e.getMessage(), e);
            // Return 500 Internal Server Error response with error message 
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding new patient.").build();
        }
    }

    // PUT method to update an existing patient by ID. 
    @PUT
    @Path("/{id}")
    public Response updatePatient(@PathParam("id") int id, Patient updatedPatient) {
        // Log to indicate that an existing patient is being updated 
        LOGGER.info("Updating patient with ID: {}", id);
        try {
            // Update existing patient with specified ID in DAO 
            patientDAO.updatePatient(id, updatedPatient);
            // Return 200 OK response with updated patient 
            return Response.status(Response.Status.OK).entity(updatedPatient).build();
        } catch (PersonNotFoundException e) {
            // Log warning if patient with specified ID is not found 
            LOGGER.warn("Patient with ID {} not found.", id);
            // Return 404 Not Found response with error message 
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            // Log error if exception occurs during update 
            LOGGER.error("Error updating patient with ID {}: {}", id, e.getMessage(), e);
            // Return 500 Internal Server Error response with error message 
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating patient with ID " + id).build();
        }
    }

    // DELETE method to delete a patient by ID. 
    @DELETE
    @Path("/{id}")
    public Response deletePatient(@PathParam("id") int id) {
        // Log to indicate that a patient is being deleted by ID 
        LOGGER.info("Deleting patient with ID: {}", id);
        try {
            // Delete patient with specified ID from DAO 
            patientDAO.deletePatient(id);
            // Return 200 OK response 
            return Response.status(Response.Status.OK).build();
        } catch (PersonNotFoundException e) {
            // Log warning if patient with specified ID is not found 
            LOGGER.warn("Patient with ID {} not found.", id);
            // Return 404 Not Found response with error message 
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            // Log error if exception occurs during deletion 
            LOGGER.error("Error deleting patient with ID {}: {}", id, e.getMessage(), e);
            // Return 500 Internal Server Error response with error message 
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting patient with ID " + id).build();
        }
    }
}