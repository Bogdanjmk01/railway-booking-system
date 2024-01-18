package com.bogdanjmk.railwaybookingsystem.export.pdf;

import com.bogdanjmk.railwaybookingsystem.export.AbstractExporter;
import com.bogdanjmk.railwaybookingsystem.model.Route;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class RoutePdfExporter extends AbstractExporter {
    public void export(List<Route> routes, HttpServletResponse response) throws IOException {
        super.setResponseHeader(response, "application/pdf", ".pdf", "routes_");

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph paragraph = new Paragraph("List of Routes", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);
        table.setWidths(new float[] {1.2f, 3.5f, 3.0f, 3.0f, 3.0f});

        writeTableHeader(table);
        writeTableData(table, routes);

        document.add(table);

        document.close();
    }

    private void writeTableData(PdfPTable table, List<Route> routes) {
        for (Route route : routes) {
            table.addCell(String.valueOf(route.getId()));
            table.addCell(String.valueOf(route.getDepartureStationId()));
            table.addCell(String.valueOf(route.getArrivalStationId()));
            table.addCell(String.valueOf(route.getDistance()));
            table.addCell(String.valueOf(route.getArrivalTime()));
        }
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Departure Station Id", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Arrival Station Id", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Distance", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Arrival Time", font));
        table.addCell(cell);
    }
}
