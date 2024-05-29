package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet que maneja las solicitudes de inicio de sesión.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Maneja las solicitudes GET para el inicio de sesión.
     *
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta que se enviará al cliente.
     * @throws ServletException Si ocurre algún error durante el manejo de la solicitud.
     * @throws IOException      Si ocurre algún error de entrada/salida durante el manejo de la solicitud.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp"); // Redirige a la página de inicio de sesión si alguien intenta acceder directamente al servlet con GET
    }

    /**
     * Maneja las solicitudes POST para el inicio de sesión.
     *
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta que se enviará al cliente.
     * @throws ServletException Si ocurre algún error durante el manejo de la solicitud.
     * @throws IOException      Si ocurre algún error de entrada/salida durante el manejo de la solicitud.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Verificar si el usuario y la contraseña son correctos
        if (username.equals("Lalo Landa") && password.equals("Lalo123")) {
            request.getSession().setAttribute("nombreUsuario", username);
            response.sendRedirect("inicio"); // Redirige al servlet InicioServlet
        } else {
            // Usuario o contraseña incorrectos, enviar mensaje de alerta y redirigir a la página de inicio de sesión
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
