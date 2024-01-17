package com.bogdanjmk.railwaybookingsystem.repository;

import com.bogdanjmk.railwaybookingsystem.model.*;

import java.sql.Timestamp;
import java.util.List;

public interface TrainRepository<T extends Train> {
    void createTrain(String trainName, String trainType);
    void createSeatForTrain(Long trainId, String seatNumber, int carNumber, String className);
    void updateTrain(Long id, String trainName, String trainType);
    void updateSeatForTrain(Long id, Long trainId,String seatNumber, int carNumber, String className);
    List<Seat> getAllSeatsForATrain(Long trainId);
    void createStation(String stationName, String location);
    List<Train> getAllTrains();
    List<Station> getAllStations();
    void createRoute(Long departureStationId, Long arrivalStationId, int distance, String arrivalTime);
    List<Route> getAllRoutes();
    List<Schedule> getAllSchedules();
    void createSchedule(Long trainId, Long routeId, String departureTime, String arrivalTime);
    Train getTrainById(Long trainId);
    Seat getSeatById(Long seatId);
    Station getStationById(Long stationId);
    Route getRouteById(Long routeId);
    void deleteStationById(Long stationId);
    void deleteTrainById(Long trainId);
    void deleteRouteById(Long routeId);
    void deleteScheduleById(Long scheduleId);
    void updateRoute(Long id, Long arrivalStationId, Long departureStationId, int distance, String arrivalTime);
    Schedule getScheduleById(Long scheduleId);
    void updateSchedule(Long scheduleId, Long trainId, Long routeId, String departureTime, String arrivalTime);
}
