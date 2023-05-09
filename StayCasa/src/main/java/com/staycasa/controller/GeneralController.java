package com.staycasa.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.staycasa.model.User;
import com.staycasa.service.BookingServices;
import com.staycasa.service.HotelServices;
import com.staycasa.service.UserServices;


@Controller
public class GeneralController {
	
	@Autowired
	private UserServices userservice;
	
	@Autowired
	private HotelServices hotelservice;

	@Autowired
	private BookingServices bookingservice;
	
	@RequestMapping(value="/",method= RequestMethod.GET)
	public String home() {
		return "index";
	}
	
	@RequestMapping(value="/signin",method= RequestMethod.GET)
	public String login() {
		return "Login";
	}
	
	@RequestMapping(value="/signup",method= RequestMethod.GET)
	public String register() {
		return "UserRegisteration";
	}
	
	@RequestMapping(value="/aboutus",method= RequestMethod.GET)
	public String aboutus() {
		return "Aboutus";
	}
	
	@RequestMapping(value="/userhome",method=RequestMethod.GET)
	public String userhome(ModelMap model,HttpSession session) {
		try {
		if(session.getAttribute("User_id")==null) {
			return "redirect:/signin";
		}else {
		int id=(int) session.getAttribute("User_id");
		
		long Hotelcount=hotelservice.hotelCount();
		model.addAttribute("user_hotel_count",Hotelcount);
		
		long Bookingcount=bookingservice.bookingcountById(id);
		model.addAttribute("user_booking_count",Bookingcount);
		
		long BookingcanceledbyAdmin=bookingservice.bookingcountcancelByAdminById(id);
		model.addAttribute("user_bookingcancelbyadmin_count",BookingcanceledbyAdmin);
		
		long BookingcanceledbyUser=bookingservice.bookingcountcancelByUserById(id);
		model.addAttribute("user_bookingcancelbyuser_count",BookingcanceledbyUser);
		
		long BookingPendingByUser=bookingservice.bookingPendingcountById(id);
		model.addAttribute("user_bookingpending_count",BookingPendingByUser);
			return "Userhome";
		}
		}catch(Exception ex) {
			System.out.println("error userhome : " +ex);
		}
		return "Userhome";
		
	}
	
	@RequestMapping(value="/adminhome",method=RequestMethod.GET)
	public String adminhome(ModelMap model) {
		try {
		
		long Usercount=userservice.userCount();
		model.addAttribute("admin_user_count",Usercount);
		
		long Hotelcount=hotelservice.hotelCount();
		model.addAttribute("admin_hotel_count",Hotelcount);

		long Bookingcount=bookingservice.bookingcount();
		model.addAttribute("admin_booking_count",Bookingcount);
		
		long BookingcanceledbyAdmin=bookingservice.bookingcountcancelByAdmin();
		model.addAttribute("admin_bookingcancelbyadmin_count",BookingcanceledbyAdmin);
		
		long BookingcanceledbyUser=bookingservice.bookingcountcancelByUser();
		model.addAttribute("admin_bookingcancelbyuser_count",BookingcanceledbyUser);
		
		long Bookingpending=bookingservice.bookingpendingcount();
		model.addAttribute("admin_bookingpending_count",Bookingpending);
		}catch(Exception ex) {
			System.out.println("error adminhome : " +ex);
		}
		
		return "AdminHome";
	}

	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
	    if (session != null) {
	        session.invalidate();
	    }
	    return "redirect:/signin"; 
	}
	
	@RequestMapping(value="/loginfailed",method=RequestMethod.GET)
	public String loginfailed() {
	    return "LoginFailed";
	}
	
}
