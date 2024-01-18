package com.bogdanjmk.railwaybookingsystem.controller;

import com.bogdanjmk.railwaybookingsystem.model.Customer;
import com.bogdanjmk.railwaybookingsystem.model.HttpResponse;
import com.bogdanjmk.railwaybookingsystem.model.LoginForm;
import com.bogdanjmk.railwaybookingsystem.provider.TokenProvider;
import com.bogdanjmk.railwaybookingsystem.repository.CustomerRepository;
import com.bogdanjmk.railwaybookingsystem.security.customers.CustomerDetails;
import com.bogdanjmk.railwaybookingsystem.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.security.authentication.UsernamePasswordAuthenticationToken.unauthenticated;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class CustomerAuthController {
    private final CustomerRepository<Customer> customerRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final RoleService roleService;

    @PostMapping(value = "/customer/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> login(@RequestBody @Valid LoginForm loginForm) {
        authenticationManager.authenticate(unauthenticated(loginForm.getEmail(), loginForm.getPassword()));
        Customer customer = customerRepository.getCustomerByEmail(loginForm.getEmail());

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("customer", customer, "access_token", tokenProvider.createAccessToken(getCustomerDetails(customer))))
                        .message("Logged in successfully!")
                        .statusCode(OK.value())
                        .httpStatus(OK)
                        .build()
        );
    }

    private CustomerDetails getCustomerDetails(Customer customer) {
        return new CustomerDetails(customerRepository.getCustomerByEmail(customer.getEmail()), roleService.getRoleByCustomerId(customer.getId()));
    }
}
