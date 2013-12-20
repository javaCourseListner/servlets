<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
          
        <br><form action = "userAdministration" method="POST" >
         Delete: <button name="delete" value="${targetUser.login}" type="submit">click</button>       
        </form>  
        
        <br><form action = "userAdministration" method="POST" >
         Validate : <button name="validate" value="${targetUser.login}" type="submit">click</button>       
        </form>  

        <br><form action = "userAdministration" method="POST" >
         Give admin rights: <button name="adminRights" value="${targetUser.login}" type="submit">click</button>       
        </form> 

</body>
</html>