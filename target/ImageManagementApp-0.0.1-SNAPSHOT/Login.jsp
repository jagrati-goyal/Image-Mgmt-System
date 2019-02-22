<%-- <%@page import="com.nagarro.itemmanagement.constants.Constants"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@ page isELIgnored="false"%>
<title>Login Page</title>
<!-- <link href="css/style.css" rel="stylesheet" type="text/css" /> -->
</head>
<body>
	<form action="loginPage" method="post">
		<div style="padding: 100px 0 0 250px;">
			<div id="login-box">
				<h2>Login Page</h2>
				Please provide your credential to use this website <br>
					<div id="login-box-name" style="margin-top: 20px;">User Name:</div>
					<div id="login-box-field" style="margin-top: 10px;">
						<input type="text" name="username" class="form-login" size="30"
							maxlength="50" required />
					</div> <br />
					<div id="login-box-name">Password:</div>
					<div id="login-box-field" style="margin-top: 10px;">
						<input type="password" name="password" class="form-login"
							size="30" maxlength="48" required />
					</div> <br /> <br />

					<button type="submit">Login</button> <br />
					<div style="color: red; font-weight: bold">
						<c:if test="${errorMessage != null }">
							${errorMessage}
						</c:if>
						<%-- <c:if test="${param.fieldNotDefined == true}">
							<span>${Constants.LOGIN_FIELD_NOT_DEFINED} </span>
						</c:if> --%>
					</div>
			</div>
		</div>
	</form>
</body>
</html>




