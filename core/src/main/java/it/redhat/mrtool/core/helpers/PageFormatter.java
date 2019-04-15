package it.redhat.mrtool.core.helpers;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PageFormatter {
    private Color darkBlue= new Color(0, 65, 85);

    private float borderWidth = 40;
    private float width;
    private float xLeft, xRight, xMiddle;
    private float imageY, imageHeight;

    private void formatTemplate(PDPageContentStream contentStream, PDImageXObject image) throws IOException, URISyntaxException {
        drawBanner(contentStream, image);
        drawHeader(contentStream);
        drawFooter(contentStream);
    }

    private void formatReport(PDPageContentStream contentStream) throws IOException, URISyntaxException {
        writeAssociateInfo(contentStream);
    }

    public void createReport(PDDocument document, boolean templateOnly) throws IOException, URISyntaxException {
        PDPage page = document.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        Path path = Paths.get(ClassLoader.getSystemResource("RHBanner.png").toURI());
        PDImageXObject image = PDImageXObject.createFromFile(path.toAbsolutePath().toString(), document);

        width = page.getBleedBox().getWidth() - (borderWidth * 2);
        xLeft = borderWidth;
        xRight = xLeft + width;
        xMiddle = xLeft + (width / 2);
        imageHeight = (width / image.getWidth()) * image.getHeight();
        imageY = page.getBleedBox().getHeight() - 40 - imageHeight;

        formatTemplate(contentStream, image);
        if (! templateOnly){
            formatReport(contentStream);
        }

        contentStream.close();
    }

    private void writeAssociateInfo(PDPageContentStream contentStream) throws IOException {
        writeCellValue(contentStream, 0, 0, "Andrea Leoncini");
    }

    public boolean checkTemplate(){
        Path path = null;
        try {
            path = Paths.get(ClassLoader.getSystemResource("report.pdf").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return path.toFile().exists();
    }

    private void drawFooter(PDPageContentStream contentStream) throws IOException {
        drawFooterLines(contentStream);
        drawFooterLabels(contentStream);
    }

    private void drawFooterLabels(PDPageContentStream contentStream) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA, 6);
        contentStream.setNonStrokingColor(darkBlue);

        String text = "Total mileage for month/period";
        float text_width = (PDType1Font.HELVETICA.getStringWidth(text) / 1000.0f) * 6;

        contentStream.beginText();
        contentStream.newLineAtOffset(xRight - 60 - text_width, 87);
        contentStream.showText(text);
        contentStream.endText();

        text = "Total cost for month/period";
        text_width = (PDType1Font.HELVETICA.getStringWidth(text) / 1000.0f) * 6;

        contentStream.beginText();
        contentStream.newLineAtOffset(xRight - 60 - text_width, 67);
        contentStream.showText(text);
        contentStream.endText();

    }

    private void drawHeader(PDPageContentStream contentStream) throws IOException {
        drawHeaderLines(contentStream);
        drawHeaderLabels(contentStream);
    }

    private void drawHeaderLabels(PDPageContentStream contentStream) throws IOException {
        writeCellLabel(contentStream, 0, 0, "associate");
        writeCellLabel(contentStream, 0, 1, "cost center");
        writeCellLabel(contentStream, 0, 2, "employee number");
        writeCellLabel(contentStream, 1, 0, "period");
        writeCellLabel(contentStream, 1, 1, "car reg number");
        writeCellLabel(contentStream, 1, 2, "mileage cost rate");
        writeCellLabel(contentStream, 2, 0, "this month total (km)");
        writeCellLabel(contentStream, 2, 1, "total from previous report");
        writeCellLabel(contentStream, 2, 2, "this year total including this month");
    }

    private void drawHeaderLines(PDPageContentStream contentStream) throws IOException {
        contentStream.setNonStrokingColor(darkBlue);
        contentStream.setLineWidth(3);

        contentStream.drawLine(40, imageY - 1, xRight, imageY - 1);
        contentStream.drawLine(40, imageY - 123, xRight, imageY - 123);


        contentStream.setLineWidth(.5f);
        contentStream.setLineDashPattern(new float[]{3,1}, 0);

        contentStream.drawLine(50, imageY - 42, xRight - 10, imageY - 42);
        contentStream.drawLine(50, imageY - 83, xRight - 10, imageY - 83);


        float cellWidth = (width - 20) / 3;
        contentStream.drawLine(50 + cellWidth, imageY - 115, 50 + cellWidth, imageY - 10);
        contentStream.drawLine(50 + (cellWidth*2), imageY - 115, 50 + (cellWidth*2), imageY - 10);
    }

    private void drawFooterLines(PDPageContentStream contentStream) throws IOException {
        contentStream.setNonStrokingColor(darkBlue);
        contentStream.setLineDashPattern(new float[]{}, 0);
        contentStream.setLineWidth(1);

        contentStream.moveTo(xLeft, 60);
        contentStream.lineTo(xRight, 60);
        contentStream.stroke();
        contentStream.moveTo(xLeft, 100);
        contentStream.lineTo(xRight, 100);
        contentStream.stroke();

        contentStream.setLineWidth(.5f);
        contentStream.setLineDashPattern(new float[]{3,1}, 0);

        contentStream.moveTo(xMiddle, 80);
        contentStream.lineTo(xRight, 80);
        contentStream.stroke();
    }

    private void drawBanner(PDPageContentStream contentStream, PDImageXObject image) throws IOException, URISyntaxException {
        contentStream.drawImage(image, xLeft, imageY, width, imageHeight);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
        contentStream.setNonStrokingColor(Color.white);
        contentStream.newLineAtOffset(65, imageY + 36);
        contentStream.showText("Business Mileage Form");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(PDType1Font.COURIER, 10);
        contentStream.setNonStrokingColor(Color.white);
        contentStream.newLineAtOffset(65, imageY + 16);
        contentStream.showText("Produced by MRTool");
        contentStream.endText();
    }

    private void writeCellLabel(PDPageContentStream contentStream, int col, int row, String header) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA, 6);
        contentStream.setNonStrokingColor(darkBlue);

        float cellWidth = width / 3;
        float text_width = (PDType1Font.HELVETICA.getStringWidth(header) / 1000.0f) * 6;
        float x = xLeft + (cellWidth * col) + (cellWidth / 2) - (text_width / 2);
        float y = imageY - (40 * row) - 18;

        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(header);
        contentStream.endText();
    }

    private void writeCellValue(PDPageContentStream contentStream, int col, int row, String value) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        contentStream.setNonStrokingColor(darkBlue);

        float text_width = (PDType1Font.HELVETICA_BOLD.getStringWidth(value) / 1000.0f) * 10;
        float cellWidth = width / 3;
        float x = xLeft + (cellWidth * col) + (cellWidth / 2) - (text_width / 2);
        float y = imageY - (40 * row) - 30;

        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(value);
        contentStream.endText();
    }

}