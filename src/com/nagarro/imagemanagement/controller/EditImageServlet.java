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
import com.nagarro.imagemanagement.model.Image;
import com.nagarro.imagemanagement.service.ImageService;

/**
 * Servlet implementation class EditImageServlet. This class helps to fetch
 * requested image data from the database for performing edit operation on it.
 * 
 * @author jagratigoyal
 */
@WebServlet("/editImage")
public class EditImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Logger logger = Logger.getLogger(EditImageServlet.class.getName());
		int imageId = 0;
		try {
			imageId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException exception) {
			imageId = 0;
		}
		HttpSession httpSession = request.getSession();
		ImageService imageService = new ImageService();
		Image image = imageService.getImage(imageId);
		httpSession.setAttribute("selectedImage", image);
		RequestDispatcher dispatcher = request.getRequestDispatcher("EditImage.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (IllegalStateException | ServletException exception) {
			String message = Constants.ERROR_IN_SENDING_REQUEST + " EditImage.jsp page..!!";
			logger.log(Level.SEVERE, message, exception);
		}
	}

}
