package com.staycasa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.staycasa.UserNotFoundException;
import com.staycasa.UserNotFoundException;
import com.staycasa.dao.BookingDAO;
import com.staycasa.dao.UserDAO;
import com.staycasa.model.User;
import com.staycasa.model.*;

@Service
public class UserServices {

//	@Autowired
//	private UserRepo userrepo;
	
	@Autowired
	private UserDAO userDAO;

	public UserDAO getUserrepo() {
		return userDAO;
	}

	public void setUserrepo(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void save(User user) {

		System.out.println("saving...");
		try {
			userDAO.save(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<User> findAll() {
		return userDAO.findAll();
	}

	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		System.out.println("deleting....");
		try {
			BookingDAO bookingDAO = new BookingDAO();
			bookingDAO.deleteAllBookings(id);
			userDAO.deleteById(id);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public User findById(int id) {
		return ((User) userDAO.findById(id));
	}

	public void updateUserDetails(String email, String firstName, String lastName, String gender, String contactno,
			String address, String role, int id) {
		try {
			userDAO.updateUser(email, firstName, lastName, gender, contactno, address, role, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateUserProfile(String email, String firstName, String lastName, String gender, String contactno,
			String address, String role, String password, String cpassword, int id) {
		try {
			userDAO.updateUserpro(email, firstName, lastName, gender, contactno, address, role, password, cpassword, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public long userCount() {
		// TODO Auto-generated method stub
		try {
			return userDAO.countByRole("User");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		try {
			return userDAO.findByEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
		User user=userDAO.findByEmail(email);
		
		if(user!=null) {
				user.setResetPasswordToken(token);
				userDAO.save(user);
		}
		 else {
	            throw new UserNotFoundException("Could not find any customer with the email " + email);
	        }
	}
	 public User getByResetPasswordToken(String token) {
	        try {
				return userDAO.findByResetPasswordToken(token);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	    }
	 
	 public void updatePassword(User user, String newPassword) {
	        user.setPassword(newPassword);
	        user.setConfirmPassword(newPassword);
	        user.setResetPasswordToken(null);
	        try {
				userDAO.save(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

}
