package com.sohaib.flightreservation.controllers;

import com.sohaib.flightreservation.dtos.ReservationRequest;
import com.sohaib.flightreservation.entities.Flight;
import com.sohaib.flightreservation.entities.Reservation;
import com.sohaib.flightreservation.repository.FlightRepository;
import com.sohaib.flightreservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @Autowired
    FlightRepository flightRepository;
    @GetMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
        Flight flight = flightRepository.findById(flightId).get();
        modelMap.addAttribute("flight",flight);
        return "completeReservation";
    }

    @PostMapping("/completeReservation")
    public String completeReservation(ReservationRequest reservationRequest, ModelMap modelMap) {

       Reservation reservation= reservationService.addFlight(reservationRequest);
       modelMap.addAttribute("msg","Reservation Created Successfully and Reservation id is "+reservation.getId());
       return "reservationConfirmation";
    }
}
