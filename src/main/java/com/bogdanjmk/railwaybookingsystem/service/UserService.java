package com.bogdanjmk.railwaybookingsystem.service;

import com.bogdanjmk.railwaybookingsystem.model.User;

public interface UserService {
    User getUserByEmail(String email);
}
