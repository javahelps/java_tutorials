package com.javahelps.helloworld;

import javax.ws.rs.*;

@Path("/service")
public class HelloService {

    @GET
    @Path("/")
    public String get() {
        System.out.println("GET invoked");
        return "Hello world!";
    }

    @POST
    @Path("/")
    public void post() {
        System.out.println("POST invoked");
    }

    @PUT
    @Path("/")
    public void put() {
        System.out.println("PUT invoked");
    }

    @DELETE
    @Path("/")
    public void delete() {
        System.out.println("DELETE invoked");
    }
}