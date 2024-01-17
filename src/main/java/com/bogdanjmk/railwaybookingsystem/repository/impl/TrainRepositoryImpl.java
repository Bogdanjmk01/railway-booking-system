package com.bogdanjmk.railwaybookingsystem.repository.impl;

import com.bogdanjmk.railwaybookingsystem.exception.ApiException;
import com.bogdanjmk.railwaybookingsystem.model.*;
import com.bogdanjmk.railwaybookingsystem.repository.TrainRepository;
import com.bogdanjmk.railwaybookingsystem.rowmapper.*;
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
    public void updateSeatForTrain(Long id, Long trainId, String seatNumber, int carNumber, String className) {
        try {
            jdbc.update(UPDATE_SEAT_TRAIN_QUERY, Map.of("seatNumber", seatNumber, "trainId", trainId, "carNumber", carNumber, "className", className, "id", id));
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
    public void createRoute(Long departureStationId, Long arrivalStationId, int distance, String arrivalTime) {
        try {
            jdbc.update(INSERT_ROUTE_QUERY, Map.of("departureStationId", departureStationId, "arrivalStationId", arrivalStationId, "distance", distance, "arrivalTime", arrivalTime));
        } catch (Exception exception) {
            log.info(exception.getMessage());
            log.info(exception.getLocalizedMessage());
            log.info(exception.getCause().toString());
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

    @Override
    public List<Schedule> getAllSchedules() {
        try {
            return jdbc.query(SELECT_ALL_SCHEDULES_QUERY, new ScheduleRowMapper());
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("Could not find any schedules");
        } catch (Exception exception) {
            throw new ApiException("An error has occurred");
        }
    }

    @Override
    public void createSchedule(Long trainId, Long routeId, String departureTime, String arrivalTime) {
        try {
            jdbc.update(INSERT_SCHEDULE_QUERY, Map.of("trainId", trainId, "routeId", routeId, "departureTime", departureTime, "arrivalTime", arrivalTime));
        } catch (Exception exception) {
            throw new ApiException("An error has occurred");
        }
    }

    @Override
    public Train getTrainById(Long trainId) {
        try {
            return jdbc.queryForObject(SELECT_TRAIN_BY_ID_QUERY, Map.of("trainId", trainId), new TrainRowMapper());
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No train found");
        } catch (Exception exception) {
            throw new ApiException("An error has occurred");
        }
    }

    @Override
    public Seat getSeatById(Long seatId) {
        try {
            return jdbc.queryForObject(SELECT_SEAT_BY_ID_QUERY, Map.of("seatId", seatId), new SeatRowMapper());
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No seat found");
        } catch (Exception exception) {
            throw new ApiException("An error has occurred");
        }
    }

    @Override
    public Station getStationById(Long stationId) {
        try {
            return jdbc.queryForObject(SELECT_STATION_BY_ID_QUERY, Map.of("stationId", stationId), new StationRowMapper());
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No station found");
        } catch (Exception exception) {
            throw new ApiException("An error has occurred");
        }
    }

    @Override
    public Route getRouteById(Long routeId) {
        try {
            return jdbc.queryForObject(SELECT_ROUTE_BY_ID_QUERY, Map.of("routeId", routeId), new RouteRowMapper());
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No route found");
        } catch (Exception exception) {
            throw new ApiException("An error has occurred");
        }
    }

    @Override
    public void deleteStationById(Long stationId) {
        try {
            jdbc.update(DELETE_STATION_BY_ID, Map.of("stationId", stationId));
        } catch (Exception exception) {
            throw new ApiException("An error has occurred");
        }
    }

    @Override
    public void deleteTrainById(Long trainId) {
        try {
            jdbc.update(DELETE_TRAIN_BY_ID_QUERY, Map.of("trainId", trainId));
        } catch (Exception exception) {
            throw new ApiException("An error occurred");
        }
    }

    @Override
    public void deleteRouteById(Long routeId) {
        try {
            jdbc.update(DELETE_ROUTE_BY_ID_QUERY, Map.of("routeId", routeId));
        } catch (Exception exception) {
            throw new ApiException("An error occurred");
        }
    }

    @Override
    public void deleteScheduleById(Long scheduleId) {
        try {
            jdbc.update(DELETE_SCHEDULE_BY_ID_QUERY, Map.of("scheduleId", scheduleId));
        } catch (Exception exception) {
            throw new ApiException("An error occurred");
        }
    }

    @Override
    public void updateRoute(Long id, Long arrivalStationId, Long departureStationId, int distance, String arrivalTime) {
        try {
            jdbc.update(UPDATE_ROUTE_TRAIN_QUERY, Map.of("departureStationId", departureStationId, "arrivalStationId", arrivalStationId, "distance", distance, "arrivalTime", arrivalTime, "id", id));
        } catch (Exception exception) {
            log.error(exception.getMessage());
            log.info(exception.getCause().getMessage());
            log.error(exception.getLocalizedMessage());
            log.error(exception.getStackTrace().toString());
            throw new ApiException("An error has occurred!");
        }
    }

    @Override
    public Schedule getScheduleById(Long scheduleId) {
        try {
            return jdbc.queryForObject(SELECT_SCHEDULE_BY_ID_QUERY, Map.of("scheduleId", scheduleId), new ScheduleRowMapper());
        } catch (Exception exception) {
            log.error(exception.getMessage());
            log.info(exception.getLocalizedMessage());
            throw new ApiException("An error has occurred!");
        }
    }

    @Override
    public void updateSchedule(Long scheduleId, Long trainId, Long routeId, String departureTime, String arrivalTime) {
        try {
            jdbc.update(UPDATE_SCHEDULE_QUERY, Map.of("scheduleId", scheduleId, "trainId", trainId, "routeId", routeId, "departureTime", departureTime, "arrivalTime", arrivalTime));
        } catch (Exception exception) {
            throw new ApiException("An error has occurred!");
        }
    }

}
