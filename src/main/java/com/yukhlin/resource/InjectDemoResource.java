package com.yukhlin.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectDemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

    @GET
    @Path("/annotations")
    public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
                                            @HeaderParam("customParam") String headerParam,
                                            @CookieParam("name") String cookie) {
        return "Matrix param: " + matrixParam + ", header param: " + headerParam + ", cookie: " + cookie;
    }

    @GET
    @Path("context")
    public String getParamsUsingContext(@Context UriInfo uriInfo,
                                        @Context HttpHeaders httpHeaders) {
        String cookies = httpHeaders.getCookies().toString();
        return "uri path: " + uriInfo.getAbsolutePath().toString() + ", cookies: " + cookies;
    }
}