/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.resource;

import com.thusarakap.healthsystemapi.dao.BillingDAO;
import com.thusarakap.healthsystemapi.exceptions.BillNotFoundException;
import com.thusarakap.healthsystemapi.exceptions.InvalidRequestException;
import com.thusarakap.healthsystemapi.model.Billing;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 *
 * @author Thusaraka
 */

/* Resource class to handle HTTP requests related to billing. */
@Path("/billings")
/* To be able to send and get json outputs and inputs */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BillingResource {
    /* Initializing SLF4J logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingResource.class);

    /* Initializing BillingDAO instance */
    private final BillingDAO billingDAO;

    /* Constructor to initialize BillingDAO */
    public BillingResource() {
        this.billingDAO = new BillingDAO();
    }

    /* GET method to retrieve all billings. */
    @GET
    public List<Billing> getAllBillings() {
        /* Log to indicate that all billings are being retrieved */
        LOGGER.info("Getting all billings.");
        /* Retrieve all billings from DAO */
        return billingDAO.getAllBillings();
    }

    /* GET method to retrieve a billing by ID. */
    @GET
    @Path("/{id}")
    public Response getBillingById(@PathParam("id") int id) {
        /* Log to indicate that a billing is being retrieved by ID */
        LOGGER.info("Getting billing by ID: {}", id);
        try {
            /* Retrieve billing with specified ID from DAO */
            Billing billing = billingDAO.getBillingById(id);
            /* Return retrieved billing with 200 OK HTTP status */
            return Response.ok(billing).build();
        } catch (BillNotFoundException e) {
            /* Log warning if billing with specified ID is not found */
            LOGGER.warn("Billing with ID {} not found.", id);
            /* Return 404 Not Found response with error message */
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /* POST method to add a new billing. */
    @POST
    public Response addBilling(Billing billing) {
        /* Log to indicate that a new billing is being added */
        LOGGER.info("Adding new billing: {}", billing);
        try {
            /* Add new billing to DAO */
            billingDAO.addBilling(billing);
            /* Return 201 Created response with added billing */
            return Response.status(Response.Status.CREATED).entity(billing).build();
        } catch (InvalidRequestException e) {
            /* Log warning if invalid request occurs while adding billing */
            LOGGER.warn("Invalid request while adding billing: {}", e.getMessage());
            /* Return 400 Bad Request response with error message */
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /* PUT method to update an existing billing by ID. */
    @PUT
    @Path("/{id}")
    public Response updateBilling(@PathParam("id") int id, Billing updatedBilling) {
        /* Log to indicate that an existing billing is being updated */
        LOGGER.info("Updating billing with ID: {}", id);
        try {
            /* Update existing billing with specified ID in DAO */
            billingDAO.updateBilling(id, updatedBilling);
            /* Return 200 OK response with updated billing */
            return Response.status(Response.Status.OK).entity(updatedBilling).build();
        } catch (BillNotFoundException e) {
            /* Log warning if billing with specified ID is not found */
            LOGGER.warn("Billing with ID {} not found.", id);
            /* Return 404 Not Found response with error message */
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /* DELETE method to delete a billing by ID. */
    @DELETE
    @Path("/{id}")
    public Response deleteBilling(@PathParam("id") int id) {
        /* Log to indicate that a billing is being deleted by ID */
        LOGGER.info("Deleting billing with ID: {}", id);
        try {
            /* Delete billing with specified ID from DAO */
            billingDAO.deleteBilling(id);
            /* Return 200 OK response */
            return Response.status(Response.Status.OK).build();
        } catch (BillNotFoundException e) {
            /* Log warning if billing with specified ID is not found */
            LOGGER.warn("Billing with ID {} not found.", id);
            /* Return 404 Not Found response with error message */
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}