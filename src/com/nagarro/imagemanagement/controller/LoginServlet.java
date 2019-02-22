package com.nagarro.imagemanagement.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.nagarro.imagemanagement.constants.Constants;
import com.nagarro.imagemanagement.model.User;
import com.nagarro.imagemanagement.service.LoginService;

/**
 * Servlet implementation class LoginServlet. This class helps to login the user
 * and authenticates the user.
 * 
 * @author jagratigoyal
 */
@WebServlet("/loginPage")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		Logger logger = Logger.getLogger(LoginServlet.class.getName());
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginService service = new LoginService();
		User user = service.authenticate(username, password);
		if (user != null) {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("currentUser", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Image.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (IllegalStateException | ServletException exception) {
				String message = Constants.ERROR_IN_SENDING_REQUEST + " Image.jsp page..!!";
				logger.log(Level.SEVERE, message, exception);
			}
		} else {
			request.setAttribute("errorMessage", Constants.USER_NOT_FOUND);
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			try {
				rd.forward(request, response);
			} catch (IllegalStateException | ServletException exception) {
				String message = Constants.ERROR_IN_SENDING_REQUEST + " Login.jsp page..!!";
				logger.log(Level.SEVERE, message, exception);
			}
		}
	}
}
