/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.resource;

import com.thusarakap.healthsystemapi.dao.AppointmentDAO;
import com.thusarakap.healthsystemapi.exception.InvalidRequestException;
import com.thusarakap.healthsystemapi.exception.AppointmentNotFoundException;
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

/* Resource class to handle HTTP requests related to appointments. */
@Path("/appointments")
/* To be able to send and get json outputs and inputs */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppointmentResource {
    /* Initializing SLF4J logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentResource.class);

    /* Initializing AppointmentDAO instance */
    private final AppointmentDAO appointmentDAO;

    /* Constructor to initialize AppointmentDAO */
    public AppointmentResource() {
        this.appointmentDAO = new AppointmentDAO();
    }

    /* GET method to retrieve all appointments. */
    @GET
    public List<Appointment> getAllAppointments() {
        /* Log to indicate that all appointments are being retrieved */
        LOGGER.info("Getting all appointments.");
        /* Retrieve all appointments from DAO */
        return appointmentDAO.getAllAppointments();
    }

    /* GET method to retrieve an appointment by ID. */
    @GET
    /* ID parameter for path to get the id of the appointment to retrive */
    @Path("/{id}")
    public Response getAppointmentById(@PathParam("id") int id) {
        /* Log to indicate that an appointment is being retrieved by ID */
        LOGGER.info("Getting appointment by ID: {}", id);
        try {
            /* Retrieve appointment with specified ID from DAO */
            Appointment appointment = appointmentDAO.getAppointmentById(id);
            /* Return retrieved appointment with 200 OK HTTP status */
            return Response.ok(appointment).build();
        } catch (AppointmentNotFoundException e) {
            /* Log warning if appointment with specified ID is not found */
            LOGGER.warn("Appointment with ID {} not found.", id);
            /* Return 404 Not Found response with error message */
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    /* GET method to retrieve appointments by Doctor ID. */
    @GET
    /* ID parameter for path to get the id of the doctor to retrieve appointments */
    @Path("/doctor/{id}")
    public List<Appointment> getAppointmentsByDoctorId(@PathParam("id") int doctorId) {
        /* Log to indicate that appointments are being retrieved by Doctor ID */
        LOGGER.info("Getting appointments by Doctor ID: {}", doctorId);
        /* Retrieve appointments with specified Doctor ID from DAO */
        List<Appointment> doctorAppointments = appointmentDAO.getAppointmentsByDoctorId(doctorId);
        /* Check if appointments list is empty */
        if (doctorAppointments.isEmpty()) {
            /* Log warning if no appointments are found for Doctor with specified ID */
            LOGGER.warn("No appointments found for Doctor with ID: {}", doctorId);
            /* Throw AppointmentNotFoundException */
            throw new AppointmentNotFoundException("No appointments found for Doctor with ID: " + doctorId);
        }
        /* Return appointments list */
        return doctorAppointments;
    }
    
    /* GET method to retrieve appointments by Patient ID. */
    @GET
    /* ID parameter for path to get the id of the patient to retrieve appointments */
    @Path("/patient/{id}")
    public List<Appointment> getAppointmentsByPatientId(@PathParam("id") int patientId) {
        /* Log to indicate that appointments are being retrieved by Patient ID */
        LOGGER.info("Getting appointments by Patient ID: {}", patientId);
        /* Retrieve appointments with specified Patient ID from DAO */
        List<Appointment> patientAppointments = appointmentDAO.getAppointmentsByPatientId(patientId);
        /* Check if appointments list is empty */
        if (patientAppointments.isEmpty()) {
            /* Log warning if no appointments are found for Patient with specified ID */
            LOGGER.warn("No appointments found for Patient with ID: {}", patientId);
            /* Throw AppointmentNotFoundException */
            throw new AppointmentNotFoundException("No appointments found for Patient with ID: " + patientId);
        }
        /* Return appointments list */
        return patientAppointments;
    }

    /* POST method to add a new appointment. */
    @POST
    public Response addAppointment(Appointment appointment) {
        /* Log to indicate that a new appointment is being added */
        LOGGER.info("Adding new appointment: {}", appointment);
        try {
            /* Add new appointment to DAO */
            appointmentDAO.addAppointment(appointment);
            /* Return 201 Created response with added appointment */
            return Response.status(Response.Status.CREATED).entity(appointment).build();
        } catch (InvalidRequestException e) {
            /* Log warning if invalid request occurs while adding appointment */
            LOGGER.warn("Invalid request while adding appointment: {}", e.getMessage());
            /* Return 400 Bad Request response with error message */
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /* PUT method to update an existing appointment by ID. */
    @PUT
    /* ID parameter for path to get the id of the appointment to update */
    @Path("/{id}")
    public Response updateAppointment(@PathParam("id") int id, Appointment updatedAppointment) {
        /* Log to indicate that an existing appointment is being updated */
        LOGGER.info("Updating appointment with ID: {}", id);
        try {
            /* Update existing appointment with specified ID in DAO */
            appointmentDAO.updateAppointment(id, updatedAppointment);
            /* Return 200 OK response with updated appointment */
            return Response.status(Response.Status.OK).entity(updatedAppointment).build();
        } catch (AppointmentNotFoundException e) {
            /* Log warning if appointment with specified ID is not found */
            LOGGER.warn("Appointment with ID {} not found.", id);
            /* Return 404 Not Found response with error message */
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /* DELETE method to delete an appointment by ID. */
    @DELETE
    /* ID parameter for path to get the id of the appointment to delete */
    @Path("/{id}")
    public Response deleteAppointment(@PathParam("id") int id) {
        /* Log to indicate that an appointment is being deleted by ID */
        LOGGER.info("Deleting appointment with ID: {}", id);
        try {
            /* Delete appointment with specified ID from DAO */
            appointmentDAO.deleteAppointment(id);
            /* Return 200 OK response */
            return Response.status(Response.Status.OK).build();
        } catch (AppointmentNotFoundException e) {
            /* Log warning if appointment with specified ID is not found */
            LOGGER.warn("Appointment with ID {} not found.", id);
            /* Return 404 Not Found response with error message */
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}