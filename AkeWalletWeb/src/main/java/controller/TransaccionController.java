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

/**
 * Servlet que maneja las transacciones financieras de los usuarios.
 */
@WebServlet("/transaccion")
public class TransaccionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private TransaccionDAO transaccionDAO;
    private UsuarioDAO usuarioDAO;

    /**
     * Inicializa el servlet y crea instancias de TransaccionDAOImpl y UsuarioDAOImpl.
     *
     * @throws ServletException Si ocurre algún error durante la inicialización del servlet.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        transaccionDAO = new TransaccionDAOImpl();
        usuarioDAO = new UsuarioDAOImpl();
    }

    /**
     * Maneja las solicitudes POST para realizar transacciones financieras.
     *
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta que se enviará al cliente.
     * @throws ServletException Si ocurre algún error durante el manejo de la solicitud.
     * @throws IOException      Si ocurre algún error de entrada/salida durante el manejo de la solicitud.
     */
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

    /**
     * Maneja las solicitudes GET para obtener el historial de transacciones.
     *
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta que se enviará al cliente.
     * @throws ServletException Si ocurre algún error durante el manejo de la solicitud.
     * @throws IOException      Si ocurre algún error de entrada/salida durante el manejo de la solicitud.
     */
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

    /**
     * Actualiza la vista con el saldo actual del usuario y redirige a la página de inicio.
     *
     * @param request        El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response       El objeto HttpServletResponse que contiene la respuesta que se enviará al cliente.
     * @param nombreUsuario El nombre del usuario del que se actualizará el saldo.
     * @throws SQLException  Si ocurre algún error al interactuar con la base de datos.
     * @throws ServletException Si ocurre algún error durante el manejo de la solicitud.
     * @throws IOException      Si ocurre algún error de entrada/salida durante el manejo de la solicitud.
     */
    private void actualizarVistaConHistorial(HttpServletRequest request, HttpServletResponse response, String nombreUsuario) throws SQLException, ServletException, IOException {
        double saldo = usuarioDAO.consultarSaldo(nombreUsuario);
        request.getSession().setAttribute("saldo", saldo);
        response.sendRedirect("inicio.jsp");
    }
}
