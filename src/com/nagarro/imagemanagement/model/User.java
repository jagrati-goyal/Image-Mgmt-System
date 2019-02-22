package com.nagarro.imagemanagement.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
	private String username;
	private String password;
	@OneToMany(mappedBy = "user")
	private List<Image> images;

	/**
	 * @return images
	 */
	public List<Image> getImages() {
		return images;
	}

	/**
	 * @param images
	 */
	public void setImages(List<Image> images) {
		this.images = images;
	}

	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
