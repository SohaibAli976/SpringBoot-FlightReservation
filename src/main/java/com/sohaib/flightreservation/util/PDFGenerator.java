package com.sohaib.flightreservation.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sohaib.flightreservation.entities.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


@Component
public class PDFGenerator {
    private static final Logger LOGGER=  LoggerFactory.getLogger(PDFGenerator.class);


    public void generateItinerary(Reservation reservation,String filePath) throws FileNotFoundException, DocumentException {
        LOGGER.info("Generating itinerary");
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
        document.add(generateTable(reservation));
        document.close();
    }

    private PdfPTable generateTable(Reservation reservation) {
        LOGGER.info("Generating table");
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);
        table.setSpacingAfter(10);
        PdfPCell pdfPCell;
        pdfPCell=new PdfPCell(new Phrase("Flight Itinerary"));
        pdfPCell.setColspan(2);
        table.addCell(pdfPCell);

        pdfPCell=new PdfPCell(new Phrase("Flight Details"));
        pdfPCell.setColspan(2);
        table.addCell(pdfPCell);

        pdfPCell=new PdfPCell(new Phrase("Departure City"));
        table.addCell(pdfPCell);
        table.addCell(reservation.getFlightId().getDepartureCity());


        pdfPCell=new PdfPCell(new Phrase("Arrival City"));
        table.addCell(pdfPCell);
        table.addCell(reservation.getFlightId().getArrivalCity());


        pdfPCell=new PdfPCell(new Phrase("Flight Number"));
        table.addCell(pdfPCell);
        table.addCell(reservation.getFlightId().getFlightNumber());


        table.addCell("Departure Date");
        table.addCell(reservation.getFlightId().getDepartureCity());


        table.addCell("Departure Time");
        table.addCell(String.valueOf(reservation.getFlightId().getEstimatedDepartureTime()));


        pdfPCell=new PdfPCell(new Phrase("Passenger Details"));
        pdfPCell.setColspan(2);
        table.addCell(pdfPCell);

        table.addCell("First Name");
        table.addCell(String.valueOf(reservation.getPassengerId().getFirstName()));

        table.addCell("Last Name");
        table.addCell(String.valueOf(reservation.getPassengerId().getLastName()));

        table.addCell("Email");
        table.addCell(String.valueOf(reservation.getPassengerId().getEmail()));

        table.addCell("Phone Number");
        table.addCell(String.valueOf(reservation.getPassengerId().getPhone()));
        LOGGER.info("Table Created");
        return table;
    }
}
