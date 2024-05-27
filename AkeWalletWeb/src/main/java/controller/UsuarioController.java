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

@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        // Creando instancia de la implementación de UsuarioDAO con DriverManager
        usuarioDAO = new UsuarioDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "consultarSaldo":
                    consultarSaldo(request, response);
                    break;
                default:
                    response.sendRedirect("index.jsp");
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Tu lógica para manejar las solicitudes POST relacionadas con los usuarios
    }

    private void consultarSaldo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreUsuario = (String) request.getSession().getAttribute("nombreUsuario");
        try {
            double saldo = usuarioDAO.consultarSaldo(nombreUsuario);
            request.setAttribute("saldo", saldo);
            request.getRequestDispatcher("inicio.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener el saldo del usuario");
        }
    }
}
