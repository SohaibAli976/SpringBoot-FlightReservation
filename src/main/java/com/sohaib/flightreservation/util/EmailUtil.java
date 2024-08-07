package com.sohaib.flightreservation.util;

import com.sohaib.flightreservation.services.ReservationServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.hibernate.pretty.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class EmailUtil {
    @Value("${com.sohaib.flightreservation.iteranary.email.subject}")
    public String SUBJECT; //= "Iteranary for your flight";
    @Value("${com.sohaib.flightreservation.iteranary.email.text}")
    public  String TEXT; //= "Please find your Iterinary attached";
    private final JavaMailSender javaMailSender;
    private static final Logger LOGGER=  LoggerFactory.getLogger(EmailUtil.class);

    public EmailUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendItinerary(String toAddress, String filePath) throws MessagingException {
        LOGGER.info("Inside sendItinerary()");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper  mimeMessageHelper= new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(toAddress);
        mimeMessageHelper.setSubject(SUBJECT);
        mimeMessageHelper.setText(TEXT);
        mimeMessageHelper.addAttachment("Iteranary",new File(filePath));
        LOGGER.info("Sending email");
        javaMailSender.send(mimeMessage);
    }
}
