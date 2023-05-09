package com.staycasa;

import java.awt.Color;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.staycasa.model.Booking;

public class UserPDFExporter {

    private Booking booking;

    public UserPDFExporter(Booking booking) {
        // TODO Auto-generated constructor stub

        this.booking = booking;

    }

    public void export(HttpServletResponse response) {

    	try {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        com.lowagie.text.Font titlefont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        titlefont.setSize(20);
        titlefont.setColor(Color.BLUE);

        com.lowagie.text.Font parafont = FontFactory.getFont(FontFactory.HELVETICA);
        parafont.setSize(10);
        parafont.setColor(Color.BLACK);

        Paragraph title = new Paragraph("STAYCASA", titlefont);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        document.add(new Paragraph("Booking id : " + booking.getId(), parafont));

        document.add(new Paragraph(" ", parafont));

        document.add(new Paragraph("Name : " + booking.getUser().getFirstName() + " " + booking.getUser().getLastName(),
                parafont));

        document.add(new Paragraph(" ", parafont));

        document.add(new Paragraph("Email : " + booking.getUser().getEmail(), parafont));

        document.add(new Paragraph(" ", parafont));

        document.add(new Paragraph("Phone Number : " + booking.getUser().getContactno(), parafont));

        document.add(new Paragraph(" ", parafont));

        document.add(new Paragraph("Address : " + booking.getUser().getAddress(), parafont));

        document.add(new Paragraph(" ", parafont));

        document.add(new Paragraph("Hotel Name : " + booking.getHotel().getHotelName(), parafont));

        document.add(new Paragraph(" ", parafont));

        document.add(new Paragraph("Hotel Location : " + booking.getHotel().getLocation(), parafont));

        document.add(new Paragraph(" ", parafont));

        document.add(new Paragraph("Booked on : " + booking.getCurrent_date(), parafont));

        document.add(new Paragraph(" ", parafont));

        document.add(new Paragraph("Event Date: " + booking.getEvent_date(), parafont));

        document.add(new Paragraph(" ", parafont));

        document.add(new Paragraph("Hotel  Price : $." + booking.getHotel().getPrice() + " per hour", parafont));

        document.add(new Paragraph(" ", parafont));

        document.add(new Paragraph("Number of guest : " + booking.getNo_of_guest(), parafont));

        com.lowagie.text.Font endfont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        endfont.setSize(20);
        endfont.setColor(Color.PINK);
        Paragraph end = new Paragraph("Enjoy your Stay");
        end.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(end);
        document.close();
    	}
    	catch( Exception ex) {
    		System.out.println("Error while generating pdf : " +ex);
    	}

    }
}
