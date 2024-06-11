<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration Page</title>
</head>
<body>
<form action="login" method="post">
<!-- if we donot specify here it will default be get method -->
	<br>User Id : <input type="text" name="userId"/>
	<br>User Name: <input type="text" name="userName"/>
	<br>Address Line 1: <input type="text" name="address1"/>
	<br>Address Line 2: <input type="text" name="address2"/>
	<br>City: <input type="text" name="city"/>
	<br>State: <input type="text" name="state"/>
	<br>Pincode: <input type="text" name="pincode"/>
	<br><input type="submit"/>
</form>
</body>
</html>