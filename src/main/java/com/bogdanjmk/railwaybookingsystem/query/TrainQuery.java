package com.bogdanjmk.railwaybookingsystem.query;

public class TrainQuery {
    public static final String INSERT_TRAIN_QUERY = "INSERT INTO trains (train_name, train_type) VALUES (:name, :type)";
    public static final String INSERT_SEAT_QUERY = "INSERT INTO seats (train_id, seat_number, car_number, class) VALUES (:id, :seatNumber, :carNumber, :className)";
    public static final String UPDATE_TRAIN_QUERY = "UPDATE trains SET train_name = :trainName, train_type = :trainType WHERE id = :id";
    public static final String UPDATE_SEAT_TRAIN_QUERY = "UPDATE seats SET seat_number = :seatNumber, car_number = :carNumber, 'class' = :className WHERE id = :id";
    public static final String SELECT_SEATS_FOR_A_TRAIN_QUERY = "SELECT DISTINCT * FROM seats WHERE train_id = :trainId";
    public static final String INSERT_STATION_QUERY = "INSERT INTO stations (station_name, location) VALUES (:stationName, :location)";
    public static final String SELECT_TRAINS_QUERY = "SELECT DISTINCT * FROM trains";
    public static final String SELECT_STATIONS_QUERY =  "SELECT DISTINCT * FROM stations";
    public static final String INSERT_ROUTE_QUERY = "INSERT INTO routes (departure_station_id, arrival_station_id, distance, arrival_time) VALUES (:departureStationId, :arrivalStationId, :distance, :arrivalTime)";
    public static final String SELECT_ALL_ROUTES_QUERY = "SELECT DISTINCT * FROM routes";
}
