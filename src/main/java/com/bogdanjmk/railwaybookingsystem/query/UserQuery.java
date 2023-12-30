package com.bogdanjmk.railwaybookingsystem.query;

public class UserQuery {
    public static final String SELECT_USER_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email = :email";
}
