package it.redhat.mrtool.core.pdf;

import it.redhat.mrtool.core.helpers.PageFormatter;
import it.redhat.mrtool.model.Associate;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URISyntaxException;

public class Report {
    private Associate associate;
    private int year;
    private int month;
    private String fileName;
    private File reportFile;

    public Report(String associateUuid, int year, int month, String fileName){
        this.associate = new Associate().setUuid(associateUuid);
        this.year = year;
        this.month = month;
        this.fileName = fileName;
    }

    public Report make() {
        return this.make(false);
    }

    public Report make(boolean templateOnly) {
        PDDocument document = createDocument();
        formatDocument(document, templateOnly);
        save(document);
        return this;
    }

    public File getReportFile(){
        return reportFile;
    }

    private void formatDocument(PDDocument document, boolean templateOnly) {
        try {
            new PageFormatter().createReport(document, templateOnly);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private PDDocument createDocument(){
        PDDocument document = new PDDocument();
        document.addPage(new PDPage());
        return document;
    }

    private void save(PDDocument document){
        String errorMessage = "";
        try {
            document.save(fileName);
            reportFile = new File(fileName);
        } catch (Throwable t) {
            StringWriter trace = new StringWriter();
            t.printStackTrace(new PrintWriter(trace, true));
            errorMessage = trace.toString();
        }
        if (errorMessage.length() > 0){
            System.out.println(errorMessage);
        }
    }

}