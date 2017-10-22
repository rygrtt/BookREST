package com.bookrest.api;

import com.bookrest.svc.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/book")
public class BookRestService {

    private ObjectMapper mapper = new ObjectMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        BookService service = new BookService();

        try {
            return mapper.writeValueAsString(service.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
