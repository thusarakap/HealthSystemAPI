/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Thusaraka
 */

@Provider
public class MedicalRecordNotFoundExceptionMapper implements ExceptionMapper<MedicalRecordNotFoundException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordNotFoundExceptionMapper.class);

    @Override
    public Response toResponse(MedicalRecordNotFoundException exception) {
        LOGGER.error("MedicalRecordNotFoundException caught: {}", exception.getMessage(), exception);

        return Response.status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}