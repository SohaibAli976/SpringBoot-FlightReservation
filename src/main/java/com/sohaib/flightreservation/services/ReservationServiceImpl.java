package com.sohaib.flightreservation.services;

import com.itextpdf.text.DocumentException;
import com.sohaib.flightreservation.controllers.FlightController;
import com.sohaib.flightreservation.dtos.ReservationRequest;
import com.sohaib.flightreservation.entities.Flight;
import com.sohaib.flightreservation.entities.Passenger;
import com.sohaib.flightreservation.entities.Reservation;
import com.sohaib.flightreservation.repository.FlightRepository;
import com.sohaib.flightreservation.repository.PassengerRepository;
import com.sohaib.flightreservation.repository.ReservationRepository;
import com.sohaib.flightreservation.util.EmailUtil;
import com.sohaib.flightreservation.util.PDFGenerator;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Value("$com.sohaib.flightreservation.iteranary.dirpath")
    public String ITERANARY_DIR;
    //= "D:/One Drive/OneDrive/Desktop/reservations/reservation";
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    PDFGenerator pdfGenerator;

    @Autowired
    EmailUtil emailUtil;

    private static final Logger LOGGER=  LoggerFactory.getLogger(ReservationServiceImpl.class);



    @Override
    @Transactional
    public Reservation addFlight(ReservationRequest reservationRequest) {
        LOGGER.info("Inside addFlight()");
        Flight flight= flightRepository.findById(reservationRequest.getFlightId()).get();
        LOGGER.info("Fetching flight id for flight id "+reservationRequest.getFlightId());
        Passenger passenger=new Passenger();
        passenger.setEmail(reservationRequest.getPassengerEmail());
        passenger.setFirstName(reservationRequest.getPassengerFirstName());
        passenger.setLastName(reservationRequest.getPassengerLastName());
        passenger.setPhone(reservationRequest.getPassengerPhone());
        LOGGER.info("saving the passenger "+passenger);
        Passenger savedPassenger=passengerRepository.save(passenger);

        Reservation reservation=new Reservation();
        reservation.setFlightId(flight);
        reservation.setPassengerId(savedPassenger);
        reservation.setCheckedIn(false);
        LOGGER.info("Saving the reservation "+reservation);
        Reservation reservation1=reservationRepository.save(reservation);
        String path= ITERANARY_DIR +reservation1.getId()+".pdf";

        try {
            LOGGER.info("Generating the Itinerary");
            pdfGenerator.generateItinerary(reservation1,path);
            LOGGER.info("Emailing the Itinerary");
            emailUtil.sendItinerary(passenger.getEmail(),path);
        } catch (FileNotFoundException | DocumentException | MessagingException e) {
            throw new RuntimeException(e);
        }
        return reservationRepository.save(reservation);
    }
}
