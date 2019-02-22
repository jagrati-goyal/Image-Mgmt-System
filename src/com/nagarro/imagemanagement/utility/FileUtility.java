package com.nagarro.imagemanagement.utility;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Part;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.nagarro.imagemanagement.controller.UploadImageServlet;
import com.nagarro.imagemanagement.model.Image;
import com.nagarro.imagemanagement.model.User;

/**
 * 
 * @author jagratigoyal
 *
 */
public class FileUtility {
	public String extractfilePath(Part file) {

		String cd = file.getHeader("content-disposition");
		String[] items = cd.split(";");
		for (String string : items) {
			if (string.trim().startsWith("filename")) {
				return string.substring(string.indexOf('=') + 2, string.length() - 1);
			}
		}
		return "";
	}

	public String getFileName(Part image) {
		String imageName = null;
		String fullPathName = extractfilePath(image);
		int lastIndex = fullPathName.lastIndexOf('\\');
		imageName = fullPathName.substring(lastIndex + 1);
		lastIndex = imageName.lastIndexOf('.');
		imageName = imageName.substring(0, lastIndex);
		return imageName;
	}

	public Image readImage(Part loadedImage, User currentUser) {
		Logger logger = Logger.getLogger(UploadImageServlet.class.getName());
		Image image = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession();
				InputStream inputStream = loadedImage.getInputStream()) {
			image = new Image();
			String imagePath = extractfilePath(loadedImage);
			byte[] userImage = new byte[(int) loadedImage.getSize()];
			inputStream.read(userImage);
			image.setImage((Blob) Hibernate.getLobCreator(session).createBlob(userImage));
			image.setName(new FileUtility().getFileName(loadedImage));
			image.setPath(imagePath);
			image.setSize(loadedImage.getSize());
		//	image.setUser(currentUser);
		} catch (IOException | IllegalStateException exception) {
			String message = "Error present either in forwarding the request or in stream..!!";
			logger.log(Level.SEVERE, message, exception);
		}
		return image;
	}
}
