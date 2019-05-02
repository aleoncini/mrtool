package it.redhat.mrtool.core.rest;

import it.redhat.mrtool.core.pdf.Report;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;

@Path("/reports")
public class ReportService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{associateId}/{year}/{month}")
    public Response scheduleBuild(@PathParam("associateId") String id, @PathParam("year") int year, @PathParam("month") int month) {
        CompletableFuture.runAsync(() -> {
            Report report = new Report(id, year, month).make();
        });
        return Response.status(200).entity("{ \"result\": \"Build successfully scheduled\" }").build();
    }

}
