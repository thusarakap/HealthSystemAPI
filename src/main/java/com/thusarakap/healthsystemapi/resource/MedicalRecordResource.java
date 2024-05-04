/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.resource;

/**
 *
 * @author Thusaraka
 */

import com.thusarakap.healthsystemapi.dao.MedicalRecordDAO;
import com.thusarakap.healthsystemapi.exception.InvalidRequestException;
import com.thusarakap.healthsystemapi.exception.MedicalRecordNotFoundException;
import com.thusarakap.healthsystemapi.model.MedicalRecord;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* Resource class to handle HTTP requests related to medical records. */
@Path("/medical-records")
/* To be able to send and get json outputs and inputs */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedicalRecordResource {
    /* Initializing SLF4J logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordResource.class);
    /* Initializing MedicalRecordDAO instance */
    private final MedicalRecordDAO medicalRecordDAO;

    /* Constructor to initialize MedicalRecordDAO */
    public MedicalRecordResource() {
        this.medicalRecordDAO = new MedicalRecordDAO();
    }

    /* GET method to retrieve all medical records. */
    @GET
    public Response getAllMedicalRecords() {
        /* Log to indicate that all medical records are being retrieved */
        LOGGER.info("Getting all medical records.");
        /* Retrieve all medical records from DAO */
        return Response.ok(medicalRecordDAO.getAllMedicalRecords()).build();
    }

    /* GET method to retrieve a medical record by ID. */
    @GET
    @Path("/{id}")
    public Response getMedicalRecordById(@PathParam("id") int id) {
        /* Log to indicate that a medical record is being retrieved by ID */
        LOGGER.info("Getting medical record by ID: {}", id);
        try {
            /* Retrieve medical record with specified ID from DAO */
            MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecordById(id);
            /* Return retrieved medical record with 200 OK HTTP status */
            return Response.ok(medicalRecord).build();
        } catch (MedicalRecordNotFoundException e) {
            /* Log warning if medical record with specified ID is not found */
            LOGGER.warn("Medical record with ID {} not found.", id);
            /* Return 404 Not Found response with error message */
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /* POST method to add a new medical record. */
    @POST
    public Response addMedicalRecord(MedicalRecord medicalRecord) {
        /* Log to indicate that a new medical record is being added */
        LOGGER.info("Adding new medical record: {}", medicalRecord);
        try {
            /* Add new medical record to DAO */
            medicalRecordDAO.addMedicalRecord(medicalRecord);
            /* Return 201 Created response with added medical record */
            return Response.status(Response.Status.CREATED).entity(medicalRecord).build();
        } catch (InvalidRequestException e) {
            /* Log warning if invalid request occurs while adding medical record */
            LOGGER.warn("Invalid request while adding medical record: {}", e.getMessage());
            /* Return 400 Bad Request response with error message */
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /* PUT method to update an existing medical record by ID. */
    @PUT
    @Path("/{id}")
    public Response updateMedicalRecord(@PathParam("id") int id, MedicalRecord updatedMedicalRecord) {
        /* Log to indicate that an existing medical record is being updated */
        LOGGER.info("Updating medical record with ID: {}", id);
        try {
            /* Update existing medical record with specified ID in DAO */
            medicalRecordDAO.updateMedicalRecord(id, updatedMedicalRecord);
            /* Return 200 OK response with updated medical record */
            return Response.status(Response.Status.OK).entity(updatedMedicalRecord).build();
        } catch (MedicalRecordNotFoundException e) {
            /* Log warning if medical record with specified ID is not found */
            LOGGER.warn("Medical record with ID {} not found.", id);
            /* Return 404 Not Found response with error message */
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /* DELETE method to delete a medical record by ID. */
    @DELETE
    @Path("/{id}")
    public Response deleteMedicalRecord(@PathParam("id") int id) {
        /* Log to indicate that a medical record is being deleted by ID */
        LOGGER.info("Deleting medical record with ID: {}", id);
        try {
            /* Delete medical record with specified ID from DAO */
            medicalRecordDAO.deleteMedicalRecord(id);
            /* Return 200 OK response */
            return Response.status(Response.Status.OK).build();
        } catch (MedicalRecordNotFoundException e) {
            /* Log warning if medical record with specified ID is not found */
            LOGGER.warn("Medical record with ID {} not found.", id);
            /* Return 404 Not Found response with error message */
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    /* POST method to add a diagnosis for a medical record by ID. */
    @POST
    @Path("/{id}/diagnoses")
    public Response addDiagnosis(@PathParam("id") int id, String diagnosis) {
        /* Log to indicate that a diagnosis is being added for a medical record */
        LOGGER.info("Adding diagnosis for medical record with ID {}: {}", id, diagnosis);
        try {
            /* Add diagnosis to medical record with specified ID in DAO */
            medicalRecordDAO.addDiagnosis(id, diagnosis);
            /* Return 200 OK response */
            return Response.status(Response.Status.OK).build();
        } catch (MedicalRecordNotFoundException e) {
            /* Log warning if medical record with specified ID is not found */
            LOGGER.warn("Medical record with ID {} not found.", id);
            /* Return 404 Not Found response with error message */
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /* POST method to add a treatment for a medical record by ID. */
    @POST
    @Path("/{id}/treatments")
    public Response addTreatment(@PathParam("id") int id, String treatment) {
        /* Log to indicate that a treatment is being added for a medical record */
        LOGGER.info("Adding treatment for medical record with ID {}: {}", id, treatment);
        try {
            /* Add treatment to medical record with specified ID in DAO */
            medicalRecordDAO.addTreatment(id, treatment);
            /* Return 200 OK response */
            return Response.status(Response.Status.OK).build();
        } catch (MedicalRecordNotFoundException e) {
            /* Log warning if medical record with specified ID is not found */
            LOGGER.warn("Medical record with ID {} not found.", id);
            /* Return 404 Not Found response with error message */
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    /* POST method to add additional notes for a medical record by ID. */
    @POST
    @Path("/{id}/additional-notes")
    public Response addAdditionalNotes(@PathParam("id") int id, String note) {
        /* Log to indicate that additional notes are being added for a medical record */
        LOGGER.info("Adding additional note for medical record with ID {}: {}", id, note);
        try {
            /* Add additional notes to medical record with specified ID in DAO */
            medicalRecordDAO.addAdditionalNotes(id, note);
            /* Return 200 OK response */
            return Response.status(Response.Status.OK).build();
        } catch (MedicalRecordNotFoundException e) {
            /* Log warning if medical record with specified ID is not found */
            LOGGER.warn("Medical record with ID {} not found.", id);
            /* Return 404 Not Found response with error message */
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

}