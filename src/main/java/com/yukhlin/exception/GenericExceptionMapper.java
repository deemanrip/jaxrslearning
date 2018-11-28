package com.yukhlin.exception;

import com.yukhlin.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "http://test.com");
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();

    }
}
