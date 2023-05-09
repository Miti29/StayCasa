package com.staycasa.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.staycasa.UserPDFExporter;
import com.staycasa.Utility;
import com.staycasa.model.Booking;
import com.staycasa.model.Hotel;
import com.staycasa.model.User;
import com.staycasa.service.BookingServices;
import com.staycasa.service.HotelServices;
import com.staycasa.service.UserServices;

@Controller

public class UserController {

	@Autowired
	private UserServices userservice;

	@Autowired
	private HotelServices hotelservice;

	@Autowired
	private BookingServices bookingservice;

	@Autowired
	private JavaMailSender mailSender;
	

	@RequestMapping(value = "/registerForm", method = RequestMethod.POST)
	public String UserRegister(@ModelAttribute("registerForm") User user, Model model) {
		System.out.println("User reg form confirmed");

		
		model.addAttribute("user", user);
		System.out.println("after model add attribute User reg form confirmed");
		User result = userservice.findByEmail(user.getEmail());
		System.out.println(result + "" + "after userservice User reg form confirmed");
		if (result != null) {

			model.addAttribute("reg_error", "User Email Already Taken");

			return "UserRegisteration";
		} else {
			System.out.println(user.getFirstName() + " "+ user.getLastName()+" "+ user.getEmail()+ " new user registration");
			userservice.save(user);
			return "redirect:/signin";
		}	
//			MimeMessage m = mailSender.createMimeMessage();
//			MimeMessageHelper ms = new MimeMessageHelper(m);
//			MimeMessage msg = mailSender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(msg);
//			
//			try {
//				try {
//					helper.setFrom("staycasa123@gmail.com", "St@yCasa12");
//				} catch (UnsupportedEncodingException e) {
//					System.out.println("staycasa123@gmail.com , StayCasa error error");
//					e.printStackTrace();
//				}
//				helper.setTo(user.getEmail());
//
//				String subject = "Welcome to StayCasa !";
//
//				String content = "<p>Hello, </p>" + user.getFirstName() + "<p>Welcome to INSTASTAY</p>";
//
//				helper.setSubject(subject);
//
//				helper.setText(content, true);
//			} catch (MessagingException e) {
//				// TODO Auto-generated catch block
//				System.out.println("Exception  MimeMessage exception");
//				e.printStackTrace();
//			}
//			System.out.println(msg+" "+" "+" msg not found Exception MimeMessage");
//			mailSender.send(msg);
//			userservice.save(user);
//			return "redirect:/signin";
//		}
	}

	@RequestMapping(value = "/login-validation", method = RequestMethod.POST)
	public String UserLogin(@ModelAttribute("loginForm") User user, HttpSession session) {
		try {

			User userDetail = userservice.findByEmail(user.getEmail());
			System.out.println("My name is :"+ user);

			if (userDetail != null) {
				if (userDetail.getPassword().equals(user.getPassword())) {
					if (userDetail.getRole().equals("Admin")) {
						session.setAttribute("Admin_firstname", userDetail.getFirstName());
						session.setAttribute("Admin_lastname", userDetail.getLastName());
						session.setAttribute("Admin_email", userDetail.getEmail());
						session.setAttribute("Admin_phone", userDetail.getContactno());
						session.setAttribute("Admin_address", userDetail.getAddress());
						session.setAttribute("Admin_gender", userDetail.getGender());
						session.setAttribute("Admin_id", userDetail.getId());
						session.setAttribute("Admin_cpassword", userDetail.getConfirmPassword());
						session.setAttribute("Admin_password", userDetail.getPassword());
						session.setAttribute("Admin_role", userDetail.getRole());
						return "redirect:/adminhome";
					}

					else if (userDetail.getRole().equals("User")) {

						session.setAttribute("User_firstname", userDetail.getFirstName());
						session.setAttribute("User_lastname", userDetail.getLastName());
						session.setAttribute("User_email", userDetail.getEmail());
						session.setAttribute("User_phone", userDetail.getContactno());
						session.setAttribute("User_address", userDetail.getAddress());
						session.setAttribute("User_gender", userDetail.getGender());
						session.setAttribute("User_id", userDetail.getId());
						session.setAttribute("User_cpassword", userDetail.getConfirmPassword());
						session.setAttribute("User_password", userDetail.getPassword());
						session.setAttribute("User_role", userDetail.getRole());
						return "redirect:/userhome";
					}

				}
			}
		} catch (Exception ex) {
			System.out.println("error login-validation : " + ex);
		}

		return "redirect:/loginfailed";

	}

	@RequestMapping(value = "/usernewbooking", method = RequestMethod.GET)
	public String userBookingForm(ModelMap model) {
		try {
			List<Hotel> hotel = hotelservice.findAll();
			model.addAttribute("hotel_for_booking", hotel);
		} catch (Exception ex) {
			System.out.println("error usernewbooking : " + ex);
		}

		return "UserBookingForm";
	}

	@RequestMapping(value = "/userbookingdetails", method = RequestMethod.GET)
	public String userBookingDetails(HttpSession session, ModelMap model) {
		try {
			if (session.getAttribute("User_id") == null) {
				return "redirect:/signin";
			} else {
				int id = (int) session.getAttribute("User_id");
				User user = userservice.findById(id);

				List<Booking> bookings = bookingservice.findAllByUser(user);

				session.setAttribute("User_bookings", bookings);
				return "UserBookingDetails";
			}
		} catch (Exception ex) {
			System.out.println("error userbookingdetails : " + ex);
		}
		return "UserBookingDetails";
	}

	@RequestMapping(value = "/useraccount", method = RequestMethod.GET)
	public String userAccount() {
		return "UserAccount";
	}

	@RequestMapping(value = "/EdituserProfile", method = RequestMethod.POST)
	public String updateUserProfile(@ModelAttribute("userEditProfile") User user, HttpSession session) {
		try {
			userservice.updateUserProfile(user.getEmail(), user.getFirstName(), user.getLastName(), user.getGender(),
					user.getContactno(), user.getAddress(), user.getRole(), user.getPassword(),
					user.getConfirmPassword(), user.getId());
			session.setAttribute("User_firstname", user.getFirstName());
			session.setAttribute("User_lastname", user.getLastName());
			session.setAttribute("User_email", user.getEmail());
			session.setAttribute("User_phone", user.getContactno());
			session.setAttribute("User_address", user.getAddress());
			session.setAttribute("User_gender", user.getGender());
			session.setAttribute("User_id", user.getId());
			session.setAttribute("User_role", user.getRole());
			session.setAttribute("User_cpassword", user.getConfirmPassword());
			session.setAttribute("User_password", user.getPassword());
		} catch (Exception ex) {
			System.out.println("error EdituserProfile : " + ex);
		}

		return "redirect:/useraccount";

	}

	@RequestMapping(value = "/userhoteldetails", method = RequestMethod.GET)
	public String userHotelDetails(ModelMap model) {
		try {
			List<Hotel> hotel = hotelservice.findAll();
			model.addAttribute("hotellist", hotel);
		} catch (Exception ex) {
			System.out.println("error userhoteldetails : " + ex);
		}
		return "UserHotelDetails";
	}

	@RequestMapping(value = "hotelbookfind/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> hotelPriceDetails(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<Hotel>(hotelservice.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Hotel>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "hotelbookingfind/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Booking>> hotelbookingDetails(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<List<Booking>>(bookingservice.findByHotelId(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Booking>>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/makeBookingForm", method = RequestMethod.POST)
	public String UserBooking(@ModelAttribute("makeBookingForm") Booking booking, @RequestParam("user_id") int user_id,
			@RequestParam("hotel_id") int hotel_id) {
		try {
			User user = userservice.findById(user_id);
			Hotel hotel = hotelservice.findById(hotel_id);

			booking.setHotel(hotel);
			booking.setUser(user);
			System.out.println(booking+" booking to be set");
			bookingservice.save(booking);
			System.out.println("Success");
		} catch (Exception ex) {
			System.out.println("Creating Booking " + ex);
			return "redirect:/userhome";
		}

		return "redirect:/userbookingdetails";

	}

	@RequestMapping(value = "/bookcancelbyuser", method = RequestMethod.POST)
	public String UserBookingCancel(@RequestParam("booking_id") int booking_id) {
		try {
			bookingservice.bookingcancelByUser(booking_id);
		} catch (Exception ex) {
			System.out.println("error bookcancelbyuser : " + ex);
		}
		return "redirect:/userbookingdetails";

	}

	@GetMapping(value = "/userbookingdetails/export")
	public String Bill(@RequestParam("booking_id") int booking_id, HttpServletResponse response) throws IOException {
		try {
			Booking booking = bookingservice.findById(booking_id);
			response.setContentType("application/pdf");
			DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
			String currentDateTime = dateFormatter.format(new Date());
			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=Inovice generated on:" + currentDateTime + ".pdf";
			response.setHeader(headerKey, headerValue);

			UserPDFExporter exporter = new UserPDFExporter(booking);
			exporter.export(response);
		} catch (Exception ex) {
			System.out.println("error userbookingdetails/export : " + ex);
		}

		return null;

	}

	@RequestMapping(value = "/userlogout", method = RequestMethod.GET)
	public String userlogout(HttpSession session) {
		if (session != null) {
			try {
				session.removeAttribute("User_firstname");
				session.removeAttribute("User_lastname");
				session.removeAttribute("User_email");
				session.removeAttribute("User_phone");
				session.removeAttribute("User_address");
				session.removeAttribute("User_gender");
				session.removeAttribute("User_id");
				session.removeAttribute("User_cpassword");
				session.removeAttribute("User_password");
				session.removeAttribute("User_role");
			} catch (Exception ex) {
				System.out.println("error userlogout : " + ex);
			}
		}
		return "redirect:/signin";
	}

	@GetMapping("/forgot_password")
	public String showForgotPasswordForm() {
		return "forgot_password_form";
	}

	@PostMapping("/forgot_password")
	public String processForgotPassword(HttpServletRequest request, Model model)
			throws UnsupportedEncodingException, MessagingException {
		String email = request.getParameter("email");
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(20);
		for (int i = 0; i < 20; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		String token = sb.toString();
		// RandomString.make(30);
		// String token1 = Random();

		try {
			userservice.updateResetPasswordToken(token, email);
			String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;

			sendEmail(email, resetPasswordLink);
			model.addAttribute("msg", "We have sent a reset password link to your email. Please check.");

		} catch (Exception ex) {
			model.addAttribute("error", ex.getMessage());
		}

		return "forgot_password_form";
	}

	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg);

			helper.setFrom("libraryneu2022@gmail.com", "StayCasa");
			helper.setTo(recipientEmail);

			String subject = "Here's the link to reset your password";

			String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
					+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + link
					+ "\">Change my password</a></p>" + "<br>"
					+ "<p>Ignore this email if you do remember your password, "
					+ "or you have not made the request.</p>";

			helper.setSubject(subject);

			helper.setText(content, true);

			mailSender.send(msg);
		} catch (Exception ex) {
			System.out.println("error send email : " + ex);
		}
	}

	@GetMapping("/reset_password")
	public String showResetPasswordForm(@RequestParam(value = "token") String token, Model model) {
		try {
			User customer = userservice.getByResetPasswordToken(token);
			model.addAttribute("token", token);

			if (customer == null) {
				model.addAttribute("msg", "Invalid Token");
				return "forgot_password_form";
			}
		} catch (Exception ex) {
			System.out.println("error reset_password : " + ex);
		}

		return "reset_password_form";
	}

	@PostMapping("/reset_password")
	public String processResetPassword(HttpServletRequest request, Model model) {
		try {
			String token = request.getParameter("token");
			String password = request.getParameter("password");

			User customer = userservice.getByResetPasswordToken(token);
			model.addAttribute("title", "Reset your password");

			if (customer == null) {
				model.addAttribute("msg", "Invalid Token");
				return "forgot_password_form";
			} else {
				userservice.updatePassword(customer, password);

				model.addAttribute("msg", "You have successfully changed your password.");
			}
		} catch (Exception ex) {
			System.out.println("error reset_password : " + ex);
		}
		return "forgot_password_form";
	}

}
