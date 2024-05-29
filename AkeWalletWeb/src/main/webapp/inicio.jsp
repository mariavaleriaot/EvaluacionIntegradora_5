<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Página de Inicio</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            margin-bottom: 100px; 
            background-color: #f8f9fa;
        }
        footer {
            background-color: #e3f2fd;
            padding: 10px;
            position: fixed;
            left: 0;
            bottom: 0;
            width: 100%;
            text-align: center;
            z-index: 1000; 
        }
        .container {
            margin-top: 50px;
            margin-bottom: 20px; 
        }
        .welcome-message {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            font-size: 28px;
            color: #343a40;
            margin-bottom: 10px;
            text-align: center;
        }
        .saldo {
            font-size: 24px;
            color: #fff;
            background-color: #28a745;
            padding: 10px;
            text-align: center;
            margin-bottom: 20px;
            border-radius: 10px; 
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Alke Wallet</a>
        <div class="collapse navbar-collapse justify-content-between">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="#">Bienvenido, ${sessionScope.nombreUsuario}</a>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link btn btn-danger text-white" href="/AkeWalletWeb/logout.jsp">Cerrar Sesión</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h4 class="welcome-message">Bienvenido, ${sessionScope.nombreUsuario}</h4>
                        <p class="text-center alke-wallet-tagline">¿Qué quieres hacer hoy?</p>
                    </div>
                    <div class="card-body">
                        <div class="saldo">
                            <c:if test="${not empty sessionScope.saldo}">
                                Saldo Actual: $${sessionScope.saldo}
                            </c:if>
                        </div>
                        <c:if test="${not empty sessionScope.message}">
                            <div class="alert alert-success" role="alert">
                                ${sessionScope.message}
                            </div>
                            <%
                                session.removeAttribute("message");
                            %>
                        </c:if>
                        <c:if test="${not empty sessionScope.error}">
                            <div class="alert alert-danger" role="alert">
                                ${sessionScope.error}
                            </div>
                            <%
                                session.removeAttribute("error");
                            %>
                        </c:if>
                        <a href="deposito.jsp" class="btn btn-primary btn-block">Realizar Depósito</a>
                        <a href="retiro.jsp" class="btn btn-primary btn-block">Realizar Retiro</a>
                        <button onclick="toggleHistorial()" class="btn btn-secondary btn-block">Ver/Ocultar Historial de Transacciones</button>

                        <c:if test="${sessionScope.mostrarHistorial}">
                            <h4 class="mt-4">Historial de Transacciones</h4>
                            <c:if test="${not empty sessionScope.historial}">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>Tipo</th>
                                            <th>Monto</th>
                                            <th>Fecha</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="transaccion" items="${sessionScope.historial}">
                                            <tr>
                                                <td>${transaccion.tipo}</td>
                                                <td>${transaccion.monto}</td>
                                                <td>${transaccion.fecha}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer>
        <p>© 2024 Alke Wallet. Todos los derechos reservados.</p>
    </footer>
    <script>
        function toggleHistorial() {
            window.location.href = 'transaccion?action=historial';
        }
    </script>
</body>
</html>
