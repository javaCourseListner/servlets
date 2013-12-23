<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Personal page</title>
</head>
	<body>            
       <form action = "welcomePage" method="GET" >
       To previous page:<button type="submit">back</button></form>                      
       <br> <b>YOUR ORDERS:</b>
       <br>              
        
        <c:forEach var="order" items="${orderList}">        
		   <br><b>Car model: </b> ${order.car.model} 
		   <b>Color:         </b> ${order.car.color}  
		   <b>Options:       </b> ${order.car.options}                      
	       <br><b>Price:     </b> ${order.car.price} 
	       <br><b>Date:      </b> <fmt:formatDate type="date" value="${order.date}" />	         
	       <br>     
        </c:forEach>      
        
        <br>          
        <form action = "personalPage" method="POST" >
        <b> To count amount:</b> <button name="showOrdersSum" type="submit">count</button></form>                
        ${sum}                 
	    	   
	    <br>
	    <form action = "personalPage" method="POST" >
	    <b> Group by month:</b> <button name="showMonthSum" type="submit">count</button></form>                
       
        <c:forEach var="entry" items="${monthSum}">
			<c:out value="${entry.key}"/>:
			<c:out value="${entry.value}"/>
		</c:forEach>	
	</body>
</html>