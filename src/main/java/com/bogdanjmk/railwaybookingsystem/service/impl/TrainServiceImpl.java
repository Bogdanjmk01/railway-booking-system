package com.bogdanjmk.railwaybookingsystem.service.impl;

import com.bogdanjmk.railwaybookingsystem.model.*;
import com.bogdanjmk.railwaybookingsystem.repository.TrainRepository;
import com.bogdanjmk.railwaybookingsystem.service.TrainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class TrainServiceImpl implements TrainService {
    private final TrainRepository<Train> trainRepository;

    @Override
    public void createTrain(Train train) {
        trainRepository.createTrain(train.getName(), train.getType());
    }

    @Override
    public void createSeat(Seat seat) {
        trainRepository.createSeatForTrain(seat.getTrainId(), seat.getSeatNumber(), seat.getCarNumber(), seat.getClassName());
    }

    @Override
    public void updateTrain(Train train, Long trainId) {
        trainRepository.updateTrain(trainId, train.getName(), train.getType());
    }

    @Override
    public void updateSeat(Seat seat, Long seatId) {
        trainRepository.updateSeatForTrain(seatId, seat.getTrainId(), seat.getSeatNumber(), seat.getCarNumber(), seat.getClassName());
    }

    @Override
    public List<Seat> getAllSeats(Long trainId) {
        return trainRepository.getAllSeatsForATrain(trainId);
    }

    @Override
    public void createStation(Station station) {
        trainRepository.createStation(station.getStationName(), station.getLocation());
    }

    @Override
    public List<Train> getTrains() {
        return trainRepository.getAllTrains();
    }

    @Override
    public List<Station> getStations() {
        return trainRepository.getAllStations();
    }

    @Override
    public void createRoute(Route route) {
        System.out.println(route);
        trainRepository.createRoute(route.getDepartureStationId(), route.getArrivalStationId(), route.getDistance(), route.getArrivalTime());
    }

    @Override
    public List<Route> getRoutes() {
        return trainRepository.getAllRoutes();
    }

    @Override
    public List<Schedule> getSchedules() {
        return trainRepository.getAllSchedules();
    }

    @Override
    public void createSchedule(Schedule schedule) {
        trainRepository.createSchedule(schedule.getTrainId(), schedule.getRouteId(), schedule.getDepartureTime(), schedule.getArrivalTime());
    }

    @Override
    public Train getTrainById(Long trainId) {
        return trainRepository.getTrainById(trainId);
    }

    @Override
    public Seat getSeatById(Long seatId) {
        return trainRepository.getSeatById(seatId);
    }

    @Override
    public void deleteTrainById(Long trainId) {
        trainRepository.deleteTrainById(trainId);
    }

    @Override
    public void deleteRouteById(Long routeId) {
        trainRepository.deleteRouteById(routeId);
    }

    @Override
    public void deleteScheduleById(Long scheduleId) {
        trainRepository.deleteScheduleById(scheduleId);
    }

    @Override
    public void deleteStationById(Long stationId) {
        trainRepository.deleteStationById(stationId);
    }

    @Override
    public Station getStationById(Long stationId) {
        return trainRepository.getStationById(stationId);
    }

    @Override
    public Route getRouteById(Long routeId) {
        return trainRepository.getRouteById(routeId);
    }

    @Override
    public void updateRouteById(Long routeId, Route route) {
        trainRepository.updateRoute(routeId, route.getDepartureStationId(), route.getArrivalStationId(), route.getDistance(), route.getArrivalTime());
    }

    @Override
    public Schedule getScheduleById(Long scheduleId) {
        return trainRepository.getScheduleById(scheduleId);
    }

    @Override
    public void updateScheduleById(Long scheduleId, Schedule schedule) {
        trainRepository.updateSchedule(scheduleId, schedule.getTrainId(), schedule.getRouteId(), schedule.getDepartureTime(), schedule.getArrivalTime());
    }
}
