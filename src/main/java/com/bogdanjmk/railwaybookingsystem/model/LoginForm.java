package com.bogdanjmk.railwaybookingsystem.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
    @NotEmpty(message = "The email cannot be empty")
    @Email(message = "Please enter a valid email address")
    private String email;
    @NotEmpty(message = "The password cannot be empty")
    private String password;
}
