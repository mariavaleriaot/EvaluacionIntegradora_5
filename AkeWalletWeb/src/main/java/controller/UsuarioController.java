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

@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        usuarioDAO = new UsuarioDAOImpl();
    }

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
