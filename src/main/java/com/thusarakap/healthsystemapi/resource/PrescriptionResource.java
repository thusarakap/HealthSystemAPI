/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.resource;

import com.thusarakap.healthsystemapi.dao.PrescriptionDAO;
import com.thusarakap.healthsystemapi.exceptions.InvalidRequestException;
import com.thusarakap.healthsystemapi.exceptions.PrescriptionNotFoundException;
import com.thusarakap.healthsystemapi.model.Prescription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Thusaraka
 */

/* Resource class to handle HTTP requests related to prescriptions. */
@Path("/prescriptions")
/* To be able to send and get json outputs and inputs */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrescriptionResource {
    /* Initializing SLF4J logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionResource.class);
    /* Initializing PrescriptionDAO instance */
    private final PrescriptionDAO prescriptionDAO;

    /* Constructor to initialize PrescriptionDAO */
    public PrescriptionResource() {
        this.prescriptionDAO = new PrescriptionDAO();
    }

    /* GET method to retrieve all prescriptions. */
    @GET
    public List<Prescription> getAllPrescriptions() {
        /* Log to indicate that all prescriptions are being retrieved */
        LOGGER.info("Getting all prescriptions.");
        /* Retrieve all prescriptions from DAO */
        return prescriptionDAO.getAllPrescriptions();
    }

    /* GET method to retrieve a prescription by ID. */
    @GET
    @Path("/{id}")
    public Response getPrescriptionById(@PathParam("id") int id) {
        /* Log to indicate that a prescription is being retrieved by ID */
        LOGGER.info("Getting prescription by ID: {}", id);
        try {
            /* Retrieve prescription with specified ID from DAO */
            Prescription prescription = prescriptionDAO.getPrescriptionById(id);
            /* Return retrieved prescription with 200 OK HTTP status */
            return Response.ok(prescription).build();
        } catch (PrescriptionNotFoundException e) {
            /* Log warning if prescription with specified ID is not found */
            LOGGER.warn("Prescription with ID {} not found.", id);
            /* Return 404 Not Found response with error message */
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /* POST method to add a new prescription. */
    @POST
    public Response addPrescription(Prescription prescription) {
        /* Log to indicate that a new prescription is being added */
        LOGGER.info("Adding new prescription: {}", prescription);
        try {
            /* Add new prescription to DAO */
            prescriptionDAO.addPrescription(prescription);
            /* Return 201 Created response with added prescription */
            return Response.status(Response.Status.CREATED).entity(prescription).build();
        } catch (InvalidRequestException e) {
            /* Log warning if invalid request occurs while adding prescription */
            LOGGER.warn("Invalid request while adding prescription: {}", e.getMessage());
            /* Return 400 Bad Request response with error message */
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /* PUT method to update an existing prescription by ID. */
    @PUT
    @Path("/{id}")
    public Response updatePrescription(@PathParam("id") int id, Prescription updatedPrescription) {
        /* Log to indicate that an existing prescription is being updated */
        LOGGER.info("Updating prescription with ID: {}", id);
        try {
            /* Update existing prescription with specified ID in DAO */
            prescriptionDAO.updatePrescription(id, updatedPrescription);
            /* Return 200 OK response with updated prescription */
            return Response.status(Response.Status.OK).entity(updatedPrescription).build();
        } catch (PrescriptionNotFoundException e) {
            /* Log warning if prescription with specified ID is not found */
            LOGGER.warn("Prescription with ID {} not found.", id);
            /* Return 404 Not Found response with error message */
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /* DELETE method to delete a prescription by ID. */
    @DELETE
    @Path("/{id}")
    public Response deletePrescription(@PathParam("id") int id) {
        /* Log to indicate that a prescription is being deleted by ID */
        LOGGER.info("Deleting prescription with ID: {}", id);
        try {
            /* Delete prescription with specified ID from DAO */
            prescriptionDAO.deletePrescription(id);
            /* Return 200 OK response */
            return Response.status(Response.Status.OK).build();
        } catch (PrescriptionNotFoundException e) {
            /* Log warning if prescription with specified ID is not found */
            LOGGER.warn("Prescription with ID {} not found.", id);
            /* Return 404 Not Found response with error message */
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}