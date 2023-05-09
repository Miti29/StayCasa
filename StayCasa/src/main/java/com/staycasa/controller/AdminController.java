package com.staycasa.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.staycasa.model.Booking;
import com.staycasa.model.Hotel;
import com.staycasa.model.User;
import com.staycasa.service.BookingServices;
import com.staycasa.service.HotelServices;
import com.staycasa.service.UserServices;

@Controller
public class AdminController {
		
	@Autowired
	private HotelServices hotelservice;
	
	@Autowired
	private  UserServices userservice;
	
	@Autowired
	private BookingServices bookingservice;
//	
//	@Autowired
//	private JavaMailSender mailSender;
	

	//User Registration
	@RequestMapping(value="/adduserForm",method= RequestMethod.POST)
	public String UserRegister(@ModelAttribute("registerForm") User user,Model model)
	{
		try {
			System.out.println(user);		
			model.addAttribute("user",user);
			userservice.save(user);
			System.out.println("add user Success");
		}catch(Exception ex) {
			System.out.println("Errer adduserform : " +ex);
		}
			
			return "redirect:/adminuserdetails";
		
	}
	
	
	//User Table
	@RequestMapping(value="/adminuserdetails",method=RequestMethod.GET)
	public String adminUserDetails(ModelMap model) {
		try {
		List<User> user=userservice.findAll();
		model.addAttribute("Userlist",user);
		}catch(Exception ex) {
			System.out.println("error adminuserdetails : " +ex);
		}
	    return "AdminUserDetails";  
	}
	
	//User Table Delete
	@RequestMapping(value="/admindeleteuser/{email}",method=RequestMethod.GET)
	public String admindeleteUser(@PathVariable String email) {
		try {
		User user=userservice.findByEmail(email);
		System.out.println(user);
		if(user.getEmail()!=null) {
			userservice.deleteUser(user.getId());
			 return "redirect:/adminuserdetails";
		}
		}catch(Exception ex) {
			System.out.println("error admindeleteuser :" +ex);
		}
		
	    return "redirect:/adminuserdetails";  
	}
	
	

	//Model find and fill for User
		@RequestMapping(value="userfind/{id}",method=RequestMethod.GET,produces =MimeTypeUtils.APPLICATION_JSON_VALUE)
		public ResponseEntity<User> adminEditDetails(@PathVariable("id") int id) {
			try {
				return new ResponseEntity<User>(userservice.findById(id),HttpStatus.OK);
			}
		    catch(Exception e) {
		    	return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		    }
			
		}
	
		
		//Edit the user
		@RequestMapping(value="/EdituserForm",method=RequestMethod.POST)
		public String updateUser(@ModelAttribute("userEditForm") User user) {
			try {
			System.out.println(user);
			userservice.updateUserDetails(user.getEmail(),user.getFirstName(),user.getLastName(),user.getGender(),user.getContactno(),user.getAddress(),user.getRole(),user.getId());
			}catch(Exception ex) {
				System.out.println("error edituserform : " +ex);
			}
			return "redirect:/adminuserdetails";
			
		}
		
	
	//Hotel Table
	@RequestMapping(value="/adminhoteldetails",method=RequestMethod.GET)
	public String adminHotelDetails(ModelMap model) {
		try {
		List<Hotel> hotel=hotelservice.findAll();
		model.addAttribute("Hotellist",hotel);
		}catch(Exception ex) {
			System.out.println("error adminhoteldetails: " +ex);
		}
	    return "AdminHotelDetails"; 
		
	}
	
	//Hotel Table Delete
	@RequestMapping(value="/admindeletehotel/{id}")
	public String admindeleteHotel(@PathVariable int id){
		try {
		Hotel hotel=hotelservice.findById(id);
		System.out.println(hotel);
		if(hotel.getHotelName()!=null) {
			hotelservice.deleteHotel(id);
			return "redirect:/adminhoteldetails";
		}
		}catch(Exception ex) {
			System.out.println("error admindeletehotel : " +ex);
		}
		return "redirect:/adminhoteldetails";
	}
	
	//Add Hotel 
	@RequestMapping(value="/addhotelForm")
	public String savehotel(@RequestParam("admin") String role1,@RequestParam("hotelName") String hotelname,@RequestParam("hotelDesc") String hotelDesc,@RequestParam("location") String hotelLoc,@RequestParam("price") int hotelPrice,@RequestParam("hotelImg1") MultipartFile file) throws Exception {
		try {
		hotelservice.savehoteltoDB(file, hotelname, hotelDesc, hotelLoc, hotelPrice);
		if(role1.equals("admin"))
		{
			return "redirect:/adminhoteldetails";
		}
		else {
			return "redirect:/adminhoteldetails";
			}
		}catch(Exception ex) {
			System.out.println("error addhotelForm : " +ex);
		}
		return "redirect:/adminhoteldetails";
	}
	
	//Hotel find
	@RequestMapping(value="hotelfind/{id}",method=RequestMethod.GET,produces =MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> adminhoteEditDetails(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<Hotel>(hotelservice.findById(id),HttpStatus.OK);
		}
	    catch(Exception e) {
	    	return new ResponseEntity<Hotel>(HttpStatus.BAD_REQUEST);
	    }
		
	}
	
	//Edit hotel
	@RequestMapping(value="/EdithotelForm",method=RequestMethod.POST)
	public String updateHotel(@RequestParam("admin") String role1,@RequestParam("hotelName") String hotelname,@RequestParam("hotelDesc") String hotelDesc,@RequestParam("location") String hotelLoc,@RequestParam("price") int hotelPrice,@RequestParam("hotelImg1") MultipartFile file ,@RequestParam("id") int id) throws Exception  {

		try {
		if(file.isEmpty())
		{
			hotelservice.updateHotelDetails(hotelname,hotelDesc,hotelLoc,hotelPrice,id);
		}
		else 
		{
			hotelservice.updateHotelDetailswithImage(hotelname,hotelDesc,hotelLoc,hotelPrice,file,id);
		}
		if(role1.equals("admin"))
		{
			return "redirect:/adminhoteldetails";
		}
		else 
		{
			return "redirect:/adminhoteldetails";
		}
		}catch(Exception ex) {
			System.out.println("error EdithotelForm : " +ex);
		}
		return "redirect:/adminhoteldetails";
		
	}
	
	//Booking details
	@RequestMapping(value="/adminbookingdetails",method=RequestMethod.GET)
	public String adminBookingDetails(ModelMap model) {
		try {
		List<Booking> booking=bookingservice.findAll();
		System.out.println(booking+"  printing all booking details");
		model.addAttribute("admin_booking",booking);
		}catch(Exception ex) {
			System.out.println("error adminbookingdetails : " +ex);
		}
	    return "AdminBookingDetails";  
	}

	//Admin Account
	@RequestMapping(value="/adminaccount",method=RequestMethod.GET)
	public String adminAccount(HttpSession session) {
		System.out.println(session.getAttribute("Admin_email"));
	    return "AdminAccount";  
	}
	
	@RequestMapping(value="/editadminprofile",method=RequestMethod.POST)
	public String updateAdminProfile(@ModelAttribute("adminEditProfile") User admin ,HttpSession session) {
		try {
		System.out.println(admin);
		
		userservice.updateUserProfile(admin.getEmail(),admin.getFirstName(),admin.getLastName(),admin.getGender(),admin.getContactno(),admin.getAddress(),admin.getRole(),admin.getPassword(),admin.getConfirmPassword(),admin.getId());
		session.setAttribute("Admin_firstname",admin.getFirstName());
		session.setAttribute("Admin_lastname", admin.getLastName());
		session.setAttribute("Admin_email", admin.getEmail());
		session.setAttribute("Admin_phone", admin.getContactno());
		session.setAttribute("Admin_address", admin.getAddress());
		session.setAttribute("Admin_gender", admin.getGender());
		session.setAttribute("Admin_id", admin.getId());
		session.setAttribute("Admin_role", admin.getRole());
		session.setAttribute("Admin_cpassword", admin.getConfirmPassword());
		session.setAttribute("Admin_password", admin.getPassword());
		}catch(Exception ex) {
			System.out.println("error editadminprofile : " +ex);
		}
	
		return "redirect:/adminhome";
		
	}
	
	
		
		@RequestMapping(value="/bookcancelbyadmin",method= RequestMethod.POST)
		public String UserBookingCancelAdmin(@RequestParam("booking_id") int booking_id)
		{
			try {
				bookingservice.bookingcancelByAdmin(booking_id);
			}catch(Exception ex) {
				System.out.println("error bookcancelbyadmin : " +ex);
			}
				return "redirect:/adminbookingdetails";
		
		}
		
		
		@RequestMapping(value="/bookacceptbyadmin",method= RequestMethod.POST)
		public String UserBookingAcceptAdmin(@RequestParam("booking_id") int booking_id) throws MessagingException, UnsupportedEncodingException
		{
			try {
			Booking booking=	bookingservice.findById(booking_id);
//			  MimeMessage msg = mailSender.createMimeMessage();              
//			    MimeMessageHelper helper = new MimeMessageHelper(msg);
//			     
//			    helper.setFrom("libraryneu2022@gmail.com", "StayCasa");
//			    helper.setTo(booking.getUser().getEmail());
//			     
//			    String subject = "Booking confirmed";
//			     
//			    String content = "<p>Hello,</p>" +booking.getUser().getLastName()
//			            + "<p>Your booking is confirmed, have a pleasant stay.</p>";
//			     
//			    helper.setSubject(subject);
//			     
//			    helper.setText(content, true);
//			     
//			    mailSender.send(msg);
				
			
				bookingservice.bookingacceptByAdmin(booking_id);
			}catch(Exception ex) {
				System.out.println("error bookacceptbyadmin : " +ex);
			}
				return "redirect:/adminbookingdetails";
		
		}
		 
		 @RequestMapping(value="/adminlogout",method=RequestMethod.GET)
			public String adminlogout(HttpSession session) {
			    if (session != null) {
			        try {
			        session.removeAttribute("Admin_firstname");
					session.removeAttribute("Admin_lastname");
					session.removeAttribute("Admin_email");
					session.removeAttribute("Admin_phone");
					session.removeAttribute("Admin_address");
					session.removeAttribute("Admin_gender");
					session.removeAttribute("Admin_id");
					session.removeAttribute("Admin_cpassword");
					session.removeAttribute("Admin_password");
					session.removeAttribute("Admin_role");
			        }catch(Exception ex) {
			        	System.out.println("error adminlogout : " +ex);
					}
			    }
			    return "redirect:/signin"; 
			}

}
