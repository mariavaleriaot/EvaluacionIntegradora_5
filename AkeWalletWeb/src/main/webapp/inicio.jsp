<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Página de Inicio</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h4 class="text-center">Bienvenido, ${sessionScope.nombreUsuario}</h4>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty saldo}">
                            <p>Saldo actual: $${saldo}</p>
                        </c:if>
                        <c:if test="${not empty sessionScope.message}">
                            <div class="alert alert-success" role="alert">
                                ${sessionScope.message}
                            </div>
                            <%
                                session.removeAttribute("message");
                            %>
                        </c:if>
                        <a href="deposito.jsp" class="btn btn-primary btn-block">Realizar Depósito</a>
                        <a href="retiro.jsp" class="btn btn-primary btn-block">Realizar Retiro</a>
                        
                        <h4 class="mt-4">Historial de Transacciones</h4>
                        <c:if test="${not empty historial}">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>Tipo</th>
                                        <th>Monto</th>
                                        <th>Fecha</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="transaccion" items="${historial}">
                                        <tr>
                                            <td>${transaccion.tipo}</td>
                                            <td>${transaccion.monto}</td>
                                            <td>${transaccion.fecha}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
