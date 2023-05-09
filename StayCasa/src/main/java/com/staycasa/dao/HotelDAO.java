package com.staycasa.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.staycasa.model.Hotel;

@Component
public class HotelDAO extends DAO {
	public HotelDAO() {
		System.out.println("DAO");
		//default constructor
	}
	
	public void updateHotel(String hotelname,String hoteldesc, String location,int price,int id) {
		try {
			System.out.println("miti");
			begin();
			Query query = getSession().createQuery("update hotel set hotel_desc ='" + hoteldesc + "' , hotel_name ='" + hotelname + "', location = '" + location + "', price = '" + price + "' where id = '" + id + "' ");
			query.executeUpdate();
			commit();
			close();
		}catch(Exception e) {
			rollback();
			System.out.println("Error updateHotel : " +e);
		}
	}

	public void updateHotelwithImage(String hotelname,String hoteldesc, String location,int price, String image, int id) {
		try {
			System.out.println("miti");
			begin();
			Query query = getSession().createQuery("update hotel set hotel_desc ='" + hoteldesc + "' , hotel_name ='" + hotelname + "', location = '" + location + "', price = '" + price + "', image = '" +image +"' where id = '" + id + "' ");
			query.executeUpdate();
			commit();
			close();
		}catch(Exception e) {
			rollback();
			System.out.println("Error updateHotelwithImage : " +e);
		}
	}

	public void save(Hotel hotel) {
		try {
            begin();
            getSession().save(hotel);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Error save : " +e);
        }
		
	}

	public List<Hotel> findAll() {
		Query query = getSession().createQuery("FROM hotel");
		List<Hotel> list = query.list();
		System.out.println(list + "  list of hotels are being printed");
		return list;
	}

	public Object findById(int id) {
		// TODO Auto-generated method stub
		return getSession().get(Hotel.class, id);
		//getSession().get
	}

	public void deleteById(int id) {
		try {
			begin();
			Query query = getSession().createQuery("delete from hotel where id = '" + id + "' ");
			query.executeUpdate();
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Error deleteById : " +e);
        }
	}

	public long count() {
		long count = 0;
		//SELECT COUNT(*) FROM tablename
		try {
			begin();
			Query query = getSession().createQuery("SELECT COUNT(*) FROM hotel");
			//count = (long) query.uniqueResult();
			count = (Long)query.uniqueResult();
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Error count : " +e);
        }
		
		return count;
	}

}