package com.bogdanjmk.railwaybookingsystem.rowmapper;

import com.bogdanjmk.railwaybookingsystem.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Customer.builder()
                .id(resultSet.getLong("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .enabled(resultSet.getBoolean("enabled"))
                .isNotLocked(resultSet.getBoolean("non_locked"))
                .identificationNumber(resultSet.getString("identification_number"))
                .build();
    }
}
