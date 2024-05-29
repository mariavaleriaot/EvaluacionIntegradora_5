package controllerTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;

import controller.LoginServlet;

public class LoginServletTest {

    @Test
    public void testDoGet() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        LoginServlet loginServlet = new LoginServlet();
        loginServlet.doGet(request, response);
        
        verify(response).sendRedirect("login.jsp");
    }

    @Test
    public void testDoPost_SuccessfulLogin() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("username")).thenReturn("Lalo Landa");
        when(request.getParameter("password")).thenReturn("Lalo123");
        
        LoginServlet loginServlet = new LoginServlet();
        loginServlet.doPost(request, response);
        
        verify(request.getSession()).setAttribute("nombreUsuario", "Lalo Landa");
        verify(response).sendRedirect("inicio");
    }

    @Test
    public void testDoPost_IncorrectCredentials() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        
        when(request.getParameter("username")).thenReturn("invalid");
        when(request.getParameter("password")).thenReturn("invalid");
        when(request.getRequestDispatcher("login.jsp")).thenReturn(dispatcher);
        
        LoginServlet loginServlet = new LoginServlet();
        loginServlet.doPost(request, response);
        
        verify(request).setAttribute("error", "Usuario o contraseña incorrectos");
        verify(dispatcher).forward(request, response);
    }
}
