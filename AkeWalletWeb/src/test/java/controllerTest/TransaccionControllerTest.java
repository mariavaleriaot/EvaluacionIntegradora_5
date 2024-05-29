package controllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Transaccion;
import org.junit.jupiter.api.Test;

import controller.TransaccionController;
import dao.TransaccionDAO;
import dao.UsuarioDAO;

public class TransaccionControllerTest {

	@Test
	public void testDoPost_DepositSuccessful() throws ServletException, IOException, SQLException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		UsuarioDAO usuarioDAO = mock(UsuarioDAO.class);
		TransaccionDAO transaccionDAO = mock(TransaccionDAO.class);

		when(request.getParameter("action")).thenReturn("depositar");
		when(request.getSession().getAttribute("nombreUsuario")).thenReturn("Lalo Landa");
		when(request.getParameter("monto")).thenReturn("100");

		TransaccionController transaccionController = new TransaccionController();
		transaccionController.setTransaccionDAO(transaccionDAO);
		transaccionController.setUsuarioDAO(usuarioDAO);

		transaccionController.doPost(request, response);

		verify(transaccionDAO).realizarDeposito("Lalo Landa", 100);
		verify(request.getSession()).setAttribute(eq("message"), anyString());
		verify(response).sendRedirect("inicio.jsp");
	}

	@Test
	public void testDoPost_WithdrawalSuccessful() throws ServletException, IOException, SQLException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		UsuarioDAO usuarioDAO = mock(UsuarioDAO.class);
		TransaccionDAO transaccionDAO = mock(TransaccionDAO.class);

		when(request.getParameter("action")).thenReturn("retirar");
		when(request.getSession().getAttribute("nombreUsuario")).thenReturn("Lalo Landa");
		when(request.getParameter("monto")).thenReturn("50");
		when(usuarioDAO.consultarSaldo("Lalo Landa")).thenReturn(100.0);

		TransaccionController transaccionController = new TransaccionController();
		transaccionController.setTransaccionDAO(transaccionDAO);
		transaccionController.setUsuarioDAO(usuarioDAO);

		transaccionController.doPost(request, response);

		verify(transaccionDAO).realizarRetiro("Lalo Landa", 50);
		verify(request.getSession()).setAttribute(eq("message"), anyString());
		verify(response).sendRedirect("inicio.jsp");
	}

	@Test
	public void testDoGet_ToggleHistory() throws ServletException, IOException, SQLException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		UsuarioDAO usuarioDAO = mock(UsuarioDAO.class);

		when(request.getParameter("action")).thenReturn("historial");
		when(request.getSession().getAttribute("nombreUsuario")).thenReturn("Lalo Landa");
		when(request.getSession().getAttribute("mostrarHistorial")).thenReturn(false);

		List<Transaccion> historial = new ArrayList<>();
		historial.add(new Transaccion("Depósito", 100));
		when(usuarioDAO.obtenerHistorial("Lalo Landa")).thenReturn(historial);

		TransaccionController transaccionController = new TransaccionController();
		transaccionController.setUsuarioDAO(usuarioDAO);

		transaccionController.doGet(request, response);

		verify(request.getSession()).setAttribute("mostrarHistorial", true);
		verify(request.getSession()).setAttribute("historial", historial);
		verify(response).sendRedirect("inicio.jsp");
	}
}
