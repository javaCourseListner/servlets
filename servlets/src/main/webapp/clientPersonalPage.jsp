<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Personal page</title>
</head>
	<body>      
       <br> <b>ORDERS:</b>
       <br>
        <c:forEach var="car" items="${client.car}">        
	        <br>Your car model: ${car.model} 
	       <br>Color: ${car.color}  
	        <br>Options:  
	             <c:forEach var="option" items="${car.options}">                        
	                 ${option}                      
	             </c:forEach>
	        <br>
        </c:forEach>
        <br>             
        <form action = "personalPage" method="POST">	   
	        <b>COMPLETE THE FORM:</b>
	        <br> 
	        <br> <b>Input car model:</b> 
	        <br> <input type = "text" name = "model" />
	        <br> <b>Choose color:</b> 
	        <br><label><input type = "radio" name = "color" value="red" />red</label>
	        <br><label><input type = "radio" name = "color" value= "blue" />blue</label>
	        <br><label><input type = "radio" name = "color" value= "green" />green</label>
	        <br><label><input type = "radio" name = "color" value= "black" />black</label>
	        <br><b>Select options:</b>
	        <br><label><input type = "checkbox" name = "options" value= "conditioner"/> conditioner </label>
	        <br><label><input type = "checkbox" name = "options" value= "hydroamplifier"/> hydroamplifier </label>
	        <br><label><input type = "checkbox" name = "options" value= "automatic transmission"/> automatic transmission </label>
	        <br><input type="reset" name="reset" value="Clean">  <input type = "submit" value="Submit"/>       
        </form>
	</body>
</html>