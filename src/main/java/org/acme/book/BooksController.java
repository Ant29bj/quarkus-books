package org.acme.book;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/api/v1/books")
public class BooksController {

    @Inject
    BookServiceImpl bookService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetAllBook(){
        return Response.status(200).entity(bookService.FindMany()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetOne(@PathParam("id") Long id){
        Optional<Book> oBook = bookService.FindOne(id);
        if (oBook.isEmpty()){
            return Response.status(404).build();
        }
        return Response.status(200).entity(oBook.get()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response CreateOneBook(Book book){
        bookService.CreateOne(book);
        return  Response.status(201).entity(book).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response UpdateOneBoo(
            @PathParam("id")Long id,
            Book book) throws Exception {
        try {
            return Response.status(201).entity(bookService.UpdateOne(id,book)).build();
        }catch (Exception ex){
            return Response.status(409).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteOneBook(@PathParam("id") Long id){
        if (bookService.DeleteOne(id)){
            return Response.status(200).build();
        }else
        {
            return Response.status(409).build();
        }
    }
}
