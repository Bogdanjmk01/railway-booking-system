package com.bogdanjmk.railwaybookingsystem.rowmapper;

import com.bogdanjmk.railwaybookingsystem.model.Train;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainRowMapper implements RowMapper<Train> {
    @Override
    public Train mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Train.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("train_name"))
                .type(rs.getString("train_type"))
                .build();
    }
}
