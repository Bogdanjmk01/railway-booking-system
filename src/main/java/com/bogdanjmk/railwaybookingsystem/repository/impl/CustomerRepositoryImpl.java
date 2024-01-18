package com.bogdanjmk.railwaybookingsystem.repository.impl;

import com.bogdanjmk.railwaybookingsystem.exception.ApiException;
import com.bogdanjmk.railwaybookingsystem.model.Customer;
import com.bogdanjmk.railwaybookingsystem.repository.CustomerRepository;
import com.bogdanjmk.railwaybookingsystem.rowmapper.CustomerRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

import static com.bogdanjmk.railwaybookingsystem.query.CustomerQuery.SELECT_CUSTOMER_BY_EMAIL_QUERY;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository<Customer> {
    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Customer getCustomerByEmail(String email) {
        try {
            return jdbc.queryForObject(SELECT_CUSTOMER_BY_EMAIL_QUERY, Map.of("email", email), new CustomerRowMapper());
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("Invalid email or password");
        } catch (Exception exception) {
            log.info(exception.getMessage());
            log.info(exception.getLocalizedMessage());
            throw new ApiException("An error has occurred while retrieving the user");
        }
    }
}
