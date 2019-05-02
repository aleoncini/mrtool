package it.redhat.mrtool.core.rest;

import it.redhat.mrtool.core.persistence.TripHelper;
import it.redhat.mrtool.model.DateOfTrip;
import it.redhat.mrtool.model.Trip;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/trips")
public class TripService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{associateId}/{year}/{month}")
    public Response tripsOfTheMonth(@PathParam("associateId") String id, @PathParam("year") int year, @PathParam("month") int month) {
        return Response.status(200).entity(new TripHelper().getTrips(id, year, month)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("add")
    public Response insert(String jsonString) {
        new TripHelper().insertOrUpdate(new Trip().build(jsonString));
        return Response.status(200).entity("{\"result\":\"success\"}").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("remove/{associateId}/{year}/{month}/{day}")
    public Response remove(@PathParam("associateId") String id,
                           @PathParam("year") int year,
                           @PathParam("month") int month,
                           @PathParam("day") int day) {
        DateOfTrip date = new DateOfTrip().setYear(year).setMonth(month).setDay(day);
        long result = new TripHelper().delete(id, date);
        if (result <= 0){
            return Response.status(404).build();
        }
        return Response.status(200).entity("{\"result\": \"success\", \"deleted\": " + result + " }").build();
    }

}
