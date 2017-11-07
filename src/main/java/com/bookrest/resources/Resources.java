package com.bookrest.resources;


import com.bookrest.annotations.Secured;
import com.bookrest.model.Book;
import com.bookrest.model.Note;
import com.bookrest.svc.BookService;
import com.bookrest.svc.NoteService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;




@Path("/users")
public class Resources {

    @GET
    @Secured
    @Path("/{userid}/books")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks(@PathParam("userid") int userId) {

        BookService service = new BookService();

        List<Book> books;

        try {
            books = service.getBooks(userId);
            return books;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @GET
    @Secured
    @Path("/{userid}/books/{bookid}/notes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Note> getNotes(@PathParam("userid") int userId, @PathParam("bookid") int bookId) {

        List<Note> notes;

        NoteService service = new NoteService();

        try {

            notes = service.getNotes(bookId);

            return notes;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Secured
    @Path("/{userid}/books/{bookid}/notes/{noteid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Note getNote(@PathParam("userid") int userId, @PathParam("noteid") int noteId) {


        NoteService service = new NoteService();
        try {
           Note note = service.getNote(noteId);


           return note;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
