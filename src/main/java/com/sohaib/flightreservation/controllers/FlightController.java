package com.sohaib.flightreservation.controllers;

import com.sohaib.flightreservation.entities.Flight;
import com.sohaib.flightreservation.repository.FlightRepository;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    private static final Logger LOGGER=  LoggerFactory.getLogger(FlightController.class);

    @GetMapping("/findFlights")
    public String showFindFlights() {
        return "findFlights";
    }

    @PostMapping("/findFlights")
    public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate, ModelMap modelMap) {
        LOGGER.info("Inside findFlights From: "+from+" To: "+to+" DepartureDate: "+departureDate);
        List<Flight> flightList = //flightRepository.findAll();
                flightRepository.findFlights(from, to, departureDate);
        LOGGER.info("Flights found are "+flightList);
        modelMap.addAttribute("flights", flightList);
        return "displayFlights";
    }


}
