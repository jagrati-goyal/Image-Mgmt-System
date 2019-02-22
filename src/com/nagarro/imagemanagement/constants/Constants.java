package com.nagarro.imagemanagement.constants;

/**
 * This class contains list of constants used throughout the assignment
 * 
 * @author jagratigoyal
 */

public final class Constants {
	private Constants() {
		// not called
	}

	public static final long VALID_IMAGE_SIZE = 1024L * 1024;
	public static final long VALID_TOTAL_USER_IMAGES_SIZE = VALID_IMAGE_SIZE * 10;
	public static final String FILE_TYPE_MISMATCHED = "Try Again, Only images can be uploaded here";
	public static final String IMAGE_SIZE_EXCEEDED = "Uploaded image should be of less than 1 mb";
	public static final String MAXIMUM_UPLOADED_IMAGE_LIMIT_EXCEEDED = "Sum of total Uploaded images should be less than 10 mb";
	public static final String ERROR_IN_SENDING_REQUEST = "Error in request sending/forwarding to ";
	public static final String LOGIN_FIELD_NOT_DEFINED = "Please fill both fields correctly";
	public static final String USER_NOT_FOUND = "User not found with entered credentials.!";
}
