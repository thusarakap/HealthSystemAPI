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
import com.thusarakap.healthsystemapi.exceptions.InvalidRequestException;
import com.thusarakap.healthsystemapi.exceptions.MedicalRecordNotFoundException;
import com.thusarakap.healthsystemapi.model.MedicalRecord;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/medical-records")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedicalRecordResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordResource.class);
    private final MedicalRecordDAO medicalRecordDAO;

    public MedicalRecordResource() {
        this.medicalRecordDAO = new MedicalRecordDAO();
    }

    @GET
    public Response getAllMedicalRecords() {
        LOGGER.info("Getting all medical records.");
        return Response.ok(medicalRecordDAO.getAllMedicalRecords()).build();
    }

    @GET
    @Path("/{id}")
    public Response getMedicalRecordById(@PathParam("id") int id) {
        LOGGER.info("Getting medical record by ID: {}", id);
        try {
            MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecordById(id);
            return Response.ok(medicalRecord).build();
        } catch (MedicalRecordNotFoundException e) {
            LOGGER.warn("Medical record with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response addMedicalRecord(MedicalRecord medicalRecord) {
        LOGGER.info("Adding new medical record: {}", medicalRecord);
        try {
            medicalRecordDAO.addMedicalRecord(medicalRecord);
            return Response.status(Response.Status.CREATED).entity(medicalRecord).build();
        } catch (InvalidRequestException e) {
            LOGGER.warn("Invalid request while adding medical record: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateMedicalRecord(@PathParam("id") int id, MedicalRecord updatedMedicalRecord) {
        LOGGER.info("Updating medical record with ID: {}", id);
        try {
            medicalRecordDAO.updateMedicalRecord(id, updatedMedicalRecord);
            return Response.status(Response.Status.OK).entity(updatedMedicalRecord).build();
        } catch (MedicalRecordNotFoundException e) {
            LOGGER.warn("Medical record with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMedicalRecord(@PathParam("id") int id) {
        LOGGER.info("Deleting medical record with ID: {}", id);
        try {
            medicalRecordDAO.deleteMedicalRecord(id);
            return Response.status(Response.Status.OK).build();
        } catch (MedicalRecordNotFoundException e) {
            LOGGER.warn("Medical record with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    @POST
    @Path("/{id}/diagnoses")
    public Response addDiagnosis(@PathParam("id") int id, String diagnosis) {
        LOGGER.info("Adding diagnosis for medical record with ID {}: {}", id, diagnosis);
        try {
            medicalRecordDAO.addDiagnosis(id, diagnosis);
            return Response.status(Response.Status.OK).build();
        } catch (MedicalRecordNotFoundException e) {
            LOGGER.warn("Medical record with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{id}/treatments")
    public Response addTreatment(@PathParam("id") int id, String treatment) {
        LOGGER.info("Adding treatment for medical record with ID {}: {}", id, treatment);
        try {
            medicalRecordDAO.addTreatment(id, treatment);
            return Response.status(Response.Status.OK).build();
        } catch (MedicalRecordNotFoundException e) {
            LOGGER.warn("Medical record with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    @POST
    @Path("/{id}/additional-notes")
    public Response addAdditionalNotes(@PathParam("id") int id, String note) {
        LOGGER.info("Adding additional note for medical record with ID {}: {}", id, note);
        try {
            medicalRecordDAO.addAdditionalNotes(id, note);
            return Response.status(Response.Status.OK).build();
        } catch (MedicalRecordNotFoundException e) {
            LOGGER.warn("Medical record with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

}
