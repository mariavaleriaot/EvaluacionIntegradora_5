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

    // Inyectar mocks en la instancia de la clase DAO (creamos una subclase anónima porque DAO es abstracta)
    @InjectMocks
    private DAO dao = new DAO() {}; 

    // Crear mocks para las dependencias de la base de datos
    @Mock
    private Connection conexionMock;

    @Mock
    private Statement declaracionMock;

    @Mock
    private ResultSet resultadoMock;

    // Inicializar los mocks antes de cada prueba
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Prueba para el método conectarDb
    @Test
    void testConectarDb() throws Exception {
        // Configurar el mock para que simule que la conexión está cerrada
        when(conexionMock.isClosed()).thenReturn(true);

        // Llamar al método que queremos probar
        dao.conectarDb();

        // Verificar que se llamó al método isClosed una vez
        verify(conexionMock, times(1)).isClosed();
    }

    // Prueba para el método consultar
    @Test
    void testConsultar() throws Exception {
        String sql = "SELECT * FROM prueba";
        
        // Configurar los mocks para simular la creación de una declaración y la ejecución de una consulta
        when(conexionMock.createStatement()).thenReturn(declaracionMock);
        when(declaracionMock.executeQuery(sql)).thenReturn(resultadoMock);

        // Llamar al método que queremos probar
        ResultSet resultado = dao.consultar(sql);

        // Verificar que se llamó a createStatement una vez
        verify(conexionMock, times(1)).createStatement();
        // Verificar que se llamó a executeQuery con el SQL correcto una vez
        verify(declaracionMock, times(1)).executeQuery(sql);
        // Verificar que el resultado es el esperado
        assertEquals(resultadoMock, resultado);
    }

    // Prueba para el método ejecutarSql
    @Test
    void testEjecutarSql() throws Exception {
        String sql = "UPDATE prueba SET valor = 1";
        
        // Configurar los mocks para simular la creación de una declaración y la ejecución de una actualización
        when(conexionMock.createStatement()).thenReturn(declaracionMock);
        when(declaracionMock.executeUpdate(sql)).thenReturn(1);

        // Llamar al método que queremos probar
        int resultado = dao.ejecutarSql(sql);

        // Verificar que se llamó a createStatement una vez
        verify(conexionMock, times(1)).createStatement();
        // Verificar que se llamó a executeUpdate con el SQL correcto una vez
        verify(declaracionMock, times(1)).executeUpdate(sql);
        // Verificar que el resultado es el esperado
        assertEquals(1, resultado);
    }

    // Prueba para el método close
    @Test
    void testCerrar() throws Exception {
        // Llamar al método que queremos probar
        dao.close();

        // Verificar que se cerraron las conexiones, declaraciones y resultados
        verify(conexionMock, times(1)).close();
        verify(declaracionMock, times(1)).close();
        verify(resultadoMock, times(1)).close();
    }
}

