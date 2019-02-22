package com.nagarro.imagemanagement.validator;

import javax.servlet.http.Part;

import com.nagarro.imagemanagement.constants.Constants;
import com.nagarro.imagemanagement.dao.ImageDao;
import com.nagarro.imagemanagement.daoimpl.ImageDaoImpl;
import com.nagarro.imagemanagement.model.User;

/**
 * 
 * @author jagratigoyal
 *
 */
public class ImageValidator {

	private ImageValidator() {
		// not used
	}

	public static boolean validateImageSize(Part image) {
		boolean flag = true;
		long imageSize = 0;
		imageSize = image.getSize();
		if (imageSize > Constants.VALID_IMAGE_SIZE) {
			flag = false;
			return flag;
		}
		return flag;
	}

	public static boolean validateImageType(String imagePath) {
		boolean flag = false;
		String extension = imagePath.substring(imagePath.lastIndexOf('.') + 1).toLowerCase();
		if (extension.equals("gif") || extension.equals("png") || extension.equals("bmp") || extension.equals("jpeg")
				|| extension.equals("jpg")) {
			flag = true;
			return flag;
		}
		return flag;
	}

	public static boolean validateUserTotalImagesSize(Part image, User user) {
		boolean flag = true;
		ImageDao imageDAO = new ImageDaoImpl();
		long totalUserImagesSize = imageDAO.getTotalSizeOfUserImages(user) + image.getSize();
		if (totalUserImagesSize > Constants.VALID_TOTAL_USER_IMAGES_SIZE) {
			flag = false;
			return flag;
		}
		return flag;
	}
}
