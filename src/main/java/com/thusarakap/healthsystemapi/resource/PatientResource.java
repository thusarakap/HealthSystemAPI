/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.resource;

import com.thusarakap.healthsystemapi.dao.PatientDAO;
import com.thusarakap.healthsystemapi.exceptions.InvalidRequestException;
import com.thusarakap.healthsystemapi.exceptions.PersonNotFoundException;
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

@Path("/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientResource.class);

    private final PatientDAO patientDAO;

    public PatientResource() {
        this.patientDAO = new PatientDAO();
    }

    @GET
    public Response getAllPatients() {
        LOGGER.info("Getting all patients.");
        try {
            List<Patient> patients = patientDAO.getAllPatients();
            return Response.ok(patients).build();
        } catch (Exception e) {
            LOGGER.error("Error getting all patients: {}", e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error getting all patients.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getPatientById(@PathParam("id") int id) {
        LOGGER.info("Getting patient by ID: {}", id);
        try {
            Patient patient = patientDAO.getPatientById(id);
            return Response.ok(patient).build();
        } catch (PersonNotFoundException e) {
            LOGGER.warn("Patient with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.error("Error getting patient with ID {}: {}", id, e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error getting patient with ID " + id).build();
        }
    }

    @POST
    public Response addPatient(Patient patient) {
        LOGGER.info("Adding new patient: {}", patient);
        try {
            patientDAO.addPatient(patient);
            return Response.status(Response.Status.CREATED).entity(patient).build();
        } catch (InvalidRequestException e) {
            LOGGER.warn("Invalid request while adding patient: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.error("Error adding new patient: {}", e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding new patient.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatePatient(@PathParam("id") int id, Patient updatedPatient) {
        LOGGER.info("Updating patient with ID: {}", id);
        try {
            patientDAO.updatePatient(id, updatedPatient);
            return Response.status(Response.Status.OK).entity(updatedPatient).build();
        } catch (PersonNotFoundException e) {
            LOGGER.warn("Patient with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.error("Error updating patient with ID {}: {}", id, e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating patient with ID " + id).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePatient(@PathParam("id") int id) {
        LOGGER.info("Deleting patient with ID: {}", id);
        try {
            patientDAO.deletePatient(id);
            return Response.status(Response.Status.OK).build();
        } catch (PersonNotFoundException e) {
            LOGGER.warn("Patient with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.error("Error deleting patient with ID {}: {}", id, e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting patient with ID " + id).build();
        }
    }
}