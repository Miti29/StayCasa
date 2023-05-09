package com.staycasa.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.staycasa.dao.HotelDAO;
import com.staycasa.model.Hotel;
import com.staycasa.model.*;

@Service
public class HotelServices {
	
	@Autowired
	private HotelDAO hotelDAO;
	

	public void savehoteltoDB(MultipartFile hotelimg1, String hotelName, String hoteldesc, String hotelloc,
			int hotelPrice) throws Exception {

		Hotel h = new Hotel();

		h.setHotelName(hotelName);
		h.setHotelDesc(hoteldesc);
		h.setLocation(hotelloc);
		h.setPrice(hotelPrice);

		try {
			h.setHotelImg1(Base64.getEncoder().encodeToString(hotelimg1.getBytes()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hotelDAO.save(h);
	}

	public List<Hotel> findAll() {
		return hotelDAO.findAll();
	}

	public Hotel findById(int id) {
		return (Hotel) hotelDAO.findById(id);
	}

	public void deleteHotel(int id) {
		System.out.println("deleting...");
		try {
			hotelDAO.deleteById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateHotelDetails(String hotelname, String hoteldesc, String location, int price, int id) {
		try {
			hotelDAO.updateHotel(hotelname, hoteldesc, location, price, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void updateHotelDetailswithImage(String hotelName, String hotelDesc, String location, int price,
			MultipartFile file, int id) throws Exception {
		String image = "";
		try {

			image = Base64.getEncoder().encodeToString(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hotelDAO.updateHotelwithImage(hotelName, hotelDesc, location, price, image, id);

	}

	public long hotelCount(){
		// TODO Auto-generated method stub
		try {
			return hotelDAO.count();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}