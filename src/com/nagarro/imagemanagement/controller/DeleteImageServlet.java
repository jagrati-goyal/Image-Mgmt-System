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

import com.nagarro.imagemanagement.constants.Constants;
import com.nagarro.imagemanagement.service.ImageService;

/**
 * Servlet implementation class DeleteImageServlet. This class deletes the
 * selected image with the help of unique image id stored in the database
 * 
 * @author jagratigoyal
 */

@WebServlet("/deleteImage")
public class DeleteImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Logger logger = Logger.getLogger(DeleteImageServlet.class.getName());
		int imageId = 0;
		try {
			imageId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException exception) {
			imageId = 0;
		}
		ImageService imageService = new ImageService();
		imageService.deleteImageById(imageId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Image.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (IllegalStateException | ServletException exception) {
			String message = Constants.ERROR_IN_SENDING_REQUEST + " Image.jsp page..!!";
			logger.log(Level.SEVERE, message, exception);
		}
	}

}
