package com.bookrest.resources;


import com.bookrest.svc.BookService;
import com.bookrest.svc.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class Resources {

    private ObjectMapper mapper = new ObjectMapper();

    @GET
    @Path("/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBooks(@PathParam("userid") int userId) {

        if (userId == 0 || !authenticated(userId)) {
            return null;
        }

        BookService service = new BookService();

        try {
            String result = mapper.writeValueAsString(service.getBooks(userId));

            if (result != null) {
                return result;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @GET
    @Path("/{userid}/{bookid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNotes(@PathParam("userid") int userId, @PathParam("bookid") int bookId) {

        if (userId == 0 || !authenticated(userId)) {
            return null;
        }

        NoteService service = new NoteService();

        try {
            String result = mapper.writeValueAsString(service.getNotes(bookId));

            if (result != null) {
                return result;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Path("/{userid}/{bookid}/{noteid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNote() {
        // authenticate user
        // if authenticated return json of note
        // else return fail/unauthenticated message
        return null;
    }

    private boolean authenticated(int userId) {
        return true;
    }


}
