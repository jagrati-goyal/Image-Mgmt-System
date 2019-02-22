package com.nagarro.imagemanagement.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.nagarro.imagemanagement.constants.Constants;
import com.nagarro.imagemanagement.model.User;
import com.nagarro.imagemanagement.service.ImageService;
import com.nagarro.imagemanagement.utility.FileUtility;
import com.nagarro.imagemanagement.validator.ImageValidator;

/**
 * Servlet implementation class UploadImageServlet. This class helps to save
 * newly uploaded or edited image data in database.
 * 
 * @author jagratigoyal
 */

@WebServlet("/uploadImage")
@MultipartConfig
public class UploadImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int imageId = 0;
		Logger logger = Logger.getLogger(UploadImageServlet.class.getName());
		Part loadedImage = null;
		loadedImage = request.getPart("image");
		HttpSession httpSession = request.getSession();
		User currentUser = (User) httpSession.getAttribute("currentUser");
		String imagePath = new FileUtility().extractfilePath(loadedImage);
		ImageService imageService = new ImageService();
		// when any new image will upload
		if (request.getParameter("editImageId") == null) {
			try {
				if (!ImageValidator.validateImageType(imagePath)) {
					response.sendRedirect("Image.jsp?invalidImageType=true");
				} else if (!ImageValidator.validateImageSize(loadedImage)) {
					response.sendRedirect("Image.jsp?invalidImageSize=true");
				} else if (!ImageValidator.validateUserTotalImagesSize(loadedImage, currentUser)) {
					response.sendRedirect("Image.jsp?invalidTotalImagesSize=true");
				} else {
					try {
						response.setContentType("text/html");
						String imageName = new FileUtility().getFileName(loadedImage);
						imageService.saveImage(imageId, loadedImage, imageName, currentUser);
						RequestDispatcher dispatcher = request.getRequestDispatcher("Image.jsp");
						dispatcher.forward(request, response);
					} catch (IOException | IllegalStateException | ServletException exception) {
						String message = "Error present either in forwarding the request or in stream..!!";
						logger.log(Level.SEVERE, message, exception);
					}
				}
			} catch (IOException exception) {
				String message = Constants.ERROR_IN_SENDING_REQUEST + " Image.jsp page..!!";
				logger.log(Level.SEVERE, message, exception);
			}
		}
		// when any existing image data will update
		else {
			long newImageSize = loadedImage.getSize();
			try {
				imageId = Integer.parseInt(request.getParameter("editImageId"));
			} catch (NumberFormatException exception) {
				imageId = 0;
			}
			String imageName = request.getParameter("imageName");
			if (newImageSize != 0) {
				try {
					if (!ImageValidator.validateImageType(imagePath)) {
						response.sendRedirect("EditImage.jsp?invalidImageType=true");
						return;
					} else if (!ImageValidator.validateImageSize(loadedImage)) {
						response.sendRedirect("EditImage.jsp?invalidImageSize=true");
						return;
					} else {
						long totalImagesSize = imageService.getTotalImageSize(currentUser);
						long prevImageSize = 0;
						try {
							prevImageSize = Long.parseLong(request.getParameter("prevImageSize"));
						} catch (NumberFormatException exception) {
							prevImageSize = 0;
						}
						long updatedSum = totalImagesSize - prevImageSize + newImageSize;
						if (updatedSum > Constants.VALID_TOTAL_USER_IMAGES_SIZE) {
							response.sendRedirect("EditImage.jsp?invalidUpdatedTotalImagesSize=true");
							return;
						}
						response.setContentType("text/html");
					}
				} catch (IOException exception) {
					String message = Constants.ERROR_IN_SENDING_REQUEST + " EditImage.jsp page..!!";
					logger.log(Level.SEVERE, message, exception);
				}
			}
			imageService.saveImage(imageId, loadedImage, imageName, currentUser);
			httpSession.removeAttribute("selectedImage");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Image.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (IllegalStateException | ServletException exception) {
				String message = Constants.ERROR_IN_SENDING_REQUEST + " Image.jsp page..!!";
				logger.log(Level.SEVERE, message, exception);
			}
		}
	}

}
