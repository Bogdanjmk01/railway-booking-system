package com.bogdanjmk.railwaybookingsystem.service;

import com.bogdanjmk.railwaybookingsystem.model.*;

import java.util.List;

public interface TrainService {
    void createTrain(Train train);
    void createSeat(Seat seat);
    void updateTrain(Train train, Long trainId);
    void updateSeat(Seat seat, Long seatId);
    List<Seat> getAllSeats(Long trainId);
    void createStation(Station station);
    List<Train> getTrains();
    List<Station> getStations();
    void createRoute(Route route);
    List<Route> getRoutes();
    List<Schedule> getSchedules();
    void createSchedule(Schedule schedule);
    Train getTrainById(Long trainId);
    Seat getSeatById(Long seatId);
    void deleteTrainById(Long trainId);
    void deleteRouteById(Long routeId);
    void deleteScheduleById(Long scheduleId);
    void deleteStationById(Long stationId);
    Station getStationById(Long stationId);
    Route getRouteById(Long routeId);
    void updateRouteById(Long routeId, Route route);
}
