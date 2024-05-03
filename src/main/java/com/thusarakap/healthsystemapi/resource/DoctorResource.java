/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.resource;

import com.thusarakap.healthsystemapi.dao.DoctorDAO;
import com.thusarakap.healthsystemapi.exceptions.InvalidRequestException;
import com.thusarakap.healthsystemapi.exceptions.PersonNotFoundException;
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

@Path("/doctors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoctorResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorResource.class);

    private final DoctorDAO doctorDAO;

    public DoctorResource() {
        this.doctorDAO = new DoctorDAO();
    }

    @GET
    public List<Doctor> getAllDoctors() {
        LOGGER.info("Getting all doctors.");
        return doctorDAO.getAllDoctors();
    }

    @GET
    @Path("/{id}")
    public Response getDoctorById(@PathParam("id") int id) {
        LOGGER.info("Getting doctor by ID: {}", id);
        try {
            Doctor doctor = doctorDAO.getDoctorById(id);
            return Response.ok(doctor).build();
        } catch (PersonNotFoundException e) {
            LOGGER.warn("Doctor with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response addDoctor(Doctor doctor) {
        LOGGER.info("Adding new doctor: {}", doctor);
        try {
            doctorDAO.addDoctor(doctor);
            return Response.status(Response.Status.CREATED).entity(doctor).build();
        } catch (InvalidRequestException e) {
            LOGGER.warn("Invalid request while adding doctor: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateDoctor(@PathParam("id") int id, Doctor updatedDoctor) {
        LOGGER.info("Updating doctor with ID: {}", id);
        try {
            doctorDAO.updateDoctor(id, updatedDoctor);
            return Response.status(Response.Status.OK).entity(updatedDoctor).build();
        } catch (PersonNotFoundException e) {
            LOGGER.warn("Doctor with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDoctor(@PathParam("id") int id) {
        LOGGER.info("Deleting doctor with ID: {}", id);
        try {
            doctorDAO.deleteDoctor(id);
            return Response.status(Response.Status.OK).build();
        } catch (PersonNotFoundException e) {
            LOGGER.warn("Doctor with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}