package daoimplTest;


import daoImpl.UsuarioDAOImpl;
import model.Transaccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioDAOImplTest {

    @InjectMocks
    private UsuarioDAOImpl usuarioDAO;

    @Mock
    private Connection connMock;

    @Mock
    private PreparedStatement stmtMock;

    @Mock
    private ResultSet rsMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsultarSaldo() throws SQLException {
        String nombreUsuario = "usuario1";
        double saldoEsperado = 100.0;

        // Configurar el mock para el resultado esperado de la consulta
        when(rsMock.next()).thenReturn(true);
        when(rsMock.getDouble("saldo")).thenReturn(saldoEsperado);

        // Configurar el mock para la ejecución de la consulta
        when(connMock.prepareStatement(anyString())).thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(rsMock);

        // Llamar al método a probar
        double saldoActual = usuarioDAO.consultarSaldo(nombreUsuario);

        // Verificar que se llamó a los métodos necesarios
        verify(connMock).prepareStatement(anyString());
        verify(stmtMock).setString(1, nombreUsuario);
        verify(stmtMock).executeQuery();
        // Verificar que el saldo devuelto es el esperado
        assertEquals(saldoEsperado, saldoActual);
    }

    @Test
    void testRealizarDeposito() throws SQLException {
        String nombreUsuario = "usuario1";
        double monto = 100.0;

        // Configurar el mock para la ejecución del update
        when(connMock.prepareStatement(anyString())).thenReturn(stmtMock);

        // Llamar al método a probar
        assertDoesNotThrow(() -> usuarioDAO.realizarDeposito(nombreUsuario, monto));

        // Verificar que se llamó a los métodos necesarios
        verify(connMock).prepareStatement(anyString());
        verify(stmtMock).setDouble(1, monto);
        verify(stmtMock).setString(2, nombreUsuario);
        verify(stmtMock).executeUpdate();
    }

    @Test
    void testRealizarRetiro() throws SQLException {
        String nombreUsuario = "usuario1";
        double monto = 50.0;

        // Configurar el mock para la ejecución del update
        when(connMock.prepareStatement(anyString())).thenReturn(stmtMock);

        // Llamar al método a probar
        assertDoesNotThrow(() -> usuarioDAO.realizarRetiro(nombreUsuario, monto));

        // Verificar que se llamó a los métodos necesarios
        verify(connMock).prepareStatement(anyString());
        verify(stmtMock).setDouble(1, monto);
        verify(stmtMock).setString(2, nombreUsuario);
        verify(stmtMock).executeUpdate();
    }

    @Test
    void testObtenerHistorial() throws SQLException {
        String nombreUsuario = "usuario1";
        List<Transaccion> transaccionesEsperadas = new ArrayList<>();
        transaccionesEsperadas.add(new Transaccion(1, 1, "deposito", 100.0, new Timestamp(System.currentTimeMillis())));
        transaccionesEsperadas.add(new Transaccion(2, 1, "retiro", 50.0, new Timestamp(System.currentTimeMillis())));

        // Configurar el mock para el resultado esperado de la consulta
        when(rsMock.next()).thenReturn(true, true, false);
        when(rsMock.getString("tipo")).thenReturn("deposito", "retiro");
        when(rsMock.getDouble("monto")).thenReturn(100.0, 50.0);
        when(rsMock.getTimestamp("fecha")).thenReturn(new Timestamp(System.currentTimeMillis()));

        // Configurar el mock para la ejecución de la consulta
        when(connMock.prepareStatement(anyString())).thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(rsMock);

        // Llamar al método a probar
        List<Transaccion> transaccionesObtenidas = usuarioDAO.obtenerHistorial(nombreUsuario);

        // Verificar que se llamó a los métodos necesarios
        verify(connMock).prepareStatement(anyString());
        verify(stmtMock).setString(1, nombreUsuario);
        verify(stmtMock).executeQuery();
        // Verificar que las transacciones devueltas son las esperadas
        assertEquals(transaccionesEsperadas.size(), transaccionesObtenidas.size());
        for (int i = 0; i < transaccionesEsperadas.size(); i++) {
            Transaccion esperada = transaccionesEsperadas.get(i);
            Transaccion obtenida = transaccionesObtenidas.get(i);
            assertEquals(esperada.getTipo(), obtenida.getTipo());
            assertEquals(esperada.getMonto(), obtenida.getMonto());
            assertEquals(esperada.getFecha(), obtenida.getFecha());
        }
    }
}
