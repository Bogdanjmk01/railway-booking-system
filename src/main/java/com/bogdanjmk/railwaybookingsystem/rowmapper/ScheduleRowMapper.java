package com.bogdanjmk.railwaybookingsystem.rowmapper;

import com.bogdanjmk.railwaybookingsystem.model.Schedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleRowMapper implements RowMapper<Schedule> {
    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Schedule.builder()
                .id(rs.getLong("id"))
                .trainId(rs.getLong("train_id"))
                .routeId(rs.getLong("route_id"))
                .departureTime(rs.getString("departure_time"))
                .arrivalTime(rs.getString("arrival_time"))
                .build();
    }
}
