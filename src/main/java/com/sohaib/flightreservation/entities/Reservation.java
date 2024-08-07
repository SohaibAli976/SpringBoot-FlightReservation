package com.sohaib.flightreservation.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Table(name = "RESERVATION")
@Entity
public class Reservation extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean checkedIn;
    private int numberOfBags;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    private Passenger passengerId;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", checkedIn=" + checkedIn +
                ", numberOfBags=" + numberOfBags +
                ", passengerId=" + passengerId +
                ", flightId=" + flightId +
                ", created=" + created +
                '}';
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="flight_id",referencedColumnName = "id")
    private Flight flightId;

    private Timestamp created;

}
