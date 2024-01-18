package com.bogdanjmk.railwaybookingsystem.export.pdf;

import com.bogdanjmk.railwaybookingsystem.export.AbstractExporter;
import com.bogdanjmk.railwaybookingsystem.model.Seat;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class SeatPdfExporter extends AbstractExporter {
    public void export(List<Seat> seats, HttpServletResponse response) throws IOException {
        super.setResponseHeader(response, "application/pdf", ".pdf", "seats_");

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph paragraph = new Paragraph("List of Seats", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);
        table.setWidths(new float[] {1.2f, 3.5f, 3.0f, 3.0f, 3.0f});

        writeTableHeader(table);
        writeTableData(table, seats);

        document.add(table);

        document.close();
    }

    private void writeTableData(PdfPTable table, List<Seat> seats) {
        for (Seat seat : seats) {
            table.addCell(String.valueOf(seat.getId()));
            table.addCell(seat.getSeatNumber());
            table.addCell(String.valueOf(seat.getTrainId()));
            table.addCell(String.valueOf(seat.getCarNumber()));
            table.addCell(seat.getClassName());
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

        cell.setPhrase(new Phrase("Seat Number", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Train Id", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Car Number", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Class Name", font));
        table.addCell(cell);
    }
}
