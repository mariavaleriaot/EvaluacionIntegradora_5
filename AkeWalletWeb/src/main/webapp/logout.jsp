<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout</title>
</head>
<body>
<%
    // Invalida la sesión actual
    session.invalidate();
    // Redirige a la página de inicio de sesión
    response.sendRedirect("/login.jsp");
%>
</body>
</html>
