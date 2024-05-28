package controller;

import dao.UsuarioDAO;
import dao.TransaccionDAO;
import daoImpl.UsuarioDAOImpl;
import daoImpl.TransaccionDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/transaccion")
public class TransaccionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private TransaccionDAO transaccionDAO;
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        // Creando instancia de la implementación de TransaccionDAO con DriverManager
        transaccionDAO = new TransaccionDAOImpl();
        usuarioDAO = new UsuarioDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String nombreUsuario = (String) request.getSession().getAttribute("nombreUsuario");
        double monto = Double.parseDouble(request.getParameter("monto"));

        if (action != null) {
            try {
                switch (action) {
                    case "depositar":
                        transaccionDAO.realizarDeposito(nombreUsuario, monto);
                        request.setAttribute("message", "Depósito exitoso");
                        break;
                    case "retirar":
                        transaccionDAO.realizarRetiro(nombreUsuario, monto);
                        request.setAttribute("message", "Retiro exitoso");
                        break;
                    default:
                        response.sendRedirect("index.jsp");
                        return;
                }

                // Obtener el saldo actualizado
                double saldo = usuarioDAO.consultarSaldo(nombreUsuario);
                request.setAttribute("saldo", saldo);
                request.getRequestDispatcher("resultado.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la transacción");
            }
        }
    }
}
