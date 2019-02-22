package com.nagarro.imagemanagement.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.imagemanagement.model.Image;
import com.nagarro.imagemanagement.service.ImageService;

/**
 * Servlet implementation class DisplayImageServlet. This class helps in
 * retrieving a particular image by unique image id from the database and
 * display it in view.
 * 
 * @author jagratigoyal
 */
@WebServlet("/showImage")
public class DisplayImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Logger logger = Logger.getLogger(DisplayImageServlet.class.getName());
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException exception) {
			id = 0;
		}
		ImageService imageService = new ImageService();
		Image image = imageService.getImage(id);
		Blob blob = image.getImage();
		long blobLength;
		try (OutputStream outStream = response.getOutputStream()) {
			blobLength = blob.length();
			byte[] bytes = blob.getBytes(1, (int) blobLength);
			response.setContentType("image/jpeg");
			outStream.write(bytes);
		} catch (SQLException | RuntimeException e) {
			logger.isLoggable(Level.SEVERE);
		}
	}

}
