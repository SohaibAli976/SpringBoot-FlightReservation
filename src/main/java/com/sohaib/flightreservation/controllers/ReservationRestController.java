package com.sohaib.flightreservation.controllers;

import com.sohaib.flightreservation.dtos.ReservationUpdateRequest;
import com.sohaib.flightreservation.entities.Reservation;
import com.sohaib.flightreservation.repository.ReservationRepository;
import com.sohaib.flightreservation.util.PDFGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class ReservationRestController {

    @Autowired
    ReservationRepository reservationRepository;

    private static final Logger LOGGER=  LoggerFactory.getLogger(ReservationRestController.class);


    @GetMapping("/reservations/{id}")
    public Optional<Reservation> findReservationById(@PathVariable("id") Long id) {
        LOGGER.info("Inside findReservationById " +id);
        return reservationRepository.findById(id);

    }

    @PostMapping("/reservations")
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest requested) {
        LOGGER.info("Inside Update reservation "+requested);
        Reservation reservation= reservationRepository.findById(requested.getId()).get();
        reservation.setCheckedIn(requested.getCheckedIn());
        reservation.setNumberOfBags(requested.getNumberOfBags());
        LOGGER.info("Saving reservation "+reservation);
        return reservationRepository.save(reservation);
    }

}
