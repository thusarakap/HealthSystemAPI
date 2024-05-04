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

@Path("/billings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BillingResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingResource.class);

    private final BillingDAO billingDAO;

    public BillingResource() {
        this.billingDAO = new BillingDAO();
    }

    @GET
    public List<Billing> getAllBillings() {
        LOGGER.info("Getting all billings.");
        return billingDAO.getAllBillings();
    }

    @GET
    @Path("/{id}")
    public Response getBillingById(@PathParam("id") int id) {
        LOGGER.info("Getting billing by ID: {}", id);
        try {
            Billing billing = billingDAO.getBillingById(id);
            return Response.ok(billing).build();
        } catch (BillNotFoundException e) {
            LOGGER.warn("Billing with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response addBilling(Billing billing) {
        LOGGER.info("Adding new billing: {}", billing);
        try {
            billingDAO.addBilling(billing);
            return Response.status(Response.Status.CREATED).entity(billing).build();
        } catch (InvalidRequestException e) {
            LOGGER.warn("Invalid request while adding billing: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateBilling(@PathParam("id") int id, Billing updatedBilling) {
        LOGGER.info("Updating billing with ID: {}", id);
        try {
            billingDAO.updateBilling(id, updatedBilling);
            return Response.status(Response.Status.OK).entity(updatedBilling).build();
        } catch (BillNotFoundException e) {
            LOGGER.warn("Billing with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBilling(@PathParam("id") int id) {
        LOGGER.info("Deleting billing with ID: {}", id);
        try {
            billingDAO.deleteBilling(id);
            return Response.status(Response.Status.OK).build();
        } catch (BillNotFoundException e) {
            LOGGER.warn("Billing with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}