<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Car List</title>
</head>
    <body> 
       <form action = "userAdministration" method="GET" >
       Previous page: <button type="submit">back</button></form>
         
	    <form action = "userAdministration" method="POST" >
	     <button name="targetUserList" value="list"  type="submit">all</button>        
	    </form>   
	   
	    <form action = "userAdministration" method="POST" >
	     <button name="invldList" value="list"  type="submit">invalid</button>       
	    </form>
	   
	    <form action = "userAdministration" method="POST" >
	    <button name="admnList" value="list"  type="submit">admin</button>        
	    </form>    
           
      <c:forEach var="targetUser" items="${targetUserList}"> 
        <br><b>Login:</b> ${targetUser.login}        
        <form action = "userAdministration" method="POST" >
         Get him: <button name="targetUser" value="${targetUser.login}" type="submit">click</button>       
        <br>
        </form>         
      </c:forEach>                                        
</body>
</html>