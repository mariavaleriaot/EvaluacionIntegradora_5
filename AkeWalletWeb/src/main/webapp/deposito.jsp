<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Realizar Depósito</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h4 class="text-center">Realizar Depósito</h4>
                    </div>
                    <div class="card-body">
                        <form action="transaccion" method="post">
                            <input type="hidden" name="action" value="depositar">
                            <div class="form-group">
                                <label for="monto">Monto:</label>
                                <input type="number" id="monto" name="monto" class="form-control" required>
                            </div>
                            <button type="submit" class="btn btn-primary btn-block">Depositar</button>
                        </form>
                        <a href="usuario?action=consultarSaldo" class="btn btn-secondary btn-block mt-3">Volver a Inicio</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
