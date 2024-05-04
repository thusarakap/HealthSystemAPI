/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.resource;

import com.thusarakap.healthsystemapi.dao.DoctorDAO;
import com.thusarakap.healthsystemapi.exception.InvalidRequestException;
import com.thusarakap.healthsystemapi.exception.PersonNotFoundException;
import com.thusarakap.healthsystemapi.model.Doctor;

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

// Resource class to handle HTTP requests related to doctors. 
@Path("/doctors")
// To be able to send and get json outputs and inputs 
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoctorResource {
    // Initializing SLF4J logger 
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorResource.class);

    // Initializing DoctorDAO instance 
    private final DoctorDAO doctorDAO;

    // Constructor to initialize DoctorDAO 
    public DoctorResource() {
        this.doctorDAO = new DoctorDAO();
    }

    // GET method to retrieve all doctors. 
    @GET
    public List<Doctor> getAllDoctors() {
        // Log to indicate that all doctors are being retrieved 
        LOGGER.info("Getting all doctors.");
        // Retrieve all doctors from DAO 
        return doctorDAO.getAllDoctors();
    }

    // GET method to retrieve a doctor by ID. 
    @GET
    @Path("/{id}")
    public Response getDoctorById(@PathParam("id") int id) {
        // Log to indicate that a doctor is being retrieved by ID 
        LOGGER.info("Getting doctor by ID: {}", id);
        try {
            // Retrieve doctor with specified ID from DAO 
            Doctor doctor = doctorDAO.getDoctorById(id);
            // Return retrieved doctor with 200 OK HTTP status 
            return Response.ok(doctor).build();
        } catch (PersonNotFoundException e) {
            // Log warning if doctor with specified ID is not found 
            LOGGER.warn("Doctor with ID {} not found.", id);
            // Return 404 Not Found response with error message 
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    // POST method to add a new doctor. 
    @POST
    public Response addDoctor(Doctor doctor) {
        // Log to indicate that a new doctor is being added 
        LOGGER.info("Adding new doctor: {}", doctor);
        try {
            // Add new doctor to DAO 
            doctorDAO.addDoctor(doctor);
            // Return 201 Created response with added doctor 
            return Response.status(Response.Status.CREATED).entity(doctor).build();
        } catch (InvalidRequestException e) {
            // Log warning if invalid request occurs while adding doctor 
            LOGGER.warn("Invalid request while adding doctor: {}", e.getMessage());
            // Return 400 Bad Request response with error message 
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // PUT method to update an existing doctor by ID. 
    @PUT
    @Path("/{id}")
    public Response updateDoctor(@PathParam("id") int id, Doctor updatedDoctor) {
        // Log to indicate that an existing doctor is being updated 
        LOGGER.info("Updating doctor with ID: {}", id);
        try {
            // Update existing doctor with specified ID in DAO 
            doctorDAO.updateDoctor(id, updatedDoctor);
            // Return 200 OK response with updated doctor 
            return Response.status(Response.Status.OK).entity(updatedDoctor).build();
        } catch (PersonNotFoundException e) {
            // Log warning if doctor with specified ID is not found 
            LOGGER.warn("Doctor with ID {} not found.", id);
            // Return 404 Not Found response with error message 
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    // DELETE method to delete a doctor by ID. 
    @DELETE
    @Path("/{id}")
    public Response deleteDoctor(@PathParam("id") int id) {
        // Log to indicate that a doctor is being deleted by ID 
        LOGGER.info("Deleting doctor with ID: {}", id);
        try {
            // Delete doctor with specified ID from DAO 
            doctorDAO.deleteDoctor(id);
            // Return 200 OK response 
            return Response.status(Response.Status.OK).build();
        } catch (PersonNotFoundException e) {
            // Log warning if doctor with specified ID is not found 
            LOGGER.warn("Doctor with ID {} not found.", id);
            // Return 404 Not Found response with error message 
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}