package com.bogdanjmk.railwaybookingsystem.security.customers;

import com.bogdanjmk.railwaybookingsystem.exception.ApiException;
import com.bogdanjmk.railwaybookingsystem.model.Customer;
import com.bogdanjmk.railwaybookingsystem.model.Role;
import com.bogdanjmk.railwaybookingsystem.repository.CustomerRepository;
import com.bogdanjmk.railwaybookingsystem.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerDetailsService implements UserDetailsService {
    private final CustomerRepository<Customer> customerRepository;
    private final RoleRepository<Role> roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.getCustomerByEmail(username);

        if (customer != null) return new CustomerDetails(customer, roleRepository.getRoleByCustomerId(customer.getId()));

        throw new ApiException("Email or password incorrect");
    }
}
