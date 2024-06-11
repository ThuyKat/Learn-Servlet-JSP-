<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="user" class=dto.User scope="request">
		<jsp:setProperty property="userName" name="user" param="userName" />
		<!-- instead of giving the value, we look it up in the parameter of request
 -->
	</jsp:useBean>
	Hello
	<jsp:getProperty property="userName" name="user" />
</body>
</html>