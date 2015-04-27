<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome to Login Page Please Enter Your Credentials</h1>
<form action="<c:url value='j_spring_security_check' />" method="post">
Enter UserName:<input type="text" name="j_username"/><br>
Enter password:&nbsp;&nbsp;<input type="password" name="j_password"/><br/>
<input type="submit"/>
<input type="hidden" name='${_csrf.parameterName}' value='${_csrf.token}' />
</form>
</body>
</html>