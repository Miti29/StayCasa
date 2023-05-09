package com.staycasa.model;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="bookings")
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="event_date")
	private Date event_date;

	@Column(name="event_start_time")
	private String start_at;
	
	@Column(name="no_of_guest")
	private String no_of_guest;
	
	@Column(name="bookedon")
	private String current_date;
	
	@Column(name="accept_status")
	private int accept_status;

	@ManyToOne
	@JoinColumn(name = "user_id")
    private User user;

	
	@ManyToOne
	private Hotel hotel;

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public Date getEvent_date() {
		return event_date;
	}

	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}
	public String getStart_at() {
		return start_at;
	}

	public void setStart_at(String start_at) {
		this.start_at = start_at;
	}

	public String getNo_of_guest() {
		return no_of_guest;
	}

	public void setNo_of_guest(String no_of_guest) {
		this.no_of_guest = no_of_guest;
	}


	public String getCurrent_date() {
		return current_date;
	}

	public void setCurrent_date(String current_date) {
		this.current_date = current_date;
	}

	public int getAccept_status() {
		return accept_status;
	}

	public void setAccept_status(int accept_status) {
		this.accept_status = accept_status;
	}
	
}

//@Entity
//@Table(name = "bookings")
//public class Booking {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "accept_status")
//    private Integer acceptStatus;
//
//    @Column(name = "bookedon")
//    private String bookedOn;
//
//    @Column(name = "event_date")
//    private LocalDate eventDate;
//
//    @Column(name = "no_of_guest")
//    private String numberOfGuests;
//
//    @Column(name = "event_start_time")
//    private String eventStartTime;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "hotel_id")
//    private Hotel hotel;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public Integer getAcceptStatus() {
//		return acceptStatus;
//	}
//
//	public void setAcceptStatus(Integer acceptStatus) {
//		this.acceptStatus = acceptStatus;
//	}
//
//	public String getBookedOn() {
//		return bookedOn;
//	}
//
//	public void setBookedOn(String bookedOn) {
//		this.bookedOn = bookedOn;
//	}
//
//	public LocalDate getEventDate() {
//		return eventDate;
//	}
//
//	public void setEventDate(LocalDate eventDate) {
//		this.eventDate = eventDate;
//	}
//
//	public String getNumberOfGuests() {
//		return numberOfGuests;
//	}
//
//	public void setNumberOfGuests(String numberOfGuests) {
//		this.numberOfGuests = numberOfGuests;
//	}
//
//	public String getEventStartTime() {
//		return eventStartTime;
//	}
//
//	public void setEventStartTime(String eventStartTime) {
//		this.eventStartTime = eventStartTime;
//	}
//
//	public Hotel getHotel() {
//		return hotel;
//	}
//
//	public void setHotel(Hotel hotel) {
//		this.hotel = hotel;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

    // constructors, getters, setters, etc.
//}

