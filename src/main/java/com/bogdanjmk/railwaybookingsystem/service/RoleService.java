package com.bogdanjmk.railwaybookingsystem.service;

import com.bogdanjmk.railwaybookingsystem.model.Role;

public interface RoleService {
    Role getRoleByUserId(Long userId);
}
