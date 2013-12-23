<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Car Panel</title>
</head>
    <body> 
    <h3> Admin page </h3>  
    <form action = "carAdministration" method="POST" >
       <pre> Input you model of car want to find:<input type = "text" name = "targetCarModel" size="15" maxlength="8"/><input type = "submit" value="find" /></pre>          
    </form>     
    <form action = "carAdministration" method="POST" >
      <pre> Click to see all models:  <button name="allCars" value="list"  type="submit">list</button></pre>        
    </form>                              
    </body>
</html>