package com.staycasa.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.staycasa.model.Booking;
import com.staycasa.model.User;

@Component
public class BookingDAO extends DAO{

	public void save(Booking booking) throws InterruptedException {

		try {
            Session session = getSession();
            Transaction transaction = getSession().getTransaction();
//            if (transaction != null || transaction.isActive()) {
//               commit();
////               close();
//            }
//            try {
//                Thread.sleep(10000); // wait for 1 second
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Transaction t = session.beginTransaction();
            session.save(booking);
            t.commit();
            System.out.println("Booking is saved");
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
        } finally {
            close();
        }
	}
	
	public void deleteAllBookings(int id) {
		try {
			begin();
			Query query = getSession().createQuery("delete from bookings where user_id = '" + id + "' ");
			query.executeUpdate();
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Error deleteById : " +e);
        }
	}

	public Object findById(int id) {
		// TODO Auto-generated method stub
		begin();
		return getSession().get(Booking.class, id);
	}

	public List<Booking> findAll() {
		System.out.println("Hello miti find all user booking");
		Query query = getSession().createQuery("FROM bookings");
		List<Booking> list = query.list();
//		commit();
		return list;
	}

//	public List<Booking> findAllByUser(User user) {
//		// TODO Auto-generated method stub
//		begin();
//		System.out.println("find all miti");
//		Criteria criteria = getSession().createCriteria(Booking.class);
//		criteria.add(Restrictions.eq("user",user ));
//		List <Booking> results = criteria.list();
//		return results;
//		//return null;
//	}

	
	public List<Booking> findAllByUser(User user) {
	    Transaction tx = null;
	    List<Booking> results = null;
	    try {
	        Session session = getSession();
	        tx = session.beginTransaction();
	        System.out.println("find all by users booking miti");
	        Criteria criteria = session.createCriteria(Booking.class);
	        criteria.add(Restrictions.eq("user", user));
	        results = criteria.list();
	        tx.commit(); // commit the transaction
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback(); // rollback the transaction if an exception occurs
	        }
	        throw e;
	    }
	    return results;
	}

	
	public void bookcancelByUser(int id){
		// TODO Auto-generated method stub
		try {
			begin();
			Query query = getSession().createQuery("Update bookings set accept_status=3 where id= " + id);
			query.executeUpdate();
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Error bookcancelByUser : " +e);
        }
		
	}

	public void bookcancelByadmin(int booking_id){
		// TODO Auto-generated method stub
		try {
			close();
//			Transaction transaction = getSession().getTransaction();
//			System.out.println("Transaction by bookcancelby admin"+ transaction);
//            if (transaction != null || transaction.isActive()) {
//               commit();
////               close();
//            }
			begin();
			Query query = getSession().createQuery("Update bookings set accept_status=2 where id= " + booking_id);
			query.executeUpdate();
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Error bookcancelByadmin : " +e);
        }
		
		
	}

	public void bookacceptByadmin(int booking_id) throws InterruptedException {
		// TODO Auto-generated method stub
		try {
			close();
//			Transaction transaction = getSession().getTransaction();
//            if (transaction != null || transaction.isActive()) {
//               commit();
////               close();
//            }
//            Thread.sleep(5000);
			begin();
			Query query = getSession().createQuery("Update bookings set accept_status=1 where id= " + booking_id);
			query.executeUpdate();
			System.out.println("Hello : booking accepted by admin" );
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Hello :" +e);
        }
		
	}

	public long count() {
		long count = 0;
		//SELECT COUNT(*) FROM tablename
		try {
			begin();
			Query query = getSession().createQuery("SELECT COUNT(*) FROM bookings");
			count = (Long)query.uniqueResult();
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Hello :" +e);
        }
		
		return count;
	}

	public long bookingcountByUser(User user) {
		long count = 0;
		try {
			begin();
			int id = user.getId();
			Query query = getSession().createQuery("SELECT COUNT(id) FROM bookings where user_id = " +id);
			count = (Long)query.uniqueResult();
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Hello :" +e);
        }
		
		return count;
	}

	public long bookingcountById(int id) {
		long count = 0;
		try {
			begin();
			
			Query query = getSession().createQuery("select count(id) from bookings where user_id = " +id);
			count = (Long)query.uniqueResult();
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Hello :" +e);
        }
		
		return count;
	}

	public long bookingcountcancelByAdmin() {
		long count = 0;
		try {
			begin();
			
			Query query = getSession().createQuery("select count(id) from bookings where accept_status=2");
			count = (Long)query.uniqueResult();
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Hello :" +e);
        }
		
		return count;
	}

	public long bookingcountcancelByUser() {
		long count = 0;
		try {
			begin();
			
			Query query = getSession().createQuery("select count(id) from bookings where accept_status=3");
			count = (Long)query.uniqueResult();
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Hello :" +e);
        }
		
		return count;
	}

	public long bookingcountcancelByAdminById(int id) {
		long count = 0;
		try {
			begin();
			
			Query query = getSession().createQuery("select count(id) from bookings where user_id= " +id + " and accept_status=2");
			count = (Long)query.uniqueResult();
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Hello :" +e);
        }
		
		return count;
	}

	public long bookingcountcancelByUserById(int id) {
		long count = 0;
		try {
			begin();
			
			Query query = getSession().createQuery("select count(id) from bookings where user_id= " +id + " and accept_status=3");
			count = (Long)query.uniqueResult();
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Hello :" +e);
        }
		
		return count;
	}

	public long allBookingPendingCount() {
		long count = 0;
		try {
			begin();
			
			Query query = getSession().createQuery("select count(id) from bookings where accept_status=0");
			count = (Long)query.uniqueResult();
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Hello :" +e);
        }
		
		return count;
	}

	public long bookingpendingcountById(int id) {
		long count = 0;
		try {
			begin();
			
			Query query = getSession().createQuery("select count(id) from bookings where user_id= " +id + " and accept_status=0");
			count = (Long)query.uniqueResult();
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Hello :" +e);
        }
		
		return count;
	}

	public List<Booking> findHotelById(int id) {
		// TODO Auto-generated method stub Select b from bookings b where hotel_id=
		
		try {
			begin();
			Query query = getSession().createQuery("Select b from bookings b where hotel_id= " +id + " and accept_status=1");
			List<Booking> bookings = query.list();
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Hello :" +e);
        }
		return null;
	}

}
