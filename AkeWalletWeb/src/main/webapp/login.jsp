<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Iniciar sesión - AlkeWallet</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-header">
						<h4 class="text-center">Iniciar sesión</h4>
					</div>
					<div class="card-body">
						<%-- Mostrar mensaje de error si existe --%>
						<%
						String error = (String) request.getAttribute("error");
						%>
						<%
						if (error != null) {
						%>
						<div class="alert alert-danger" role="alert">
							<%=error%>
						</div>
						<%
						}
						%>
						<form action="login" method="post">
							<div class="form-group">
								<label for="username">Nombre de Usuario:</label> <input
									type="text" id="username" name="username" class="form-control"
									required>
							</div>
							<div class="form-group">
								<label for="password">Contraseña:</label> <input type="password"
									id="password" name="password" class="form-control" required>
							</div>
							<button type="submit" class="btn btn-primary btn-block">Iniciar
								sesión</button>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
