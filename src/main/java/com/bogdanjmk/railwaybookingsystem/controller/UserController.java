package com.bogdanjmk.railwaybookingsystem.controller;

import com.bogdanjmk.railwaybookingsystem.model.*;
import com.bogdanjmk.railwaybookingsystem.service.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final TrainService trainService;

    @GetMapping("/trains")
    public ResponseEntity<HttpResponse> getAllTrains(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(
            HttpResponse.builder()
                    .data(Map.of("trains", trainService.getTrains(), "user", user))
                    .build()
        );
    }

    @GetMapping("/stations")
    public ResponseEntity<HttpResponse> getAllStations(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("stations", trainService.getStations(), "user", user))
                        .build()
        );
    }

    @GetMapping("/seats")
    public ResponseEntity<HttpResponse> getAllSeats(@AuthenticationPrincipal User user, @RequestParam(value = "trainId", required = false) Long trainId) {
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("seats", trainService.getAllSeats(trainId), "user", user))
                        .build()
        );
    }

    @GetMapping("/routes")
    public ResponseEntity<HttpResponse> getAllRoutes(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("routes", trainService.getRoutes(), "user", user))
                        .build()
        );
    }

    @PostMapping("/create/train")
    public ResponseEntity<HttpResponse> createTrain(@AuthenticationPrincipal User user, @RequestBody Train train) {
        trainService.createTrain(train);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("user", user))
                        .message("Train created successfully!")
                        .build()
        );
    }

    @PostMapping("/create/seat")
    public ResponseEntity<HttpResponse> createSeat(@AuthenticationPrincipal User user, @RequestBody Seat seat) {
        trainService.createSeat(seat);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("user", user))
                        .message("Seat created successfully!")
                        .build()
        );
    }

    @PostMapping("/create/station")
    public ResponseEntity<HttpResponse> createStation(@AuthenticationPrincipal User user, @RequestBody Station station) {
        trainService.createStation(station);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("user", user))
                        .message("Station created successfully!")
                        .build()
        );
    }

    @PostMapping("/create/route")
    public ResponseEntity<HttpResponse> createRoute(@AuthenticationPrincipal User user, @RequestBody Route route) {
        trainService.createRoute(route);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("user", user))
                        .message("Route created successfully!")
                        .build()
        );
    }

    @PutMapping("/update/train")
    public ResponseEntity<HttpResponse> updateTrain(@AuthenticationPrincipal User user, @RequestBody Train train) {
        trainService.updateTrain(train);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("user", user))
                        .message("Train updated successfully!")
                        .build()
        );
    }

    @PutMapping("/update/seat")
    public ResponseEntity<HttpResponse> updateSeat(@AuthenticationPrincipal User user, @RequestBody Seat seat) {
        trainService.updateSeat(seat);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("user", user))
                        .message("Seat updated successfully!")
                        .build()
        );
    }
}
