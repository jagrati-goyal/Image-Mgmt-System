package com.nagarro.imagemanagement.utility;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nagarro.imagemanagement.model.Image;
import com.nagarro.imagemanagement.model.User;

/**
 * 
 * @author jagratigoyal
 *
 */
public class HibernateUtil {
	private HibernateUtil() {
		// not used
	}

	private static SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").addPackage("com.nagarro.imagemanagement.model")
					.addAnnotatedClass(User.class).addAnnotatedClass(Image.class).buildSessionFactory();
		} catch (HibernateException exception) {
			exception.getStackTrace();
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
