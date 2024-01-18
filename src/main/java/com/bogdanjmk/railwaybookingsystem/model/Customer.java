package com.bogdanjmk.railwaybookingsystem.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_DEFAULT)
public class Customer {
    private Long id;
    @NotEmpty(message = "First name cannot be empty!")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty!")
    private String lastName;
    @Email(message = "Invalid email address. Please enter a valid email address.")
    @NotEmpty(message = "Email cannot be empty!")
    private String email;
    @NotEmpty(message = "Password cannot be empty!")
    private String password;
    private boolean enabled;
    private boolean isNotLocked;
    private String identificationNumber;
}
