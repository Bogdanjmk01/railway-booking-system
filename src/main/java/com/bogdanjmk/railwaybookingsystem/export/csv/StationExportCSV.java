package com.bogdanjmk.railwaybookingsystem.export.csv;

import com.bogdanjmk.railwaybookingsystem.export.AbstractExporter;
import com.bogdanjmk.railwaybookingsystem.model.Station;
import jakarta.servlet.http.HttpServletResponse;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.List;

public class StationExportCSV extends AbstractExporter {
    public void export (List<Station> stations, HttpServletResponse response) throws IOException {
        if (response.isCommitted() || stations.isEmpty()) return;

        super.setResponseHeader(response, "text/csv", ".csv", "stations_");

        try (ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE)) {
            String[] csvHeader = {"Station Id", "Station Name", "Station Location"};
            String[] fieldMapping = {"id", "stationName", "stationLocation"};

            csvWriter.writeHeader(csvHeader);

            for (Station station : stations) {
                csvWriter.write(station, fieldMapping);
            }
        }
    }
}
