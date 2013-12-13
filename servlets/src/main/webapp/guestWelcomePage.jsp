<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
        <h3> Authorization page </h3>
        <form action = "welcomePage" method="POST" >
            <pre>
            <br> <label> Input your login:    <input type = "text" name = "login" size="15" maxlength="8"/></label>
            <br> <label> Input your password: <input type = "password" name = "password" size="15" maxlength="8"/></label>
            <br>
            <br> <input type="reset" name="reset" value="Clean">  <input type = "submit" value="Submit" />
            </pre>
        </form>  
</body>
</html>