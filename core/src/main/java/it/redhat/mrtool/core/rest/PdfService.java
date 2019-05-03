package it.redhat.mrtool.core.rest;

import it.redhat.mrtool.core.io.ReportDirectory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.File;

@Path("/pdf")
public class PdfService {

    @GET
    @Path("/latest/{associateId}")
    @Produces("application/pdf")
    public Response downloadFileWithPost(@PathParam("associateId") String id) {
        File fileDownload = new ReportDirectory().getLatestReport(id);
        if (fileDownload == null){
            return Response.status(404).build();
        }
        Response.ResponseBuilder response = Response.ok((Object) fileDownload);
        response.header("Content-Disposition", "attachment;filename=" + fileDownload.getName());
        return response.build();
    }

}
