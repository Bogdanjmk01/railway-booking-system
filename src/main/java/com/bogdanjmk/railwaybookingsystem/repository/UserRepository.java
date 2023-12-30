package com.bogdanjmk.railwaybookingsystem.repository;

import com.bogdanjmk.railwaybookingsystem.model.User;

public interface UserRepository<T extends User> {
    User getUserByEmail(String email);
}
