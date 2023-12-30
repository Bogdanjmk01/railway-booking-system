package com.bogdanjmk.railwaybookingsystem.repository.impl;

import com.bogdanjmk.railwaybookingsystem.exception.ApiException;
import com.bogdanjmk.railwaybookingsystem.model.Route;
import com.bogdanjmk.railwaybookingsystem.model.Seat;
import com.bogdanjmk.railwaybookingsystem.model.Station;
import com.bogdanjmk.railwaybookingsystem.model.Train;
import com.bogdanjmk.railwaybookingsystem.repository.TrainRepository;
import com.bogdanjmk.railwaybookingsystem.rowmapper.RouteRowMapper;
import com.bogdanjmk.railwaybookingsystem.rowmapper.SeatRowMapper;
import com.bogdanjmk.railwaybookingsystem.rowmapper.StationRowMapper;
import com.bogdanjmk.railwaybookingsystem.rowmapper.TrainRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static com.bogdanjmk.railwaybookingsystem.query.TrainQuery.*;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TrainRepositoryImpl implements TrainRepository<Train> {
    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public void createTrain(String trainName, String trainType) {
        try {
            jdbc.update(INSERT_TRAIN_QUERY, Map.of("name", trainName, "type", trainType));
        } catch (Exception exception) {
            log.info(exception.getMessage());
            throw new ApiException("An error has occurred!");
        }
    }

    @Override
    public void createSeatForTrain(Long trainId, String seatNumber, int carNumber, String className) {
        try {
            jdbc.update(INSERT_SEAT_QUERY, Map.of("id", trainId, "seatNumber", seatNumber, "carNumber", carNumber, "className", className));
        } catch (Exception exception) {
            log.info(exception.getMessage());
            throw new ApiException("An error has occurred!");
        }
    }

    @Override
    public void updateTrain(Long id, String trainName, String trainType) {
        try {
            jdbc.update(UPDATE_TRAIN_QUERY, Map.of("id", id, "trainName", trainName, "trainType", trainType));
        } catch (Exception exception) {
            log.info(exception.getMessage());
            throw new ApiException("An error occurred!");
        }
    }

    @Override
    public void updateSeatForTrain(Long id, String seatNumber, int carNumber, String className) {
        try {
            jdbc.update(UPDATE_SEAT_TRAIN_QUERY, Map.of("id", id, "seatNumber", seatNumber, "carNumber", carNumber, "className", className));
        } catch (Exception exception) {
            log.error(exception.getMessage());
            log.info(exception.getCause().getMessage());
            log.error(exception.getLocalizedMessage());
            log.error(exception.getStackTrace().toString());
            throw new ApiException("An error has occurred!");
        }
    }

    @Override
    public List<Seat> getAllSeatsForATrain(Long trainId) {
        try {
            return jdbc.query(SELECT_SEATS_FOR_A_TRAIN_QUERY, Map.of("trainId", trainId), new SeatRowMapper());
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No seats found!");
        } catch (Exception exception) {
            throw new ApiException(exception.getMessage());
        }
    }

    @Override
    public void createStation(String stationName, String location) {
        try {
            jdbc.update(INSERT_STATION_QUERY, Map.of("stationName", stationName, "location", location));
        } catch (Exception exception) {
            throw new ApiException(exception.getMessage());
        }
    }

    @Override
    public List<Train> getAllTrains() {
        try {
            return jdbc.query(SELECT_TRAINS_QUERY, new TrainRowMapper());
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No trains found");
        } catch (Exception exception) {
            throw new ApiException("An error has occurred!");
        }
    }

    @Override
    public List<Station> getAllStations() {
        try {
            return jdbc.query(SELECT_STATIONS_QUERY, new StationRowMapper());
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No stations found");
        } catch (Exception exception) {
            throw new ApiException("An error has occurred");
        }
    }

    @Override
    public void createRoute(Long departureStationId, Long arrivalStationId, int distance, Timestamp arrivalTime) {
        try {
            jdbc.update(INSERT_ROUTE_QUERY, Map.of("departureStationId", departureStationId, "arrivalStationId", arrivalStationId, "distance", distance, "arrivalTime", arrivalTime));
        } catch (Exception exception) {
            throw new ApiException("An error has occurred");
        }
    }

    @Override
    public List<Route> getAllRoutes() {
        try {
            return jdbc.query(SELECT_ALL_ROUTES_QUERY, new RouteRowMapper());
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No routes found");
        } catch (Exception exception) {
            throw new ApiException("An error has occurred");
        }
    }

}
