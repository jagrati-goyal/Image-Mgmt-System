package com.nagarro.imagemanagement.service;

import com.nagarro.imagemanagement.dao.UserDao;
import com.nagarro.imagemanagement.daoimpl.UserDaoImpl;
import com.nagarro.imagemanagement.model.User;

public class LoginService {
	public User authenticate(String username, String password) {
		UserDao daoService = new UserDaoImpl();
		User user = daoService.getUser(username, password);
		System.out.println(user);
		return user;
	}
}
