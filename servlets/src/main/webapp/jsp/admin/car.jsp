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
       Previous page: <button type="submit">back</button></form>        	                    	        
	              
       <br><b>Car:</b> ${car.model} 
       <br><b>Color:</b> ${car.color}  
       <br><b>Options:</b> ${car.options}                      
       <br><b>Price:</b> ${car.price}
       <br><b>Description:</b> ${car.description}
        
	   <br><br> 	   	   	          	   
	   
	   <form action = "carAdministration" method="POST" >
            <br>Update price: 
            <input type = "submit" value="update" />  
            <br><br><input type = "text" name = "newPrice" size="15" />
            <input type="hidden" name = "car" value="${car.carId}">           
       </form>  
       
       <br><br>
                
       <form action="carAdministration" method="POST">
	        <br>Update description:
	        <input type="submit" value="update">
	        <br><br><textarea rows="10" cols="45" name="newDescription"></textarea>	       
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
       *will work only if there are not orders on this car.
	    
	     
<!-- 	     
	     
	    <c:forEach var="order" items="${orderList}">        
           <br><b>Car model:</b> ${order.car.model} 
           <b>Color:        </b> ${order.car.color}  
           <b>Options:      </b> ${order.car.options}                      
           <br><b>Price:    </b> ${order.car.price} 
           <br><b>Date:      </b> <fmt:formatDate type="date" value="${order.date}" />           
           <br>     
        </c:forEach> 
	     
	     
	     
	     
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
-->    
	</body>
 </html>