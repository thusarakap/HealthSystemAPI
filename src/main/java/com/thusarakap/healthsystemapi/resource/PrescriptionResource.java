/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.resource;

import com.thusarakap.healthsystemapi.dao.PrescriptionDAO;
import com.thusarakap.healthsystemapi.exceptions.InvalidRequestException;
import com.thusarakap.healthsystemapi.exceptions.PersonNotFoundException;
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

@Path("/prescriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrescriptionResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionResource.class);
    private final PrescriptionDAO prescriptionDAO;

    public PrescriptionResource() {
        this.prescriptionDAO = new PrescriptionDAO();
    }

    @GET
    public List<Prescription> getAllPrescriptions() {
        LOGGER.info("Getting all prescriptions.");
        return prescriptionDAO.getAllPrescriptions();
    }

    @GET
    @Path("/{id}")
    public Response getPrescriptionById(@PathParam("id") int id) {
        LOGGER.info("Getting prescription by ID: {}", id);
        try {
            Prescription prescription = prescriptionDAO.getPrescriptionById(id);
            return Response.ok(prescription).build();
        } catch (PersonNotFoundException e) {
            LOGGER.warn("Prescription with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response addPrescription(Prescription prescription) {
        LOGGER.info("Adding new prescription: {}", prescription);
        try {
            prescriptionDAO.addPrescription(prescription);
            return Response.status(Response.Status.CREATED).entity(prescription).build();
        } catch (InvalidRequestException e) {
            LOGGER.warn("Invalid request while adding prescription: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatePrescription(@PathParam("id") int id, Prescription updatedPrescription) {
        LOGGER.info("Updating prescription with ID: {}", id);
        try {
            prescriptionDAO.updatePrescription(id, updatedPrescription);
            return Response.status(Response.Status.OK).entity(updatedPrescription).build();
        } catch (PersonNotFoundException e) {
            LOGGER.warn("Prescription with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePrescription(@PathParam("id") int id) {
        LOGGER.info("Deleting prescription with ID: {}", id);
        try {
            prescriptionDAO.deletePrescription(id);
            return Response.status(Response.Status.OK).build();
        } catch (PersonNotFoundException e) {
            LOGGER.warn("Prescription with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
