package com.bogdanjmk.railwaybookingsystem.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(NON_DEFAULT)
public class Route {
    private Long id;
    private Long departureStationId;
    private Long arrivalStationId;
    private int distance;
    private String arrivalTime;
}
