package com.bogdanjmk.railwaybookingsystem.export.pdf;

import com.bogdanjmk.railwaybookingsystem.export.AbstractExporter;
import com.bogdanjmk.railwaybookingsystem.model.Station;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class StationPdfExporter extends AbstractExporter {
    public void export(List<Station> stations, HttpServletResponse response) throws IOException {
        super.setResponseHeader(response, "application/pdf", ".pdf", "stations_");

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph paragraph = new Paragraph("List of Stations", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);
        table.setWidths(new float[] {1.2f, 3.5f, 3.0f});

        writeTableHeader(table);
        writeTableData(table, stations);

        document.add(table);

        document.close();
    }

    private void writeTableData(PdfPTable table, List<Station> stations) {
        for (Station station : stations) {
            table.addCell(String.valueOf(station.getId()));
            table.addCell(station.getStationName());
            table.addCell(station.getLocation());
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

        cell.setPhrase(new Phrase("Station Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Station Location", font));
        table.addCell(cell);
    }
}
