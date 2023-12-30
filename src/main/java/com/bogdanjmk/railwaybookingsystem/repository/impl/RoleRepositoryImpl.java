package com.bogdanjmk.railwaybookingsystem.repository.impl;

import com.bogdanjmk.railwaybookingsystem.exception.ApiException;
import com.bogdanjmk.railwaybookingsystem.model.Role;
import com.bogdanjmk.railwaybookingsystem.repository.RoleRepository;
import com.bogdanjmk.railwaybookingsystem.rowmapper.RoleRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

import static com.bogdanjmk.railwaybookingsystem.query.RoleQuery.SELECT_ROLES_QUERY;
import static com.bogdanjmk.railwaybookingsystem.query.RoleQuery.SELECT_ROLE_BY_USER_ID_QUERY;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RoleRepositoryImpl implements RoleRepository<Role> {
    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Collection<Role> getAllRoles() {
        try {
            return jdbc.query(SELECT_ROLES_QUERY, new RoleRowMapper());
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error has occurred. Please try again.");
        }
    }

    @Override
    public Role getRoleByUserId(Long userId) {
        try {
            return jdbc.queryForObject(SELECT_ROLE_BY_USER_ID_QUERY, Map.of("id", userId), new RoleRowMapper());
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No role found by this user");
        } catch (Exception exception) {
            log.error(exception.getMessage());
            log.info(exception.getCause().getMessage());
            log.error(exception.getLocalizedMessage());
            log.error(exception.getStackTrace().toString());
            throw new ApiException("An error has occurred. Please try again later.");
        }
    }
}
