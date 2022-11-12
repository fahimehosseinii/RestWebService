/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mcgill.cccs425.assignment1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcgill.cccs425.assignment1.repo.BookClass;
import com.mcgill.cccs425.assignment1.repo.BookEntity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;  
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import org.json.simple.JSONObject;

/**
 * REST Web Service
 *
 * @author Himeh
 */
@Path("book")
@XmlRootElement
public class BookService {

    @Context
    private UriInfo context;
    ObjectMapper mapper = new ObjectMapper();
    private static final BookClass bookStore = new BookClass();

    /**
     * Creates a new instance of BookService
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    public BookService() {}

    /**
     * Retrieves representation of an instance of com.mcgill.cccs425.assignment1.service.BookService
     * @return an instance of java.lang.String
     */

    @GET
    @Path("/getBook/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("id") int id) throws JsonProcessingException {

        if (!bookStore.checkId(id)){
            String msg = "This book id does not exist in the library.\n";
            return Response.status(Response.Status.BAD_REQUEST). entity(msg).
                                           type(MediaType.TEXT_PLAIN).build();
        }
        BookEntity book = bookStore.getBook(id);
        String jsonData = mapper.writeValueAsString(book);
        return Response.status(Response.Status.OK).entity(jsonData).build();

    }

    
    @GET
    @Path("/getSize")
//    @Produces(MediaType.APPLICATION_JSON)
    public String getSize() throws JsonProcessingException {

        String msg = "This total number of the books in the library is:  ";
        return msg+bookStore.getSize();
        }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addBook") 
    public String addBook(String jsonContent) throws JsonProcessingException {
        BookEntity newBook = mapper.readValue(jsonContent, BookEntity.class);	
        bookStore.addBook(newBook);
        return "Success";
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() throws JsonProcessingException {
       String book = bookStore.getBooks();
        return Response.status(Response.Status.OK).entity(book).build();
    }


    @DELETE
    @Path("/deleteId/{id}")
    public String deleteId(@PathParam("id") int id) {
        
       int removedId =  bookStore.removeBook(id);
       return "The id: "+removedId+" is removed successfully.";
       
    }
    
    @DELETE
    @Path("/deleteAll")
    public String deleteAll() {
       bookStore.removeAll();
       return "All ids are removed successfully.";
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateBook/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("id") int id, String book) throws JsonProcessingException {
       
        if (!bookStore.checkId(id)){
            String msg = "This book id does not exist in the library to get updated so the request is skipped.\n";

            return Response.status(Response.Status.BAD_REQUEST). entity(msg).
                                           type(MediaType.TEXT_PLAIN).build();
        }
              
        BookEntity newBook = mapper.readValue(book, BookEntity.class);
        BookEntity bookInfo = bookStore.updateBook(id,  newBook);
        String jsonData = mapper.writeValueAsString(bookInfo);
        return Response.status(Response.Status.OK).entity(bookInfo).build();
    }
}
