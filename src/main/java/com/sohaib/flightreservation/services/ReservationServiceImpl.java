package com.sohaib.flightreservation.services;

import com.sohaib.flightreservation.dtos.ReservationRequest;
import com.sohaib.flightreservation.entities.Flight;
import com.sohaib.flightreservation.entities.Passenger;
import com.sohaib.flightreservation.entities.Reservation;
import com.sohaib.flightreservation.repository.FlightRepository;
import com.sohaib.flightreservation.repository.PassengerRepository;
import com.sohaib.flightreservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Reservation addFlight(ReservationRequest reservationRequest) {
        Flight flight= flightRepository.findById(reservationRequest.getFlightId()).get();
        Passenger passenger=new Passenger();
        passenger.setEmail(reservationRequest.getPassengerEmail());
        passenger.setFirstName(reservationRequest.getPassengerFirstName());
        passenger.setLastName(reservationRequest.getPassengerLastName());
        passenger.setPhone(reservationRequest.getPassengerPhone());

        Passenger savedPassenger=passengerRepository.save(passenger);

        Reservation reservation=new Reservation();
        reservation.setFlightId(flight);
        reservation.setPassengerId(savedPassenger);
        reservation.setCheckedIn(false);
        return reservationRepository.save(reservation);
    }
}
