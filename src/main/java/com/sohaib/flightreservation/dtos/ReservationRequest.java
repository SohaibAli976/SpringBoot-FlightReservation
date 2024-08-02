package com.sohaib.flightreservation.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequest {

    private Long flightId;
    private String passengerFirstName;
    private String passengerLastName;
    private String passengerEmail;
    private String passengerPhone;
    private String nameOnTheCard;
    private String cardNumber;
    private String expirationDate;
    private String securityCode;

}
