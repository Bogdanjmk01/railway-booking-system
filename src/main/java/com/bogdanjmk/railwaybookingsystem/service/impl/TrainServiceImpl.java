package com.bogdanjmk.railwaybookingsystem.service.impl;

import com.bogdanjmk.railwaybookingsystem.model.Route;
import com.bogdanjmk.railwaybookingsystem.model.Seat;
import com.bogdanjmk.railwaybookingsystem.model.Station;
import com.bogdanjmk.railwaybookingsystem.model.Train;
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
    public void updateTrain(Train train) {
        trainRepository.updateTrain(train.getId(), train.getName(), train.getType());
    }

    @Override
    public void updateSeat(Seat seat) {
        trainRepository.updateSeatForTrain(seat.getId(), seat.getSeatNumber(), seat.getCarNumber(), seat.getClassName());
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
        trainRepository.createRoute(route.getDepartureStationId(), route.getArrivalStationId(), route.getDistance(), route.getArrivalTime());
    }

    @Override
    public List<Route> getRoutes() {
        return trainRepository.getAllRoutes();
    }
}
