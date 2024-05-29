<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenido a Alke Wallet - Iniciar sesión</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        footer {
            background-color: #e3f2fd;
            padding: 10px;
            position: fixed;
            left: 0;
            bottom: 0;
            width: 100%;
            text-align: center;
        }
        .alke-wallet-header {
            font-size: 36px;
            color: #343a40;
            margin-bottom: 10px;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .alke-wallet-tagline {
            font-size: 14px;
            color: #6c757d;
            margin-bottom: 20px;
        }
        .card {
            border-radius: 15px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); 
            padding: 20px; 
        }
        .ingresa-text {
            font-family: Arial, sans-serif; 
            font-size: 18px; 
            color: #007bff; 
            margin-bottom: 10px; 
        }
        .container {
            margin-top: 50px; 
            margin-bottom: 50px; 
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h2 class="text-center alke-wallet-header">¡Bienvenido a <span style="color: #007bff;">Alke Wallet</span>!</h2>
                        <p class="text-center alke-wallet-tagline">Descubre un nuevo mundo de finanzas digitales.</p>
                        <hr> <!-- Línea horizontal -->
                        <h5 class="text-center ingresa-text">Ingresa a tu billetera digital</h5>
                    </div>
                    <div class="card-body">
                        <%-- Mostrar mensaje de error si existe --%>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger" role="alert">
                                <c:out value="${error}" />
                            </div>
                        </c:if>
                        <form action="login" method="post">
                            <div class="form-group">
                                <label for="username">Nombre de Usuario:</label>
                                <input type="text" id="username" name="username" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="password">Contraseña:</label>
                                <input type="password" id="password" name="password" class="form-control" required>
                            </div>
                            <button type="submit" class="btn btn-primary btn-block">Iniciar sesión</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer>
        <p>© 2024 Alke Wallet. Todos los derechos reservados.</p>
    </footer>
</body>
</html>
