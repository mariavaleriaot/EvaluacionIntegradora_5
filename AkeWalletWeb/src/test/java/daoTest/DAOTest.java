package daoTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dao.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DAOTest {

    @InjectMocks
    private DAO dao;

    @Mock
    private Connection conexionMock;

    @Mock
    private Statement declaracionMock;

    @Mock
    private ResultSet resultadoMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConectarDb() throws Exception {
        when(conexionMock.isClosed()).thenReturn(true);

        dao.conectarDb();

        verify(conexionMock, times(1)).isClosed();
    }

    @Test
    void testConsultar() throws Exception {
        String sql = "SELECT * FROM prueba";

        when(conexionMock.createStatement()).thenReturn(declaracionMock);
        when(declaracionMock.executeQuery(sql)).thenReturn(resultadoMock);

        ResultSet resultado = dao.consultar(sql);

        verify(conexionMock, times(1)).createStatement();
        verify(declaracionMock, times(1)).executeQuery(sql);
        assertNotNull(resultado);
    }

    @Test
    void testEjecutarSql() throws Exception {
        String sql = "UPDATE prueba SET valor = 1";

        when(conexionMock.createStatement()).thenReturn(declaracionMock);
        when(declaracionMock.executeUpdate(sql)).thenReturn(1);

        int resultado = dao.ejecutarSql(sql);

        verify(conexionMock, times(1)).createStatement();
        verify(declaracionMock, times(1)).executeUpdate(sql);
        assertEquals(1, resultado);
    }

    @Test
    void testCerrar() throws Exception {
        dao.close();

        verify(conexionMock, times(1)).close();
        verify(declaracionMock, times(1)).close();
        verify(resultadoMock, times(1)).close();
    }
}
