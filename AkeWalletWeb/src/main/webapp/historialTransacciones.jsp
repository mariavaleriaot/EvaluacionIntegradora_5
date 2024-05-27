<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Historial de Transacciones</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Historial de Transacciones</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Fecha</th>
                    <th>Tipo</th>
                    <th>Monto</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="transaccion" items="${transacciones}">
                    <tr>
                        <td><c:out value="${transaccion.id}"/></td>
                        <td><c:out value="${transaccion.fecha}"/></td>
                        <td><c:out value="${transaccion.tipo}"/></td>
                        <td><c:out value="${transaccion.monto}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
