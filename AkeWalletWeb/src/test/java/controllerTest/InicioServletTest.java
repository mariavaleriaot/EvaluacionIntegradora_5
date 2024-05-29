package controllerTest;


import dao.UsuarioDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import controller.InicioServlet;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class InicioServletTest {

    @InjectMocks
    private InicioServlet inicioServlet;

    @Mock
    private UsuarioDAO usuarioDAO;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoGet_UsuarioLogueado() throws ServletException, IOException, SQLException {
        // Configurar el comportamiento esperado del request y del DAO
        when(request.getSession().getAttribute("nombreUsuario")).thenReturn("usuario1");
        when(usuarioDAO.consultarSaldo("usuario1")).thenReturn(100.0);
        when(request.getRequestDispatcher("inicio.jsp")).thenReturn(requestDispatcher);

        // Llamar al método doGet del servlet
        inicioServlet.doGet(request, response);

        // Verificar que se redireccionó correctamente
        verify(requestDispatcher).forward(request, response);
        // Verificar que se estableció el saldo en el request
        verify(request).setAttribute("saldo", 100.0);
    }

    @Test
    void testDoGet_UsuarioNoLogueado() throws ServletException, IOException {
        // Configurar el comportamiento esperado del request
        when(request.getSession().getAttribute("nombreUsuario")).thenReturn(null);

        // Llamar al método doGet del servlet
        inicioServlet.doGet(request, response);

        // Verificar que se redireccionó correctamente
        verify(response).sendRedirect("login.jsp");
    }

    @Test
    void testDoGet_ErrorSQLException() throws ServletException, IOException, SQLException {
        // Configurar el comportamiento esperado del request y del DAO
        when(request.getSession().getAttribute("nombreUsuario")).thenReturn("usuario1");
        when(usuarioDAO.consultarSaldo("usuario1")).thenThrow(new SQLException());

        // Llamar al método doGet del servlet
        inicioServlet.doGet(request, response);

        // Verificar que se envió un error HTTP 500
        verify(response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener el saldo del usuario");
    }
}
