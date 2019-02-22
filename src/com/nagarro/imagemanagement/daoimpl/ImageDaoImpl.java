package com.nagarro.imagemanagement.daoimpl;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.imagemanagement.dao.ImageDao;
import com.nagarro.imagemanagement.model.Image;
import com.nagarro.imagemanagement.model.User;
import com.nagarro.imagemanagement.utility.HibernateUtil;

/**
 * This class provides definition for all the methods declared in ImageDao
 * interface.
 * 
 * @author jagratigoyal
 */
public class ImageDaoImpl implements ImageDao {
	Transaction transaction = null;
	Query<Image> query = null;

	@Override
	public void saveOrUpdateImage(int imageId, Image image) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		try {
			if (imageId == 0) {
				session.saveOrUpdate(image);
			} else {
				Image retrievedImage = session.load(Image.class, imageId);
				retrievedImage.setName(image.getName());
				if (image.getSize() != 0) {
					retrievedImage.setSize(image.getSize());
					retrievedImage.setImage(image.getImage());
					retrievedImage.setPath(image.getPath());
				}
				session.saveOrUpdate(retrievedImage);
			}
			transaction.commit();
		} catch (HibernateException exception) {
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteImage(Image image) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		try {
			session.delete(image);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public List<Image> getImagesByUser(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		List<Image> imageList = null;
		try {
			query = session.createQuery("from Image i where i.user = :user");
			query.setParameter("user", user);
			imageList = query.list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return imageList;
	}

	@Override
	public long getTotalSizeOfUserImages(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		long totalSizeOfImages = 0;
		List<Image> userImages = null;
		transaction = session.beginTransaction();
		try {
			query = session.createQuery("from Image i where i.user = :user");
			query.setParameter("user", user);
			userImages = query.list();
			for (Image userImage : userImages) {
				totalSizeOfImages += userImage.getSize();
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return totalSizeOfImages;
	}

	@Override
	public Image getImageById(int imageId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		Image image = null;
		try {
			image = session.get(Image.class, imageId);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return image;
	}

}
