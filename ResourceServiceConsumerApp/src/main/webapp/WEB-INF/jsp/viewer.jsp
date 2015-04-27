<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table border=1>
<tr>
<th>FileName</th>
<th>Uri</th>
</tr>

<c:forEach items="${list}" var="resources">


<tr>
<td>${resources.fileName }</td>
<td><a href="/ResourceConsumerServiceApp/download?uri=${resources.uri}&fileType=${resources.fileType}">${resources.uri}</a></td>
</tr>



</c:forEach>
</table>
</body>
</html>