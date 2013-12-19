<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Car</title>
</head>
	 <body>
	 <form action = "adminPage" method="GET" >
	       To previous page: <input type = "submit" value="back" />    
	       </form>             	       
	       <br>              	        
	       
	       <c:forEach var="targetCar" items="${targetCarList}">        
	            <br><b>Car Id:</b> ${targetCar.carId} 
	            <br><b>Car model:</b> ${targetCar.model} 
	            <br><b>Color:</b> ${targetCar.color}  
	            <br><b>Options:</b> ${targetCar.options}                      
	            <br><b>Options:</b> ${targetCar.description}
	        <br>        
	        </c:forEach>               
	        <br>                     
	        	        
	    <br><form action = "adminPage" method="POST" >
         Delete: <button name="deleteCar" value="${targetCar.caId}" type="submit">click</button>       
        </form>  
        
        <br><form action = "adminPage" method="POST" >
         Set price : <button name="setCarPrice" value="${targetCar.caId}" type="submit">click</button>       
        </form>        
	        
		<form action="adminPage" method="POST">
	    <br>Update description:
	    <p><textarea rows="10" cols="45" name="updateDescrp"></textarea></p>
	    <p><input type="submit" value="click"></p>
	    </form>
	        
	        
	        
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
	            <br><label><input type = "checkbox" name = "conditioner" value= "true"/> conditioner </label>
	            <br><label><input type = "checkbox" name = "hydroamplifier" value= "true"/> hydroamplifier </label>
	            <br><label><input type = "checkbox" name = "automaticTransmission" value= "true"/> automatic transmission </label>
	            <br><input type="reset" name="reset" value="Clean">  <input type = "submit" value="Submit"/>       
	        </form>
	</body>
 </html>