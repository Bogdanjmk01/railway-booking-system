package com.bogdanjmk.railwaybookingsystem.repository.impl;

import com.bogdanjmk.railwaybookingsystem.exception.ApiException;
import com.bogdanjmk.railwaybookingsystem.model.User;
import com.bogdanjmk.railwaybookingsystem.repository.UserRepository;
import com.bogdanjmk.railwaybookingsystem.rowmapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import static com.bogdanjmk.railwaybookingsystem.query.UserQuery.SELECT_USER_BY_EMAIL_QUERY;
import static java.util.Map.of;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository<User> {
    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public User getUserByEmail(String email) {
        try {
            return jdbc.queryForObject(SELECT_USER_BY_EMAIL_QUERY, of("email", email), new UserRowMapper());
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No user found by email: " + email);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            log.info(exception.getCause().getMessage());
            log.error(exception.getLocalizedMessage());
            log.error(exception.getStackTrace().toString());
            throw new ApiException("An error has occurred. Please try again later.");
        }
    }
}
