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

@WebServlet("/inicio")
public class InicioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        usuarioDAO = new UsuarioDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreUsuario = (String) request.getSession().getAttribute("nombreUsuario");

        if (nombreUsuario != null) {
            try {
                double saldo = usuarioDAO.consultarSaldo(nombreUsuario);
                request.setAttribute("saldo", saldo);
                request.getRequestDispatcher("inicio.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener el saldo del usuario");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
