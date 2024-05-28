<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Realizar Retiro</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h4 class="text-center">Realizar Retiro</h4>
                    </div>
                    <div class="card-body">
                        <form action="transaccion" method="post">
                            <input type="hidden" name="action" value="retirar">
                            <div class="form-group">
                                <label for="monto">Monto:</label>
                                <input type="number" id="monto" name="monto" class="form-control" required>
                            </div>
                            <button type="submit" class="btn btn-primary btn-block">Retirar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
