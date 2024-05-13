<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
   String name=(String)session.getAttribute("name");
%>
<h1> Welcome <%= name %></h1>
<a href="home.jsp">home</a>
  <a href="about.jsp"> about</a>
  <a href="index.jsp"> Login</a>  

</body>
</html>