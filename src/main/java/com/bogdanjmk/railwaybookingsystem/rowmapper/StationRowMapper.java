package com.bogdanjmk.railwaybookingsystem.rowmapper;

import com.bogdanjmk.railwaybookingsystem.model.Station;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StationRowMapper implements RowMapper<Station> {
    @Override
    public Station mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Station.builder()
                .id(rs.getLong("id"))
                .stationName(rs.getString("station_name"))
                .location(rs.getString("location"))
                .build();
    }
}
