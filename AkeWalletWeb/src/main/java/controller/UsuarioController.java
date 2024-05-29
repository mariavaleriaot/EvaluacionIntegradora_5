package controller;

import dao.UsuarioDAO;
import daoImpl.UsuarioDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Transaccion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet que maneja las solicitudes relacionadas con el usuario.
 */
@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO usuarioDAO;

    /**
     * Inicializa el servlet y crea una instancia de UsuarioDAOImpl.
     *
     * @throws ServletException Si ocurre algún error durante la inicialización del servlet.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        usuarioDAO = new UsuarioDAOImpl();
    }

    /**
     * Maneja las solicitudes GET relacionadas con el usuario.
     *
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta que se enviará al cliente.
     * @throws ServletException Si ocurre algún error durante el manejo de la solicitud.
     * @throws IOException      Si ocurre algún error de entrada/salida durante el manejo de la solicitud.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String nombreUsuario = (String) request.getSession().getAttribute("nombreUsuario");

        if ("consultarSaldo".equals(action)) {
            try {
                double saldo = usuarioDAO.consultarSaldo(nombreUsuario);
                List<Transaccion> historial = usuarioDAO.obtenerHistorial(nombreUsuario);
                request.getSession().setAttribute("saldo", saldo);
                request.getSession().setAttribute("historial", historial);
                response.sendRedirect("inicio.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al consultar el saldo");
            }
        }
    }
}
