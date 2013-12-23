<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List</title>
</head>
    <body> 
        <br><form action = "carAdministration" method="GET" >
       Previous page: <button type="submit">back</button></form>
        <br>  
	    <form action = "carAdministration" method="POST" >
	     <button name="unordered" value=""  type="submit">unordered</button>       
	    </form>   
        
        <c:forEach var="car" items="${cars}">        
            <b>Car model:</b> ${car.model} 
            <b>Color:</b> ${car.color}  
            <b>Options:</b> ${car.options}                           
        <br><form action = "carAdministration" method="POST" >
         Detail Information: <button name="targetCar" value="${car.carId}"  type="submit">detail</button>       
        </form>  
        <br>   
        </c:forEach>  
    </body>
</html>