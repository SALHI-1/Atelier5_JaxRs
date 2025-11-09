package com.fstt.atelier5.atelier5_jax_rs;

import com.fstt.atelier5.atelier5_jax_rs.entities.HistoCarb;
import com.fstt.atelier5.atelier5_jax_rs.entities.Station;
import com.fstt.atelier5.atelier5_jax_rs.services.HistoCarbService;
import com.fstt.atelier5.atelier5_jax_rs.services.StationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/stations") // URL: /api/stations
@Produces(MediaType.APPLICATION_JSON) // Renvoie du JSON
@Consumes(MediaType.APPLICATION_JSON) // Accepte du JSON
public class StationResource {

    @Inject // Injecte le service CDI
    private StationService stationService;

    @Inject
    private HistoCarbService histoCarbService;

    // --- CRUD Station ---

    @GET
    public Response getAllStations() {
        List<Station> stations = stationService.findAll();
        return Response.ok(stations).build();
    }

    @GET
    @Path("/{id}")
    public Response getStationById(@PathParam("id") Long id) {
        Station station = stationService.findById(id);
        if (station == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(station).build();
    }

    @POST
    public Response createStation(Station station) {
        Station created = stationService.create(station);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateStation(@PathParam("id") Long id, Station station) {
        Station updated = stationService.update(id, station);
        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStation(@PathParam("id") Long id) {
        stationService.delete(id);
        return Response.noContent().build();
    }

    // --- Gestion des Prix (Tâche 3b) ---

    @GET
    @Path("/{id}/prices") // URL: /api/stations/{id}/prices
    public Response getPricesForStation(@PathParam("id") Long stationId) {
        List<HistoCarb> prices = histoCarbService.findByStationId(stationId);
        return Response.ok(prices).build();
    }

    @POST
    @Path("/{stationId}/prices/{carburantId}") // URL: /api/stations/{idStation}/prices/{idCarburant}
    public Response addPrice(@PathParam("stationId") Long stationId,
                             @PathParam("carburantId") Long carburantId,
                             HistoCarb histoCarb) { // Le JSON body contiendra {"date": "...", "prix": ...}

        try {
            HistoCarb newPrice = histoCarbService.addPrice(stationId, carburantId, histoCarb);
            return Response.status(Response.Status.CREATED).entity(newPrice).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}