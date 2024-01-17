package com.bogdanjmk.railwaybookingsystem.query;

public class TrainQuery {
    public static final String INSERT_TRAIN_QUERY = "INSERT INTO trains (train_name, train_type) VALUES (:name, :type)";
    public static final String INSERT_SEAT_QUERY = "INSERT INTO seats (train_id, seat_number, car_number, class) VALUES (:id, :seatNumber, :carNumber, :className)";
    public static final String UPDATE_TRAIN_QUERY = "UPDATE trains SET train_name = :trainName, train_type = :trainType WHERE id = :id";
    public static final String UPDATE_SEAT_TRAIN_QUERY = "UPDATE seats SET seat_number = :seatNumber, train_id = :trainId, car_number = :carNumber, class = :className WHERE id = :id";
    public static final String SELECT_SEATS_FOR_A_TRAIN_QUERY = "SELECT DISTINCT * FROM seats WHERE train_id = :trainId";
    public static final String INSERT_STATION_QUERY = "INSERT INTO stations (station_name, location) VALUES (:stationName, :location)";
    public static final String SELECT_TRAINS_QUERY = "SELECT DISTINCT * FROM trains";
    public static final String SELECT_STATIONS_QUERY =  "SELECT DISTINCT * FROM stations";
    public static final String INSERT_ROUTE_QUERY = "INSERT INTO routes (departure_station_id, arrival_station_id, distance, arrival_time) VALUES (:departureStationId, :arrivalStationId, :distance, :arrivalTime)";
    public static final String SELECT_ALL_ROUTES_QUERY = "SELECT DISTINCT * FROM routes";
    public static final String SELECT_ALL_SCHEDULES_QUERY = "SELECT DISTINCT * FROM schedules";
    public static final String INSERT_SCHEDULE_QUERY = "INSERT INTO schedules (train_id, route_id, departure_time, arrival_time) VALUES (:trainId, :routeId, :departureTime, :arrivalTime)";
    public static final String SELECT_TRAIN_BY_ID_QUERY = "SELECT * FROM trains WHERE id = :trainId";
    public static final String SELECT_SEAT_BY_ID_QUERY = "SELECT * FROM seats WHERE id = :seatId";
    public static final String DELETE_TRAIN_BY_ID_QUERY = "DELETE FROM trains WHERE id = :trainId";
    public static final String DELETE_ROUTE_BY_ID_QUERY = "DELETE FROM routes WHERE id = :routeId";
    public static final String DELETE_SCHEDULE_BY_ID_QUERY = "DELETE FROM schedules WHERE id = :scheduleId";
    public static final String SELECT_STATION_BY_ID_QUERY = "SELECT * FROM stations WHERE id = :stationId";
    public static final String SELECT_ROUTE_BY_ID_QUERY = "SELECT * FROM routes WHERE id = :routeId";
    public static final String DELETE_STATION_BY_ID = "DELETE FROM stations WHERE id = :stationId";
    public static final String UPDATE_ROUTE_TRAIN_QUERY = "UPDATE routes SET departure_station_id = :departureStationId, arrival_station_id = :arrivalStationId, distance = :distance, arrival_time = :arrivalTime WHERE id = :id";
    public static final String SELECT_SCHEDULE_BY_ID_QUERY = "SELECT * FROM schedules WHERE id = :scheduleId";
    public static final String UPDATE_SCHEDULE_QUERY = "UPDATE schedules SET train_id = :trainId, route_id = :routeId, departure_time = :departureTime, arrival_time = :arrivalTime WHERE id = :scheduleId";
}
