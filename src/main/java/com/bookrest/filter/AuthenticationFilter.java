package com.bookrest.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
            .entity("You cannot access this resource").build();


    public void filter(ContainerRequestContext requestContext) throws IOException {

        // test token for expiration/signature

        MultivaluedMap<String, String> headers = requestContext.getHeaders();

        List<String> authorization = headers.get("Authorization");

        if (authorization == null || authorization.isEmpty()) {
            requestContext.abortWith(ACCESS_DENIED);
        }






    }
}
