package com.bogdanjmk.railwaybookingsystem.repository;

import com.bogdanjmk.railwaybookingsystem.model.Role;

import java.util.Collection;

public interface RoleRepository<T extends Role> {
    Collection<Role> getAllRoles();
    Role getRoleByUserId(Long userId);
    Role getRoleByCustomerId(Long customerId);
}
