package com.staycasa.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.staycasa.model.User;

@Component
public class UserDAO extends DAO{
	
	public UserDAO() {
		System.out.println("*** Category DAO");
		//default constructor
	}

	public void save(User user){
		try {
            begin();
            getSession().save(user);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Error save : " +e);
        }
		
	}

	public List<User> findAll() {
		Query query = getSession().createQuery("FROM user");
		List<User> list = query.list();
		return list;
	}

	public User findById(int id) {
		return (User) getSession().get(User.class, id);
	}

	public void updateUser(String email, String firstName, String lastName, String gender, String contactno,
			String address, String role, int id) {
		// TODO Auto-generated method stub
		try {
			System.out.println("miti");
			begin();
			Query query = getSession().createQuery("update user set email ='" + email + "' , first_name ='" + firstName + "', " + "last_name = '" + lastName + "', gender = '" + gender + "', contact_no = '" + contactno + "', address = '" + address + "', role = '" + role + "' where id = '" + id + "' ");
			query.executeUpdate();
			commit();
			close();
		}catch(Exception e) {
			rollback();
			System.out.println("Error updateUser : " +e);
		}
	}

	public void updateUserpro(String email, String firstName, String lastName, String gender, String contactno,
			String address, String role, String password, String cpassword, int id) {
		// TODO Auto-generated method stub
		try {
			System.out.println("miti");
			begin();
			Query query = getSession().createQuery("update user set email ='" + email + "' , first_name ='" + firstName + "', " + "last_name = '" + lastName + "', gender = '" + gender + "', contact_no = '" + contactno + "', address = '" + address + "', role = '" + role + "', password = '" + password + "', confirm_password = '" + cpassword + "' where id = '" + id + "' ");
			query.executeUpdate();
			commit();
			close();
		}catch(Exception e) {
			rollback();
			System.out.println("Error updateUserpro : " +e);
		}
		
	}

	public long countByRole(String string) {
		// TODO Auto-generated method stub
		long count = 0;
		//SELECT COUNT(*) FROM tablename
		try {
			begin();
			Query query = getSession().createQuery("SELECT COUNT(*) FROM user");
			//count = (long) query.uniqueResult();
			count = (Long)query.uniqueResult();
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Error countByRole : " +e);
        }
		
		return count;
	}

	public User findByEmail(String email) {
		Session session = getSession();
        Criteria crit = session.createCriteria(User.class);
        crit.add(Restrictions.eq("email", email));
//        System.out.println(crit.uniqueResult());
        return (User) crit.uniqueResult();
	}

	public User findbyRole(String role) {
		// TODO Auto-generated method stub
		try {
			begin();
			Query query = getSession().createQuery(" SELECT * FROM user where role = " + role);
			//count = (long) query.uniqueResult();
			User user = (User)query.uniqueResult();
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Error findbyRole : " +e);
        }
		return null;
	}

	public User findByResetPasswordToken(String token) throws Exception {
		// TODO Auto-generated method stub
		Session session = getSession();
        Criteria crit = session.createCriteria(User.class);
        crit.add(Restrictions.eq("resetPasswordToken", token));
        return (User) crit.uniqueResult();
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		try {
			begin();
			Query query = getSession().createQuery("delete from user where id = '" + id + "' ");
			query.executeUpdate();
			commit();
			close();
        } catch (HibernateException e) {
            rollback();
            System.out.println("Error deleteById : " +e);
        }
		
	}

}
