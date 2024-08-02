package com.sohaib.flightreservation.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name="FLIGHT")
@Entity
public class Flight extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FLIGHT_NUMBER")
    private String flightNumber;

    @Column(name = "DEPARTURE_CITY")
    private String departureCity;

    @Column(name = "OPERATING_AIRLINES")
    private String operatingAirlines;

    @Column(name = "ARRIVAL_CITY")
    private String arrivalCity;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATE_OF_DEPARTURE")
    private LocalDate dateOfDeparture;

    @Column(name = "ESTIMATED_DEPARTURE_TIME")
    private Timestamp estimatedDepartureTime;

}
