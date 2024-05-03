/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thusarakap.healthsystemapi.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Thusaraka
 */

@Provider
public class InvalidRequestExceptionMapper implements ExceptionMapper<InvalidRequestException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidRequestExceptionMapper.class);

    @Override
    public Response toResponse(InvalidRequestException exception) {
        LOGGER.error("InvalidRequestException caught: {}", exception.getMessage(), exception);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}