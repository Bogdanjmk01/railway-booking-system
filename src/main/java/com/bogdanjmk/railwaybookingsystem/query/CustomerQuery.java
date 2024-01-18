package com.bogdanjmk.railwaybookingsystem.query;

public class CustomerQuery {
    public static final String SELECT_CUSTOMER_BY_EMAIL_QUERY = "SELECT * FROM customers WHERE email = :email";
}
