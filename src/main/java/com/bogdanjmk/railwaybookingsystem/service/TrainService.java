package com.bogdanjmk.railwaybookingsystem.service;

import com.bogdanjmk.railwaybookingsystem.model.Route;
import com.bogdanjmk.railwaybookingsystem.model.Seat;
import com.bogdanjmk.railwaybookingsystem.model.Station;
import com.bogdanjmk.railwaybookingsystem.model.Train;

import java.util.List;

public interface TrainService {
    void createTrain(Train train);
    void createSeat(Seat seat);
    void updateTrain(Train train);
    void updateSeat(Seat seat);
    List<Seat> getAllSeats(Long trainId);
    void createStation(Station station);
    List<Train> getTrains();
    List<Station> getStations();
    void createRoute(Route route);
    List<Route> getRoutes();
}
