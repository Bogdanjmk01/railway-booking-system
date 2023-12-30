package com.bogdanjmk.railwaybookingsystem.rowmapper;

import com.bogdanjmk.railwaybookingsystem.model.Route;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteRowMapper implements RowMapper<Route> {
    @Override
    public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Route.builder()
                .id(rs.getLong("id"))
                .departureStationId(rs.getLong("departure_station_id"))
                .arrivalStationId(rs.getLong("arrival_station_id"))
                .distance(rs.getInt("distance"))
                .arrivalTime(rs.getTimestamp("arrival_time"))
                .build();
    }
}
