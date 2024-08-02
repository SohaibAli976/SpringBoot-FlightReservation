package com.sohaib.flightreservation.services;

import com.sohaib.flightreservation.dtos.ReservationRequest;
import com.sohaib.flightreservation.entities.Reservation;


public interface ReservationService {

    Reservation addFlight(ReservationRequest reservationRequest);
}
