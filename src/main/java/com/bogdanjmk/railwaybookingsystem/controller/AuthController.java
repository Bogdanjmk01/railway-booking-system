package com.bogdanjmk.railwaybookingsystem.controller;

import com.bogdanjmk.railwaybookingsystem.model.HttpResponse;
import com.bogdanjmk.railwaybookingsystem.model.LoginForm;
import com.bogdanjmk.railwaybookingsystem.model.User;
import com.bogdanjmk.railwaybookingsystem.provider.TokenProvider;
import com.bogdanjmk.railwaybookingsystem.security.RailwayUserDetails;
import com.bogdanjmk.railwaybookingsystem.service.RoleService;
import com.bogdanjmk.railwaybookingsystem.service.UserService;
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
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final RoleService roleService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> login(@RequestBody @Valid LoginForm loginForm) {
        authenticationManager.authenticate(unauthenticated(loginForm.getEmail(), loginForm.getPassword()));
        User user = userService.getUserByEmail(loginForm.getEmail());

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("user", user, "access_token", tokenProvider.createAccessToken(getUserDetails(user))))
                        .message("Logged in successfully!")
                        .statusCode(OK.value())
                        .httpStatus(OK)
                        .build()
        );
    }

    private RailwayUserDetails getUserDetails(User user) {
        return new RailwayUserDetails(userService.getUserByEmail(user.getEmail()), roleService.getRoleByUserId(user.getId()));
    }
}
