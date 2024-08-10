package com.sohaib.flightreservation;

import com.itextpdf.text.DocumentException;
import com.sohaib.flightreservation.dtos.ReservationRequest;
import com.sohaib.flightreservation.entities.Flight;
import com.sohaib.flightreservation.entities.Passenger;
import com.sohaib.flightreservation.entities.Reservation;
import com.sohaib.flightreservation.repository.FlightRepository;
import com.sohaib.flightreservation.repository.PassengerRepository;
import com.sohaib.flightreservation.repository.ReservationRepository;
import com.sohaib.flightreservation.services.ReservationServiceImpl;
import com.sohaib.flightreservation.util.EmailUtil;
import com.sohaib.flightreservation.util.PDFGenerator;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.FileNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class ReservationServiceImplTest {

	@Mock
	private ReservationRepository reservationRepository;

	@Mock
	private PassengerRepository passengerRepository;

	@Mock
	private FlightRepository flightRepository;

	@Mock
	PDFGenerator pdfGenerator;

	@Mock
	EmailUtil emailUtil;

	@InjectMocks
	private ReservationServiceImpl reservationService;

	@BeforeEach
	void setUp() {
		// Set the ITERANARY_DIR value for the service
		ReflectionTestUtils.setField(reservationService, "ITERANARY_DIR", "D:/One Drive/OneDrive/Desktop/reservations/reservation");
	}
	@Test
	void testAddFlight() throws DocumentException, MessagingException, FileNotFoundException {
		// Arrange
		ReservationRequest reservationRequest = new ReservationRequest();
		reservationRequest.setFlightId(1L);
		reservationRequest.setPassengerEmail("test@example.com");
		reservationRequest.setPassengerFirstName("John");
		reservationRequest.setPassengerLastName("Doe");
		reservationRequest.setPassengerPhone("1234567890");

		Flight flight = new Flight();
		flight.setId(1L);

		Passenger passenger = new Passenger();
		passenger.setId(1L);

		Reservation reservation = new Reservation();
		reservation.setId(1L);

		when(flightRepository.findById(any(Long.class))).thenReturn(Optional.of(flight));
		when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);
		when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

		// Act
		Reservation result = reservationService.addFlight(reservationRequest);

		// Assert
		assertNotNull(result);
		verify(flightRepository, times(1)).findById(1L);
		verify(passengerRepository, times(1)).save(any(Passenger.class));
		verify(reservationRepository, times(2)).save(any(Reservation.class)); // Saved twice in your code
		verify(pdfGenerator, times(1)).generateItinerary(any(Reservation.class), anyString());
		verify(emailUtil, times(1)).sendItinerary(anyString(), anyString());
	}

}
