package com.sohaib.flightreservation.controllers;

import com.sohaib.flightreservation.entities.Flight;
import com.sohaib.flightreservation.repository.FlightRepository;
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

    @GetMapping("/findFlights")
    public String showFindFlights() {
        return "findFlights";
    }

    @PostMapping("/findFlights")
    public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate, ModelMap modelMap) {
        List<Flight> flightList = //flightRepository.findAll();
                flightRepository.findFlights(from, to, departureDate);
        System.out.println("Departure Date: " + departureDate);
        System.out.println("From: " + from);
        System.out.println("To: " + to);
        modelMap.addAttribute("flights", flightList);
        return "displayFlights";
    }


}
