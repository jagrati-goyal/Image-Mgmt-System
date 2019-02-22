package com.nagarro.imagemanagement.dao;

import java.util.List;

import com.nagarro.imagemanagement.model.Image;
import com.nagarro.imagemanagement.model.User;

/**
 * This interface declares all required operations on image.
 * 
 * @author jagratigoyal
 */

public interface ImageDao {
	public void saveOrUpdateImage(int imageId, Image image);

//	public void saveImage(Image image);

	public void deleteImage(Image image);

	public List<Image> getImagesByUser(User user);

	public long getTotalSizeOfUserImages(User user);

	public Image getImageById(int imageId);
	
}
