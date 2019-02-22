package com.nagarro.imagemanagement.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nagarro.imagemanagement.dao.UserDao;
import com.nagarro.imagemanagement.model.User;
import com.nagarro.imagemanagement.utility.HibernateUtil;

/**
 * This class provides definition for all the methods declared in UserDao
 * interface.
 * 
 * @author jagratigoyal
 *
 */
public class UserDaoImpl implements UserDao {
	public User getUser(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();	
		User user = new User();
		try {
			@SuppressWarnings("unchecked")
			Query<User> query = session
					.createQuery("from User u where u.username = :username and u.password = :password");
			query.setParameter("username", username);
			query.setParameter("password", password);
			user = query.uniqueResult();
			transaction.commit();
		} catch(Exception e) {
			transaction.rollback();
		}finally{
			session.close();
		}
		return user;
	}

}
