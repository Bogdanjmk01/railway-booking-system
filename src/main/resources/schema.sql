DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY CHECK (id > 0),
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    non_locked BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT UQ_Users_Email UNIQUE (email)
);

DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY CHECK (id > 0),
    name VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS user_roles;

CREATE TABLE user_roles (
    id BIGSERIAL PRIMARY KEY NOT NULL CHECK (id > 0),
    user_id BIGSERIAL NOT NULL CHECK (user_id > 0),
    role_id BIGSERIAL NOT NULL CHECK (role_id > 0),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT UQ_UserRoles_User_Id UNIQUE (user_id)
);

DROP TABLE IF EXISTS reset_password_verifications;

CREATE TABLE reset_password_verifications (
    id BIGSERIAL PRIMARY KEY NOT NULL CHECK (id > 0),
    user_id BIGSERIAL NOT NULL,
    url VARCHAR(255) NOT NULL,
    expiration_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT UQ_ResetPasswordVerifications_User_Id UNIQUE (user_id),
    CONSTRAINT UQ_ResetPasswordVerifications_Url UNIQUE (url)
);

DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
    id BIGSERIAL PRIMARY KEY CHECK (id > 0),
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    non_locked BOOLEAN NOT NULL DEFAULT TRUE,
    identification_number VARCHAR(100) NOT NULL,
    CONSTRAINT UQ_Customers_Email UNIQUE (email),
    CONSTRAINT UQ_Customers_Identification_Number UNIQUE (identification_number)
);

DROP TABLE IF EXISTS trains;

CREATE TABLE trains (
    id BIGSERIAL PRIMARY KEY CHECK (id > 0),
    train_name VARCHAR(50) NOT NULL,
    train_type VARCHAR(50) NOT NULL,
    CONSTRAINT UQ_Trains_Name UNIQUE (train_name)
);

DROP TABLE IF EXISTS stations;

CREATE TABLE stations (
    id BIGSERIAL PRIMARY KEY CHECK (id > 0),
    station_name VARCHAR(100) NOT NULL,
    location TEXT NOT NULL,
    CONSTRAINT UQ_Stations_Name UNIQUE (station_name)
);

DROP TABLE IF EXISTS routes;

CREATE TABLE routes (
    id BIGSERIAL PRIMARY KEY CHECK (id > 0),
    departure_station_id BIGSERIAL NOT NULL,
    arrival_station_id BIGSERIAL NOT NULL,
    distance DECIMAL(10, 2) NOT NULL,
    arrival_time VARCHAR(75) NOT NULL,
    FOREIGN KEY (departure_station_id) REFERENCES stations (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (arrival_station_id) REFERENCES stations (id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS schedules;

CREATE TABLE schedules (
    id BIGSERIAL PRIMARY KEY CHECK (id > 0),
    train_id BIGSERIAL NOT NULL,
    route_id BIGSERIAL NOT NULL,
    departure_time TIMESTAMP NOT NULL,
    arrival_time TIMESTAMP NOT NULL,
    FOREIGN KEY (train_id) REFERENCES trains (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (route_id) REFERENCES routes (id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS seats;

CREATE TABLE seats (
    id BIGSERIAL PRIMARY KEY NOT NULL CHECK (id > 0),
    train_id BIGSERIAL NOT NULL,
    seat_number VARCHAR(10) NOT NULL,
    car_number INTEGER NOT NULL,
--     CAR_NUMBER REPRESENTS VAGON
    class VARCHAR (50) NOT NULL,
    FOREIGN KEY (train_id) REFERENCES trains (id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS tickets;

CREATE TABLE tickets (
    id BIGSERIAL NOT NULL PRIMARY KEY CHECK (id > 0),
    customer_id BIGSERIAL NOT NULL,
    schedule_id BIGSERIAL NOT NULL,
    seat_id BIGSERIAL NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    date_of_purchase DATE NOT NULL,
    travel_date DATE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (schedule_id) REFERENCES schedules (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (seat_id) REFERENCES seats (id) ON DELETE CASCADE ON UPDATE CASCADE
);