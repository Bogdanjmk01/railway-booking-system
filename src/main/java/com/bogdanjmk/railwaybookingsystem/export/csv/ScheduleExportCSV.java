package com.bogdanjmk.railwaybookingsystem.export.csv;

import com.bogdanjmk.railwaybookingsystem.export.AbstractExporter;
import com.bogdanjmk.railwaybookingsystem.model.Schedule;
import jakarta.servlet.http.HttpServletResponse;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.List;

public class ScheduleExportCSV extends AbstractExporter {
    public void export (List<Schedule> schedules, HttpServletResponse response) throws IOException {
        if (response.isCommitted() || schedules.isEmpty()) return;

        super.setResponseHeader(response, "text/csv", ".csv", "schedules_");

        try (ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE)) {
            String[] csvHeader = {"Schedule Id", "Train Id", "Route Id", "Departure Time", "Arrival Time"};
            String[] fieldMapping = {"id", "trainId", "routeId", "departureTime", "arrivalTime"};

            csvWriter.writeHeader(csvHeader);

            for (Schedule schedule : schedules) {
                csvWriter.write(schedule, fieldMapping);
            }
        }
    }
}
