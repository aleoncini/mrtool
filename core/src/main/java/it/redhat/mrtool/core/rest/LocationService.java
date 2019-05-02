package it.redhat.mrtool.core.rest;

import it.redhat.mrtool.core.persistence.LocationHelper;
import it.redhat.mrtool.model.Location;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/locations")
public class LocationService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("dest") String dest) {
        return Response.status(200).entity(new LocationHelper().getLocations(dest)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("add")
    public Response insert(String jsonString) {
        new LocationHelper().insertOrUpdate(new Location().build(jsonString));
        return Response.status(200).entity("{\"result\":\"success\"}").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("remove")
    public Response remove(String jsonString) {
        LocationHelper helper = new LocationHelper();
        long result = new LocationHelper().delete(jsonString);
        if (result <= 0){
            return Response.status(404).build();
        }
        return Response.status(200).entity("{\"result\":\"success\"}").build();
    }

}
