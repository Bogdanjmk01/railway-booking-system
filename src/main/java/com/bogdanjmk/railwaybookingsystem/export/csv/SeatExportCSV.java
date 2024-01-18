package com.bogdanjmk.railwaybookingsystem.export.csv;

import com.bogdanjmk.railwaybookingsystem.export.AbstractExporter;
import com.bogdanjmk.railwaybookingsystem.model.Seat;
import jakarta.servlet.http.HttpServletResponse;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.List;

public class SeatExportCSV extends AbstractExporter {
    public void export (List<Seat> seats, HttpServletResponse response) throws IOException {
        if (response.isCommitted() || seats.isEmpty()) return;

        super.setResponseHeader(response, "text/csv", ".csv", "seats_");

        try (ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE)) {
            String[] csvHeader = {"Seat Id", "Seat Number", "Train Id", "Car Number", "Class Name"};
            String[] fieldMapping = {"id", "seatNumber", "trainId", "carNumber", "className"};

            csvWriter.writeHeader(csvHeader);

            for (Seat seat : seats) {
                csvWriter.write(seat, fieldMapping);
            }
        }
    }
}
