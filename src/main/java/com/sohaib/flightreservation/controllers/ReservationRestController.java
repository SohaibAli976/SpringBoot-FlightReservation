package com.sohaib.flightreservation.controllers;

import com.sohaib.flightreservation.dtos.ReservationUpdateRequest;
import com.sohaib.flightreservation.entities.Reservation;
import com.sohaib.flightreservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class ReservationRestController {

    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/reservations/{id}")
    public Optional<Reservation> findReservationById(@PathVariable("id") Long id) {
        return reservationRepository.findById(id);

    }

    @PostMapping("/reservations")
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest requested) {
        Reservation reservation= reservationRepository.findById(requested.getId()).get();
        reservation.setCheckedIn(requested.getCheckedIn());
        reservation.setNumberOfBags(requested.getNumberOfBags());
        return reservationRepository.save(reservation);
    }

}
