<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Car List</title>
</head>
    <body> 
        <c:forEach var="car" items="${cars}">        
            <br>Car model: ${car.model} 
            <br>Color: ${car.color}  
            <br>Options: ${car.options}                      
        <br>        
         <form action = "carManeger" method="POST" >
         Detail Information: <button name="carToLook" value="${car.carId}"  type="submit">detail</button>       
        </form>  
        </c:forEach>  
</body>
</html>