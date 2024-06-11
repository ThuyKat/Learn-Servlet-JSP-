<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dto.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success</title>
</head>
<body>
<h3> Login Successful</h3>
<!-- print username, greeting, we need to have getUserName in business service -->
<%-- <%
/* User user = (User) session.getAttribute("user"); */
User user = (User)request.getAttribute("user");
%> --%>

<jsp:useBean id="user" class=dto.User scope="request">
<jsp:setProperty property="userName" name="user" value="newUser">
<!-- set the value of the property of "user" object as "newUser", dont care the value passed by user object
 -->
</jsp:useBean>

Hello <%=user.getUserName() %>

Hello <jsp:getProperty property="userName" name="user">
</body>
</html> 