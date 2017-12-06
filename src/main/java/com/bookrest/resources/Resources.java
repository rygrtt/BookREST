package com.bookrest.resources;


import com.bookrest.model.*;
import com.bookrest.resources.annotations.Secured;
import com.bookrest.svc.BookService;
import com.bookrest.svc.NoteService;
import com.bookrest.svc.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/api")
public class Resources {

    @POST
    @Path("/users/new")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createUser(@FormParam("username") String username,
                               @FormParam("password") String password) {
        User user = new User(0, username, password);
        UserService service = new UserService();

        boolean success = service.createUser(user);

        if (success) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Secured
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks(@QueryParam("userid") int userId) {

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

    @POST
    @Secured
    @Path("/books/new")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addBook(@QueryParam("userid") int userId,
                            @FormParam("title") String title,
                            @FormParam("publisher") String publisher,
                            @FormParam("yearpublished") int yearPublished,
                            @FormParam("edition") String edition,
                            @FormParam("afirstname") String aFname,
                            @FormParam("alastname") String aLname,
                            @FormParam("tfirstname") String tFname,
                            @FormParam("tlastname") String tLname) {

        if (title == null || title.isEmpty() ||
                aFname == null ||aFname.isEmpty() ||
                aLname == null || aLname.isEmpty() ||
                publisher == null || publisher.isEmpty() ||
                yearPublished == 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Book book = new Book(0, userId, yearPublished, publisher, edition, title, 0, 0);

        Person author = new Person(aFname, aLname);

        Person translator = null;

        if (!tFname.isEmpty() && !tLname.isEmpty()) {
            translator = new Person(tFname, tLname);
        }

        BookService svc = new BookService();

       if (svc.insert(book, author, translator, userId)) {
           return Response.status(Response.Status.CREATED).build();
       } else {
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
       }
    }



    @GET
    @Secured
    @Path("/notes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Note> getNotes(@QueryParam("bookid") int bookId) {

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

    @POST
    @Secured
    @Path("/notes/new")
    public Response addNote(@QueryParam("bookid") int bookId,
                            @FormParam("pagenotebegins") int pageNoteBegins,
                            @FormParam("pagenoteends") int pageNoteEnds,
                            @FormParam("text") String text) {

        if (pageNoteBegins < pageNoteEnds || text.isEmpty() || pageNoteBegins == 0 || pageNoteEnds == 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Note note = new Note(bookId, 0, pageNoteBegins, pageNoteEnds, text);
        NoteService svc = new NoteService();

        if (svc.insert(note)) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GET
    @Secured
    @Path("/notes/{noteid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Note getNote(@PathParam("noteid") int noteId) {

        NoteService service = new NoteService();
        try {
            Note note = service.getNote(noteId);
            return note;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @GET
    @Secured
    @Path("/citation")
    @Produces(MediaType.APPLICATION_JSON)
    public Citation getCitation(@QueryParam("noteid") int noteId) {

        NoteService service = new NoteService();

        try {
            return service.getCitation(noteId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
