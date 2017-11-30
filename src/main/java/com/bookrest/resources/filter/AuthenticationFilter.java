/*
* I am in special debt for much of this code to this response
* https://stackoverflow.com/questions/26777083/best-practice-for-rest-token-based-authentication-with-jax-rs-and-jersey
*/

package com.bookrest.resources.filter;

import com.bookrest.resources.annotations.Secured;
import com.bookrest.utils.KeyFinder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Key;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHENTICATION_SCHEME = "Bearer";

    public void filter(ContainerRequestContext requestContext) throws IOException {

        // fetch authorization header from request
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (!containsToken(authHeader)) {
            abortAsUnauthorized(requestContext);
            return;
        }

        // trim token by length of name of authentication type (Bearer)
        String token = authHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

        try {
            validateToken(token);
        } catch (Exception e) {
            abortAsUnauthorized(requestContext);
        }

    }

    private boolean containsToken(String authHeader) {
        // makes sure header is not null and begins with Bearer
        return authHeader != null && authHeader.toLowerCase()
                .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");


    }

    private void abortAsUnauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME)
                        .build());
    }

    private void validateToken(String token) throws Exception {

        Key key = KeyFinder.getKey();

        // returns collection of all claims contained in the token
        Jws<Claims> jws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);

        // gets expiration time from among claims and compares against current system time
        if (jws.getBody().getExpiration().getTime() < System.currentTimeMillis()) {
            throw new Exception();
        }


        /*String[] parts = token.split("\\.");

        System.out.println(parts[0]);
        System.out.println(new String(Base64.getDecoder().decode(parts[0])));

        System.out.println(parts[1]);
        System.out.println(new String(Base64.getDecoder().decode(parts[1])));

        System.out.println(parts[2]);
        System.out.println(new String(Base64.getDecoder().decode(parts[2])));
*/

    }
}
