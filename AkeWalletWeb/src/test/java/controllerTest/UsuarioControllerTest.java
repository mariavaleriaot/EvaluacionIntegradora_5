package controllerTest;



import controller.UsuarioController;
import dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Transaccion;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class UsuarioControllerTest {

    @Test
    public void testDoGet_ConsultarSaldo() throws ServletException, IOException, SQLException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        UsuarioDAO usuarioDAO = mock(UsuarioDAO.class);

        // Simula los parámetros necesarios para el método doGet
        when(request.getParameter("action")).thenReturn("consultarSaldo");
        when(request.getSession().getAttribute("nombreUsuario")).thenReturn("Lalo Landa");

        // Simula el saldo y el historial que se obtendrán del DAO
        double saldo = 1000.0;
        List<Transaccion> historial = new ArrayList<>();
        when(usuarioDAO.consultarSaldo("Lalo Landa")).thenReturn(saldo);
        when(usuarioDAO.obtenerHistorial("Lalo Landa")).thenReturn(historial);

        UsuarioController usuarioController = new UsuarioController();
        usuarioController.setUsuarioDAO(usuarioDAO);

        usuarioController.doGet(request, response);

        // Verifica si se establecen los atributos de sesión correctamente
        verify(request.getSession()).setAttribute("saldo", saldo);
        verify(request.getSession()).setAttribute("historial", historial);
        verify(response).sendRedirect("inicio.jsp");
    }
}
