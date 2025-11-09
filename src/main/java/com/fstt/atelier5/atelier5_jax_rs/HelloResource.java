package com.fstt.atelier5.atelier5_jax_rs;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "{\"message\":\"Hello, World!\"}";
    }
}