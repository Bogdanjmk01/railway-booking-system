package com.bogdanjmk.railwaybookingsystem.security.customers;

import com.bogdanjmk.railwaybookingsystem.model.Customer;
import com.bogdanjmk.railwaybookingsystem.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@RequiredArgsConstructor
public class CustomerDetails implements UserDetails {
    private final Customer customer;
    private final Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return stream(this.role.getName().split(",".trim())).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return customer.isNotLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return customer.isEnabled();
    }
}
