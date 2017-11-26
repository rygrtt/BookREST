package com.bookrest.resources;

import com.bookrest.model.User;
import com.bookrest.svc.UserService;
import com.bookrest.utils.KeyFinder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.Date;

@Path("/login")
public class LoginEndpoint {
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("password") String password) {

        try {

            // Authenticate the user using the credentials provided
            authenticate(username, password);

            // Issue a token for the user
            String token = issueToken(username);

            // Return the token on the response
            return Response.ok(token, MediaType.TEXT_PLAIN).build();


        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private void authenticate(String username, String password) throws Exception {
        UserService service = new UserService();
        User user = service.find(username, password);

        if (user == null) {
            throw new Exception();
        }
    }

    private String issueToken(String username) {

        // pulling a pre-generated key from file
        // would probably want something more robust in a "real" system
        Key key = KeyFinder.getKey();

        // creates String representation of a jwt object with expiration in 10 mins
        // and user name in subject field
        Date expirationDate = new Date(System.currentTimeMillis() + 60000000);

        return Jwts.builder().setSubject(username).setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, key).compact();
    }

}
