package com.bogdanjmk.railwaybookingsystem.repository;

import com.bogdanjmk.railwaybookingsystem.model.Route;
import com.bogdanjmk.railwaybookingsystem.model.Seat;
import com.bogdanjmk.railwaybookingsystem.model.Station;
import com.bogdanjmk.railwaybookingsystem.model.Train;

import java.sql.Timestamp;
import java.util.List;

public interface TrainRepository<T extends Train> {
    void createTrain(String trainName, String trainType);
    void createSeatForTrain(Long trainId, String seatNumber, int carNumber, String className);
    void updateTrain(Long id, String trainName, String trainType);
    void updateSeatForTrain(Long id, String seatNumber, int carNumber, String className);
    List<Seat> getAllSeatsForATrain(Long trainId);
    void createStation(String stationName, String location);
    List<Train> getAllTrains();
    List<Station> getAllStations();
    void createRoute(Long departureStationId, Long arrivalStationId, int distance, Timestamp arrivalTime);
    List<Route> getAllRoutes();
}
