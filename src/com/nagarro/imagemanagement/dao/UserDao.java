package com.nagarro.imagemanagement.dao;

import com.nagarro.imagemanagement.model.User;

/**
 * This interface declares all required operations on user.
 * 
 * @author jagratigoyal
 */
public interface UserDao {
	public User getUser(String username, String password);
}
