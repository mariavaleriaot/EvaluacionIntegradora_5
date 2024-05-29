package daoimplTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dao.DAO;
import daoImpl.TransaccionDAOImpl;

class TransaccionDAOImplTest {

    @Mock
    private Connection connMock;

    @Mock
    private PreparedStatement stmtMock;

    @Mock
    private ResultSet rsMock;

    @InjectMocks
    private TransaccionDAOImpl transaccionDAO;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        // Configurar el DAO para usar el mock de la conexión
        transaccionDAO = spy(new TransaccionDAOImpl());
        doReturn(connMock).when(transaccionDAO).getConnection();
    }

    @Test
    void realizarDepositoTest() throws SQLException {
        // Configurar el mock para el resultado esperado de la consulta del id del usuario
        when(rsMock.next()).thenReturn(true);
        when(rsMock.getInt("id")).thenReturn(1);
        // Configurar el mock para la ejecución de la transacción
        when(connMock.prepareStatement(anyString())).thenReturn(stmtMock);

        // Llamar al método a probar
        assertDoesNotThrow(() -> transaccionDAO.realizarDeposito("usuario", 100.0));

        // Verificar que se hayan llamado a los métodos necesarios
        verify(connMock).setAutoCommit(false);
        verify(connMock, atLeastOnce()).prepareStatement(anyString());
        verify(stmtMock, times(2)).executeUpdate();
        verify(connMock).commit();
        verify(connMock).setAutoCommit(true);
        verify(transaccionDAO).close();
    }

    @Test
    void realizarRetiroTest() throws SQLException {
        // Configurar el mock para el resultado esperado de la consulta del id del usuario
        when(rsMock.next()).thenReturn(true);
        when(rsMock.getInt("id")).thenReturn(1);

        // Configurar el mock para la ejecución de la transacción
        when(connMock.prepareStatement(anyString())).thenReturn(stmtMock);

        // Llamar al método a probar
        assertDoesNotThrow(() -> transaccionDAO.realizarRetiro("usuario", 100.0));

        // Verificar que se hayan llamado a los métodos necesarios
        verify(connMock).setAutoCommit(false);
        verify(connMock, atLeastOnce()).prepareStatement(anyString());
        verify(stmtMock, times(2)).executeUpdate();
        verify(connMock).commit();
        verify(connMock).setAutoCommit(true);
        verify(transaccionDAO).close();
    }
}
