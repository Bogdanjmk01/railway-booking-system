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

    @GetMapping("/trains/{trainId}")
    public ResponseEntity<HttpResponse> getTrainById(@AuthenticationPrincipal User user, @PathVariable("trainId") Long trainId) {
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("train", trainService.getTrainById(trainId), "user", user))
                        .build()
        );
    }

    @GetMapping("/seats/{id}")
    public ResponseEntity<HttpResponse> getSeatById(@AuthenticationPrincipal User user, @PathVariable("id") Long id) {
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("seat", trainService.getSeatById(id), "user", user))
                        .build()
        );
    }

    @GetMapping("/station/{id}")
    public ResponseEntity<HttpResponse> getStationById(@AuthenticationPrincipal User user, @PathVariable("id") Long id) {
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("station", trainService.getStationById(id)))
                        .build()
        );
    }

    @GetMapping("/routes/{id}")
    public ResponseEntity<HttpResponse> getRouteById(@AuthenticationPrincipal User user, @PathVariable("id") Long id) {
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("route", trainService.getRouteById(id)))
                        .build()
        );
    }

    @GetMapping("/schedules")
    public ResponseEntity<HttpResponse> getSchedules(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("schedules", trainService.getSchedules(), "user", user))
                        .build()
        );
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<HttpResponse> getScheduleById(@AuthenticationPrincipal User user, @PathVariable("scheduleId") Long scheduleId) {
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("schedule", trainService.getScheduleById(scheduleId), "user", user))
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

    @PostMapping("/create/schedule")
    public ResponseEntity<HttpResponse> createSchedule(@AuthenticationPrincipal User user, @RequestBody Schedule schedule) {
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .message("Schedule created successfully")
                        .build()
        );
    }

    @PutMapping("/update/train")
    public ResponseEntity<HttpResponse> updateTrain(@AuthenticationPrincipal User user, @RequestBody Train train, @RequestParam("id") Long trainId) {
        trainService.updateTrain(train, trainId);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("user", user))
                        .message("Train updated successfully!")
                        .build()
        );
    }

    @PutMapping("/update/seat")
    public ResponseEntity<HttpResponse> updateSeat(@AuthenticationPrincipal User user, @RequestBody Seat seat, @RequestParam("id") Long seatId) {
        trainService.updateSeat(seat, seatId);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("user", user))
                        .message("Seat updated successfully!")
                        .build()
        );
    }

    @PutMapping("/update/route")
    public ResponseEntity<HttpResponse> updateRoute(@AuthenticationPrincipal User user, @RequestBody Route route, @RequestParam("id") Long routeId) {
        trainService.updateRouteById(routeId, route);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("user", user))
                        .message("Route updated successfully!")
                        .build()
        );
    }

    @PutMapping("/update/schedule")
    public ResponseEntity<HttpResponse> updateSchedule(@AuthenticationPrincipal User user, @RequestBody Schedule schedule, @RequestParam("id") Long scheduleId) {
        trainService.updateScheduleById(scheduleId, schedule);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("user", user))
                        .message("Schedule updated successfully!")
                        .build()
        );
    }

    @DeleteMapping("/delete/train/{trainId}")
    public ResponseEntity<HttpResponse> deleteTrainById(@AuthenticationPrincipal User user, @PathVariable("trainId") Long trainId) {
        trainService.deleteTrainById(trainId);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("user", user))
                        .message("Train deleted successfully!")
                        .build()
        );
    }

    @DeleteMapping("/delete/station/{stationId}")
    public ResponseEntity<HttpResponse> deleteStationById(@AuthenticationPrincipal User user, @PathVariable("stationId") Long stationId) {
        trainService.deleteStationById(stationId);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("user", user))
                        .message("Station deleted successfully")
                        .build()
        );
    }

    @DeleteMapping("/delete/route/{routeId}")
    public ResponseEntity<HttpResponse> deleteRouteById(@AuthenticationPrincipal User user, @PathVariable("routeId") Long routeId) {
        trainService.deleteRouteById(routeId);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("user", user))
                        .message("Route deleted successfully")
                        .build()
        );
    }

    @DeleteMapping("/delete/schedule/{scheduleId}")
    public ResponseEntity<HttpResponse> deleteScheduleById(@AuthenticationPrincipal User user, @PathVariable("scheduleId") Long scheduleId) {
        trainService.deleteScheduleById(scheduleId);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .data(Map.of("user", user))
                        .message("Schedule deleted successfully")
                        .build()
        );
    }
}
