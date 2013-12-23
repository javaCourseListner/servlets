<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Car</title>
</head>
    <body>        
	       <form action = "carManeger" method="GET" >
	       To welcome page: <button type="submit">back</button></form>  
	       
	       <br><b>Car:</b> ${car.model} 
	       <br><b>Color:</b> ${car.color}  
	       <br><b>Options:</b> ${car.options}                      
	       <br><b>Price:</b> ${car.price}
	       <br><b>Description:</b> ${car.description}
           <br>        
           
           <form action = "carManeger" method="POST" >
           Create order: <button name="carToBuy" value="${car.carId}" type="submit">buy</button>       
           </form>               
    </body>
</html>