package com.bogdanjmk.railwaybookingsystem.service.impl;

import com.bogdanjmk.railwaybookingsystem.model.User;
import com.bogdanjmk.railwaybookingsystem.repository.UserRepository;
import com.bogdanjmk.railwaybookingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository<User> userRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
