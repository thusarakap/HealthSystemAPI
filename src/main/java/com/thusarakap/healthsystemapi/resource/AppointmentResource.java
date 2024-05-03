/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.resource;

import com.thusarakap.healthsystemapi.dao.AppointmentDAO;
import com.thusarakap.healthsystemapi.exceptions.InvalidRequestException;
import com.thusarakap.healthsystemapi.exceptions.AppointmentNotFoundException;
import com.thusarakap.healthsystemapi.model.Appointment;

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

@Path("/appointments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppointmentResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentResource.class);

    private final AppointmentDAO appointmentDAO;

    public AppointmentResource() {
        this.appointmentDAO = new AppointmentDAO();
    }

    @GET
    public List<Appointment> getAllAppointments() {
        LOGGER.info("Getting all appointments.");
        return appointmentDAO.getAllAppointments();
    }

    @GET
    @Path("/{id}")
    public Response getAppointmentById(@PathParam("id") int id) {
        LOGGER.info("Getting appointment by ID: {}", id);
        try {
            Appointment appointment = appointmentDAO.getAppointmentById(id);
            return Response.ok(appointment).build();
        } catch (AppointmentNotFoundException e) {
            LOGGER.warn("Appointment with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    @GET
    @Path("/doctor/{id}")
    public List<Appointment> getAppointmentsByDoctorId(@PathParam("id") int doctorId) {
        LOGGER.info("Getting appointments by Doctor ID: {}", doctorId);
        List<Appointment> doctorAppointments = appointmentDAO.getAppointmentsByDoctorId(doctorId);
        if (doctorAppointments.isEmpty()) {
            LOGGER.warn("No appointments found for Doctor with ID: {}", doctorId);
            throw new AppointmentNotFoundException("No appointments found for Doctor with ID: " + doctorId);
        }
        return doctorAppointments;
    }
    
    @GET
    @Path("/patient/{id}")
    public List<Appointment> getAppointmentsByPatientId(@PathParam("id") int patientId) {
        LOGGER.info("Getting appointments by Patient ID: {}", patientId);
        List<Appointment> patientAppointments = appointmentDAO.getAppointmentsByPatientId(patientId);
        if (patientAppointments.isEmpty()) {
            LOGGER.warn("No appointments found for Patient with ID: {}", patientId);
            throw new AppointmentNotFoundException("No appointments found for Patient with ID: " + patientId);
        }
        return patientAppointments;
    }

    @POST
    public Response addAppointment(Appointment appointment) {
        LOGGER.info("Adding new appointment: {}", appointment);
        try {
            appointmentDAO.addAppointment(appointment);
            return Response.status(Response.Status.CREATED).entity(appointment).build();
        } catch (InvalidRequestException e) {
            LOGGER.warn("Invalid request while adding appointment: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateAppointment(@PathParam("id") int id, Appointment updatedAppointment) {
        LOGGER.info("Updating appointment with ID: {}", id);
        try {
            appointmentDAO.updateAppointment(id, updatedAppointment);
            return Response.status(Response.Status.OK).entity(updatedAppointment).build();
        } catch (AppointmentNotFoundException e) {
            LOGGER.warn("Appointment with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAppointment(@PathParam("id") int id) {
        LOGGER.info("Deleting appointment with ID: {}", id);
        try {
            appointmentDAO.deleteAppointment(id);
            return Response.status(Response.Status.OK).build();
        } catch (AppointmentNotFoundException e) {
            LOGGER.warn("Appointment with ID {} not found.", id);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}