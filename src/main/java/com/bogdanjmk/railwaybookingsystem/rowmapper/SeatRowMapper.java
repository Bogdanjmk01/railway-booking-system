package com.bogdanjmk.railwaybookingsystem.rowmapper;

import com.bogdanjmk.railwaybookingsystem.model.Seat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatRowMapper implements RowMapper<Seat> {
    @Override
    public Seat mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Seat.builder()
                .id(resultSet.getLong("id"))
                .trainId(resultSet.getLong("train_id"))
                .seatNumber(resultSet.getString("seat_number"))
                .carNumber(resultSet.getInt("car_number"))
                .className(resultSet.getString("class"))
                .build();
    }
}
