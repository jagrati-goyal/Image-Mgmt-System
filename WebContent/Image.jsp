<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page import="com.nagarro.imagemanagement.constants.Constants"%>
<%@page import="java.util.List"%>
<%@page import="com.nagarro.imagemanagement.daoimpl.ImageDaoImpl"%>
<%@page import="com.nagarro.imagemanagement.dao.ImageDao"%>
<%@page import="com.nagarro.imagemanagement.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Image Management App</title>
<style>
form {
	display: inline;
}

table {
	border-collapse: collapse;
}
</style>
<%@ page isELIgnored="false"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<%
		if (session.getAttribute("currentUser") == null) {
			response.sendRedirect("Login.jsp");
		}
	%>
	<table border="1" align="center">
		<tr>
			<th colspan="7">

				<form action="Logout.jsp" method="post">
					<p>
						Welcome User..!! &nbsp;&nbsp;&nbsp;
						<button type="submit">Logout</button>
					</p>
				</form>
			</th>
		</tr>
		<tr>
			<th colspan="5"><br> Image Management Utility</th>
		</tr>
		<tr>
			<td colspan="5"
				style="height: 150px; text-align: left; vertical-align: top;"><p>
				<h4>Please select an image to upload(max size 1MB)</h4>
				</p>

				<form action="uploadImage" method="post"
					enctype="multipart/form-data">
					<input type="file" name="image" value="Browse.."
						accept="image/gif, image/jpeg, image/png" required /><br /> <br />
					<button type="submit">Submit</button>
					<button type="reset">Clear</button>
				</form>
				<div style="color: red">
					<c:if test="${param.invalidImageType == true}">
						<span><%=Constants.FILE_TYPE_MISMATCHED%></span>
					</c:if>
					<c:if test="${param.invalidImageSize == true}">
						<span><%=Constants.IMAGE_SIZE_EXCEEDED%></span>
					</c:if>
					<c:if test="${param.invalidTotalImagesSize == true}">
						<span><%=Constants.MAXIMUM_UPLOADED_IMAGE_LIMIT_EXCEEDED%></span>
					</c:if>

				</div></td>
		</tr>
		<tr>
			<th align="left" style="height: 30px;" colspan="5"><br>
				Uploaded Images</th>
		</tr>

		<tr>
			<td>S.No</td>
			<td>Name</td>
			<td>Size</td>
			<td>Preview</td>
			<td>Action</td>
		</tr>

		<%
			HttpSession httpSession = request.getSession();
			User user = (User) httpSession.getAttribute("currentUser");
			ImageDao imageDao = new ImageDaoImpl();
			List<Image> imageList = imageDao.getImagesByUser(user);
			int index = 0;
			float totalImageSize = 0;
			for (Image image : imageList) {
				index++;
				float imageSizeInMB = (float) image.getSize() / Constants.VALID_IMAGE_SIZE;
				totalImageSize += imageSizeInMB;
		%>
		<tr style="height: 60px;">
			<td><%=index%></td>
			<td><%=image.getName()%></td>
			<td><%=String.format("%.2f", imageSizeInMB * 1024) + "KB"%></td>
			<td><img src="showImage?id=<%=image.getId()%>"
				height="50px" width="50px" /></td>
			<td><form action="deleteImage" method="post">
					<input type="hidden" name="id" value="<%=image.getId()%>">
					<button type="submit" class="fa fa-trash"></button>
				</form>
				<form action="editImage" method="post">
					<input type="hidden" name="id" value="<%=image.getId()%>">
					<button type="submit" class="fa fa-edit"></button>
				</form></td>
		</tr>
		<%
			}
		%>
		<tr>
			<th colspan="5">Total Uploaded Image size : <%=String.format("%.2f", totalImageSize) + "MB"%></th>
		</tr>
	</table>

</body>
</html>