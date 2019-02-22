<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page import="com.nagarro.imagemanagement.constants.Constants"%>
<%@page import="com.nagarro.imagemanagement.model.Image"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Image Management App</title>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<%
		Image image = (Image) session.getAttribute("selectedImage");
	%>

	<div style="padding: 100px 0 0 550px;">
		<h2>Edit Selected Image</h2>
	</div>
	<div style="padding: 0px 0 0 600px">
		<form action="Logout.jsp" method="post">
			<p>
				Welcome User..!! &nbsp;&nbsp;&nbsp;
				<button type="submit">Logout</button>
			</p>
		</form>
	</div>
	<div style="padding: 5px 0 0 450px;">
		<form action="uploadImage" method="post" enctype="multipart/form-data">
			<br>
			<table border="0" cellspacing="5" cellpadding="5"
				style="margin-left: 40px">
				<tr>
					<td>Name :</td>
					<td><input type="text" size="35" name="imageName"
						value="<%=image.getName()%>" required></td>
				</tr>
				<tr>
					<td>Source :</td>
					<td><input type="file" name="image"></td>
				</tr>
				<tr>
					<td><input type="hidden" name="editImageId"
						value=<%=image.getId()%>></td>
					<td><input type="hidden" name="prevImageSize"
						value="<%=image.getSize()%>"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Edit"></td>
					<td><input type="reset" value="Clear"></td>
				</tr>
			</table>
			<div style="color: red">
				<c:if test="${param.invalidImageSize == true}">
					<span><%=Constants.IMAGE_SIZE_EXCEEDED%></span>
				</c:if>
				<c:if test="${param.invalidTotalImagesSize == true}">
					<span><%=Constants.MAXIMUM_UPLOADED_IMAGE_LIMIT_EXCEEDED%></span>
				</c:if>
				<c:if test="${param.invalidImageType == true}">
					<span><%=Constants.FILE_TYPE_MISMATCHED%></span>
				</c:if>
			</div>
		</form>
	</div>
	<div style="padding: 0px 0 0 720px">
		<form action="Image.jsp">
			<button type="submit">Back to Images</button>
		</form>
	</div>

</body>
</html>