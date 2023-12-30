package com.bogdanjmk.railwaybookingsystem.security;

import com.bogdanjmk.railwaybookingsystem.exception.ApiException;
import com.bogdanjmk.railwaybookingsystem.model.Role;
import com.bogdanjmk.railwaybookingsystem.model.User;
import com.bogdanjmk.railwaybookingsystem.repository.RoleRepository;
import com.bogdanjmk.railwaybookingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RailwayDetailsService implements UserDetailsService {
    private final UserRepository<User> userRepository;
    private final RoleRepository<Role> roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(username);

        if (user != null) return new RailwayUserDetails(user, roleRepository.getRoleByUserId(user.getId()));

        throw new ApiException("Email or password incorrect!");
    }
}
