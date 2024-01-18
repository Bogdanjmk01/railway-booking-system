package com.bogdanjmk.railwaybookingsystem.export.csv;

import com.bogdanjmk.railwaybookingsystem.export.AbstractExporter;
import com.bogdanjmk.railwaybookingsystem.model.Train;
import jakarta.servlet.http.HttpServletResponse;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.List;

public class TrainExportCSV extends AbstractExporter {
    public void export (List<Train> trains, HttpServletResponse response) throws IOException {
        if (response.isCommitted() || trains.isEmpty()) return;

        super.setResponseHeader(response, "text/csv", ".csv", "trains_");

        try (ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE)) {
            String[] csvHeader = {"Train Id", "Train Name", "Train Type"};
            String[] fieldMapping = {"id", "name", "type"};

            csvWriter.writeHeader(csvHeader);

            for (Train train : trains) {
                csvWriter.write(train, fieldMapping);
            }
        }
    }
}
