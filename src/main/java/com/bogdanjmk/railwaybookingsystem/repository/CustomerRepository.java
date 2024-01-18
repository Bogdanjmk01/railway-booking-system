package com.bogdanjmk.railwaybookingsystem.repository;

import com.bogdanjmk.railwaybookingsystem.model.Customer;

public interface CustomerRepository<T extends Customer> {
    Customer getCustomerByEmail(String email);
}
