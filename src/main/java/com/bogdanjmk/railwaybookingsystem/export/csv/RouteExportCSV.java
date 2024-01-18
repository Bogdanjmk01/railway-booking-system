package com.bogdanjmk.railwaybookingsystem.export.csv;

import com.bogdanjmk.railwaybookingsystem.export.AbstractExporter;
import com.bogdanjmk.railwaybookingsystem.model.Route;
import jakarta.servlet.http.HttpServletResponse;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.List;

public class RouteExportCSV extends AbstractExporter {
    public void export (List<Route> routes, HttpServletResponse response) throws IOException {
        if (response.isCommitted() || routes.isEmpty()) return;

        super.setResponseHeader(response, "text/csv", ".csv", "routes_");

        try (ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE)) {
            String[] csvHeader = {"Route Id", "Departure Station Id", "Arrival Station Id", "Distance [km]", "Arrival Time"};
            String[] fieldMapping = {"id", "departureStationId", "arrivalStationId", "distance", "arrivalTime"};

            csvWriter.writeHeader(csvHeader);

            for (Route route : routes) {
                csvWriter.write(route, fieldMapping);
            }
        }
    }
}
