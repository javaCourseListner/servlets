<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Panel</title>
</head>
    <body> 
     <form action = "welcomePage" method="GET" >
     <br> To general panel: <button type="submit">back</button></form>
   
    <form action = "userAdministration" method="POST" >
     <br> Input login of user you want to find:<input type = "submit" value="find" /> 
     <input type = "text" name = "targetUser" size="15" maxlength="8"/>
    </form>
    
    <form action = "userAdministration" method="POST" >
     <br>  Click to see all users:  <button name="targetUserList" value="list"  type="submit">list</button>        
    </form>                                  
    </body>
</html>