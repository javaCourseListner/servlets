<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Car</title>
</head>
	 <body>
       
       <form action = "carAdministration" method="GET" >
       Car panel: <button type="submit">back</button></form>        	                    	        
	              
       <br><b>Car:</b> ${car.model} 
       <br><b>Color:</b> ${car.color}  
       <br><b>Options:</b> ${car.options}                      
       <br><b>Price:</b> ${car.price}
       <br><b>Description:</b> ${car.description}
        
	   <br><br> 	   	   	          	   
	   
	   <form action = "carAdministration" method="POST" >
            <br>Update price: 
            <input type = "submit" value="update" />  
            <br><br><input type = "text" name = "updatePrice" size="15" />
            <input type="hidden" name = "car" value="${car.carId}">           
       </form>  
       
       <br><br>
                
       <form action="carAdministration" method="POST">
	        <br>Update description:
	        <input type="submit" value="update">
	        <br><br><textarea rows="10" cols="45" name="updateDescription"></textarea>	       
            <input type="hidden" name = "car" value="${car.carId}">  
       </form>
     
       <br><br>
       
       <form action = "carAdministration" method="POST" >
            Count orders on this car: <button name="countCarOrders" value="${car.carId}" type="submit">click</button>       
       </form> 
       ${countCarOrders}
 
       <br><br>
	   
	   <form action = "carAdministration" method="POST" >
            Delete: <button name="deleteCar" value="${car.carId}" type="submit">click</button>       
       </form>  
       *only if there are not orders on this car.	    	     
	
	</body>
 </html>