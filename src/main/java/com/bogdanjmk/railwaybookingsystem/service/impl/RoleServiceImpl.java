package com.bogdanjmk.railwaybookingsystem.service.impl;

import com.bogdanjmk.railwaybookingsystem.model.Role;
import com.bogdanjmk.railwaybookingsystem.repository.RoleRepository;
import com.bogdanjmk.railwaybookingsystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository<Role> roleRepository;

    @Override
    public Role getRoleByUserId(Long userId) {
        return roleRepository.getRoleByUserId(userId);
    }
}
