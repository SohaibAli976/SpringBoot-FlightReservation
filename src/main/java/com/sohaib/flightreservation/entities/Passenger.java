package com.sohaib.flightreservation.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "PASSENGER")
public class Passenger extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @NotBlank(message = "User Name is Mandatory")
    @Length(min = 10,max = 30, message = "User Name should be 30 characters")
    private String firstName;
    @NotBlank(message = "User Name is Mandatory")
    @Length(min = 10,max = 30, message = "User Name should be 30 characters")
    private String lastName;
    @NotBlank(message = "User Name is Mandatory")
    @Length(min = 10,max = 30, message = "User Name should be 30 characters")
    private String middleName;
    private String email;
    private String phone;
}
