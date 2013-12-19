<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Authorization page</title>
</head>
	<body>              	
		<h3> You are welcome ${user.login} </h3>
		<form action="clientPersonalPage.jsp" method="GET">
		Your personal page here: <input type="submit" value="get it)"></form>
		<br>
		<form action="carManeger" method="GET">
        To get car list click  here: <input type="submit" value="cars"></form>  
		<br>
        <form action="logout" method="GET">
        To logout click  here: <input type="submit" value="logout"></form>  
	</body>
</html>
