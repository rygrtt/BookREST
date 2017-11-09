package com.bookrest.resources;


import com.bookrest.annotations.Secured;
import com.bookrest.model.Book;
import com.bookrest.model.Note;
import com.bookrest.model.Person;
import com.bookrest.model.User;
import com.bookrest.svc.BookService;
import com.bookrest.svc.NoteService;
import com.bookrest.svc.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/users")
public class Resources {

    @POST
    @Path("/new")
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

    @POST
    @Secured
    @Path("/{userid}/books/new")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addBook(@PathParam("userid") int userId,
                            @FormParam("title") String title,
                            @FormParam("publisher") String publisher,
                            @FormParam("yearpublished") int yearPublished,
                            @FormParam("edition") String edition,
                            @FormParam("afirstname") String aFname,
                            @FormParam("alastname") String aLname,
                            @FormParam("tfirstname") String tFname,
                            @FormParam("tlastname") String tLname) {

        if (title.isEmpty() || aFname.isEmpty() || aLname.isEmpty() || publisher.isEmpty()
                || yearPublished == 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Book book = new Book(0, userId, yearPublished, publisher, edition, title);

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
