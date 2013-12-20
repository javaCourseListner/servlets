<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User</title>
</head>
    <body> 
       <form action = "userAdministration" method="GET" >
       To control panel: <button type="submit">back</button></form>
       
            <br><b>Login:</b> ${targetUser.login}        
            <br><b>Is valid:</b> ${targetUser.valid} 
            <br><b>Has admin rights:</b> ${targetUser.adminRights}                       
            <br>        
 
        
        <br> Get orders:           
        <form action = "userAdministration" method="POST" >
        <button name="userOrders" value="${targetUser.login}" type="submit">orders</button>       
        </form> 
        
       <br>              
        <c:forEach var="order" items="${orders}">        
           <br><b>Car model:</b> ${order.car.model} 
           <b>Color:        </b> ${order.car.color}  
           <b>Options:      </b> ${order.car.options}                      
           <br><b>Price:    </b> ${order.car.price} 
           <br><b>Date:     </b> <fmt:formatDate type="date" value="${order.date}" />                      
           <form action = "userAdministration" method="POST" >
           <input type="hidden" name = "user" value="${targetUser.login}">           
           <button name="dltOrder" value="${order.userOrderId}" type="submit">delete</button>       
           </form> 
           <br>     
        </c:forEach>                   
        <br> Validation :           
        <form action = "userAdministration" method="POST" >
        <button name="invalid" value="${targetUser.login}" type="submit">remove</button>       
        </form> 
        <form action = "userAdministration" method="POST" >
        <button name="valid" value="${targetUser.login}" type="submit">set</button>       
        </form>  
        
        <br>Admin rights:      
        <form action = "userAdministration" method="POST" >
        <button name="rmvAdmRts" value="${targetUser.login}" type="submit">remove</button>       
        </form> 
        <form action = "userAdministration" method="POST" >
        <button name="setAdmRts" value="${targetUser.login}" type="submit">set</button>       
        </form> 
                  
        <br>Delete:
        <form action = "userAdministration" method="POST" >
        <button name="delete" value="${targetUser.login}" type="submit">delete</button>       
        </form> 
</body>
</html>