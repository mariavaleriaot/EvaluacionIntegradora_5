package controller;

import java.io.IOException;

import dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login") 
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    

    
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp"); // Redirige a la página de inicio de sesión si alguien intenta acceder directamente al servlet con GET
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

//        // Verifica que username no sea null
//        if (username == null || username.isEmpty()) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Username is missing");
//            return;
//        }
//
//        // Verifica que password no sea null
//        if (password == null || password.isEmpty()) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Password is missing");
//            return;
//        }
        
        // Verificar si el usuario y la contraseña son correctos
        if (username.equals("Lalo Landa") && password.equals("Lalo123")) {
            request.getSession().setAttribute("nombreUsuario", username);
            response.sendRedirect("inicio.jsp"); // Redirige al servlet InicioServlet
        } else {
            // Usuario o contraseña incorrectos, enviar mensaje de alerta y redirigir a la página de inicio de sesión
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
