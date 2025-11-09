package com.fstt.atelier5.atelier5_jax_rs;

import com.fstt.atelier5.atelier5_jax_rs.entities.Carburant;
import com.fstt.atelier5.atelier5_jax_rs.services.CarburantService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/carburants") // URL: /api/carburants
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarburantResource {

    @Inject
    private CarburantService carburantService;

    @GET
    public Response getAllCarburants() {
        List<Carburant> carburants = carburantService.findAll();
        return Response.ok(carburants).build();
    }

    @GET
    @Path("/{id}")
    public Response getCarburantById(@PathParam("id") Long id) {
        Carburant carburant = carburantService.findById(id);
        if (carburant == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(carburant).build();
    }

    @POST
    public Response createCarburant(Carburant carburant) {
        Carburant created = carburantService.create(carburant);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCarburant(@PathParam("id") Long id, Carburant carburant) {
        Carburant updated = carburantService.update(id, carburant);
        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCarburant(@PathParam("id") Long id) {
        carburantService.delete(id);
        return Response.noContent().build();
    }
}