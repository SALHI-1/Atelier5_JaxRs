package com.fstt.atelier5.atelier5_jax_rs;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {

        // Autorise les requêtes de votre app Angular
        responseContext.getHeaders().add(
                "Access-Control-Allow-Origin", "http://localhost:4200");

        // Autorise les méthodes HTTP
        responseContext.getHeaders().add(
                "Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");

        // Autorise certains en-têtes (comme Content-Type)
        responseContext.getHeaders().add(
                "Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization");
    }
}