package controller;

import dao.UsuarioDAO;
import daoImpl.UsuarioDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet que maneja las solicitudes para la página de inicio de sesión.
 * Este servlet verifica si el usuario está autenticado y muestra su saldo si lo está.
 */
@WebServlet("/inicio")
public class InicioServlet extends HttpServlet {
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
     * Maneja las solicitudes GET para la página de inicio.
     *
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta que se enviará al cliente.
     * @throws ServletException Si ocurre algún error durante el manejo de la solicitud.
     * @throws IOException      Si ocurre algún error de entrada/salida durante el manejo de la solicitud.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreUsuario = (String) request.getSession().getAttribute("nombreUsuario");

        if (nombreUsuario != null) {
            try {
                // Consulta el saldo del usuario y lo agrega como atributo a la solicitud
                double saldo = usuarioDAO.consultarSaldo(nombreUsuario);
                request.setAttribute("saldo", saldo);
                request.getRequestDispatcher("inicio.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener el saldo del usuario");
            }
        } else {
            // Si no hay un usuario autenticado, redirige a la página de inicio de sesión
            response.sendRedirect("login.jsp");
        }
    }
}
