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
import model.Transaccion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/transaccion")
public class TransaccionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private TransaccionDAO transaccionDAO;
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        transaccionDAO = new TransaccionDAOImpl();
        usuarioDAO = new UsuarioDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String nombreUsuario = (String) request.getSession().getAttribute("nombreUsuario");
        double monto = Double.parseDouble(request.getParameter("monto"));

        if (action != null) {
            try {
                if (monto <= 0) {
                    request.getSession().setAttribute("error", "¡Atención! El monto debe ser mayor que 0");
                    actualizarVistaConHistorial(request, response, nombreUsuario);
                    return;
                }

                switch (action) {
                    case "depositar":
                        transaccionDAO.realizarDeposito(nombreUsuario, monto);
                        request.getSession().setAttribute("message", "¡Depósito exitoso!");
                        break;
                    case "retirar":
                        double saldoActual = usuarioDAO.consultarSaldo(nombreUsuario);
                        if (monto > saldoActual) {
                            request.getSession().setAttribute("error", "Error.Saldo insuficiente");
                            actualizarVistaConHistorial(request, response, nombreUsuario);
                            return;
                        }
                        transaccionDAO.realizarRetiro(nombreUsuario, monto);
                        request.getSession().setAttribute("message", "¡Retiro exitoso!");
                        break;
                    default:
                        response.sendRedirect("index.jsp");
                        return;
                }

                actualizarVistaConHistorial(request, response, nombreUsuario);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la transacción");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String nombreUsuario = (String) request.getSession().getAttribute("nombreUsuario");

        if ("historial".equals(action)) {
            try {
                Boolean mostrarHistorial = (Boolean) request.getSession().getAttribute("mostrarHistorial");
                if (mostrarHistorial == null || !mostrarHistorial) {
                    request.getSession().setAttribute("mostrarHistorial", true);
                    List<Transaccion> historial = usuarioDAO.obtenerHistorial(nombreUsuario);
                    request.getSession().setAttribute("historial", historial);
                } else {
                    request.getSession().setAttribute("mostrarHistorial", false);
                    request.getSession().removeAttribute("historial");
                }
                actualizarVistaConHistorial(request, response, nombreUsuario);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al consultar el historial");
            }
        }
    }

    private void actualizarVistaConHistorial(HttpServletRequest request, HttpServletResponse response, String nombreUsuario) throws SQLException, ServletException, IOException {
        double saldo = usuarioDAO.consultarSaldo(nombreUsuario);
        request.getSession().setAttribute("saldo", saldo);
        response.sendRedirect("inicio.jsp");
    }
}
