package com.sohaib.flightreservation.controllers;

import com.sohaib.flightreservation.dtos.ReservationRequest;
import com.sohaib.flightreservation.entities.Flight;
import com.sohaib.flightreservation.entities.Reservation;
import com.sohaib.flightreservation.repository.FlightRepository;
import com.sohaib.flightreservation.services.ReservationService;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    private static final Logger LOGGER=  LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    FlightRepository flightRepository;
    @GetMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
        LOGGER.info("showCompleteReservation invoked with the flight id "+flightId);
        Flight flight = flightRepository.findById(flightId).get();
        modelMap.addAttribute("flight",flight);
        return "completeReservation";
    }

    @PostMapping("/completeReservation")
    public String completeReservation(ReservationRequest reservationRequest, ModelMap modelMap) {
        LOGGER.info("completeReservation "+reservationRequest);
        Reservation reservation;
        try {
            reservation = reservationService.addFlight(reservationRequest);
        }catch(ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
        }
       modelMap.addAttribute("msg","Reservation Created Successfully and Reservation id is "+reservation.getId());
       return "reservationConfirmation";
    }
}
