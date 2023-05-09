package com.staycasa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staycasa.dao.BookingDAO;
import com.staycasa.model.Booking;
import com.staycasa.model.User;
import com.staycasa.model.*;

@Service
public class BookingServices {
	
	@Autowired
	private BookingDAO bookingDAO;

	public BookingDAO getBookingrepo() {
		return bookingDAO;
	}

	public void setBookingrepo(BookingDAO bookingDAO) {
		this.bookingDAO = bookingDAO;
	}

	public void save(Booking booking) throws InterruptedException {

		System.out.println("save booking ");
		bookingDAO.save(booking);
	}

	public Booking findById(int id) {
		System.out.println("find booking by id");
		return (Booking) bookingDAO.findById(id);

	}

	public List<Booking> findAllByUser(User user) {
		System.out.println("find all user booking ");
		// TODO Auto-generated method stub
		return bookingDAO.findAllByUser(user);
	}

	public void bookingcancelByUser(int id) {
		System.out.println("bookingcancelByUser");
		try {
			bookingDAO.bookcancelByUser(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Booking> findAll() {
		return bookingDAO.findAll();
	}

	public void bookingcancelByAdmin(int booking_id) {
		// TODO Auto-generated method stub
		try {
			bookingDAO.bookcancelByadmin(booking_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void bookingacceptByAdmin(int booking_id) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("bookingacceptByAdmin");
		bookingDAO.bookacceptByadmin(booking_id);
	}

	public long bookingcount() {
		// TODO Auto-generated method stub
		System.out.println("7676:");
		System.out.println(bookingDAO.count());
		return bookingDAO.count();
	}

	public long bookingcountById(int id) {
		// TODO Auto-generated method stub
		return bookingDAO.bookingcountById(id);
	}

	public long bookingcountcancelByAdmin() {
		// TODO Auto-generated method stub
		return bookingDAO.bookingcountcancelByAdmin();
	}

	public long bookingcountcancelByUser() {
		return bookingDAO.bookingcountcancelByUser();
	}

	public long bookingcountcancelByAdminById(int id) {
		// TODO Auto-generated method stub
		return bookingDAO.bookingcountcancelByAdminById(id);
	}

	public long bookingcountcancelByUserById(int id) {
		// TODO Auto-generated method stub
		return bookingDAO.bookingcountcancelByUserById(id);
	}

	public long bookingpendingcount() {
		// TODO Auto-generated method stub
		return bookingDAO.allBookingPendingCount();
	}

	public long bookingPendingcountById(int id) {
		// TODO Auto-generated method stub
		return bookingDAO.bookingpendingcountById(id);
	}

	public List<Booking> findByHotelId(int id) {
		// TODO Auto-generated method stub
		return bookingDAO.findHotelById(id);
	}

}
