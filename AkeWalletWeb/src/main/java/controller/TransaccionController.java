package controller;

import dao.TransaccionDAO;
import daoImpl.TransaccionDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/transaccion")
public class TransaccionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private TransaccionDAO transaccionDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        // Creando instancia de la implementación de TransaccionDAO con DriverManager
        transaccionDAO = new TransaccionDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String nombreUsuario = "Lalo Landa"; // Obtener nombre de usuario de la sesión
        double monto = Double.parseDouble(request.getParameter("monto"));

        if (action != null) {
            switch (action) {
                case "depositar":
                    transaccionDAO.realizarDeposito(nombreUsuario, monto);
                    break;
                case "retirar":
                    transaccionDAO.realizarRetiro(nombreUsuario, monto);
                    break;
                default:
                    response.sendRedirect("index.jsp");
                    return;
            }
        }

        response.sendRedirect("inicio.jsp");
    }
}
