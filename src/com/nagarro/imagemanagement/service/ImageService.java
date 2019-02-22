package com.nagarro.imagemanagement.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import javax.servlet.http.Part;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.nagarro.imagemanagement.dao.ImageDao;
import com.nagarro.imagemanagement.daoimpl.ImageDaoImpl;
import com.nagarro.imagemanagement.model.Image;
import com.nagarro.imagemanagement.model.User;
import com.nagarro.imagemanagement.utility.FileUtility;
import com.nagarro.imagemanagement.utility.HibernateUtil;

public class ImageService {
	private ImageDao imageDao = new ImageDaoImpl();
	private Image image = null;

	public byte[] readImage(Part loadedImage) {
		byte[] userImage = new byte[(int) loadedImage.getSize()];
		try (Session session = HibernateUtil.getSessionFactory().openSession();
				InputStream inputStream = loadedImage.getInputStream()) {
			inputStream.read(userImage);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return userImage;
	}

	public void saveImage(int imageId, Part loadedImage, String imageName, User currentUser) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			image = new Image();
			byte[] userImage = readImage(loadedImage);
			long newImageSize = loadedImage.getSize();
			if (newImageSize != 0) {
				image.setImage((Blob) Hibernate.getLobCreator(session).createBlob(userImage));
				image.setPath(new FileUtility().extractfilePath(loadedImage));
				image.setSize(newImageSize);
			}
			image.setName(imageName);
			image.setUser(currentUser);
			imageDao.saveOrUpdateImage(imageId, image);
		} catch (HibernateException exception) {
			exception.printStackTrace();
		}
	}

	public long getTotalImageSize(User currentUser) {
		return imageDao.getTotalSizeOfUserImages(currentUser);
	}

	public void deleteImageById(int id) {
		image = getImage(id);
		imageDao.deleteImage(image);
	}

	public Image getImage(int id) {
		return imageDao.getImageById(id);
	}

}
