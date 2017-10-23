package com.bookrest.resources;

import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
            .entity("You cannot access this resource").build();



}
