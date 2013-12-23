<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Car Panel</title>
</head>
    <body>    
    
     <form action = "welcomePage" method="GET" >
     <br> To general panel: <button type="submit">back</button></form>
    
    <form action = "carAdministration" method="POST" >
      <br> All models:  <button name="allCars" value="list"  type="submit">list</button>        
    </form>                              
                
    <form action = "carAdministration" method="POST" >
      <br> Unordered models: <button name="unordered" value=""  type="submit">unordered</button>       
    </form>   
         
    <form action = "carAdministration" method="POST" >
      <br> Input you model of car want to find:<input type = "text" name = "targetCarModel" size="15" maxlength="8"/><input type = "submit" value="find" />          
    </form>    
      
    <form action = "carAdministration" method="POST" >
      <br> Set new position: <button name="createCar" value=""  type="submit">create</button>       
    </form> 
    </body>
</html>