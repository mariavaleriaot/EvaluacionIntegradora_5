package modelTest;



import org.junit.jupiter.api.Test;

import model.Transaccion;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class TransaccionTest {

    @Test
    void testConstructorAndGetters() {
        // Crear una instancia de Transaccion usando el constructor con parámetros
        int id = 1;
        int usuarioId = 2;
        String tipo = "deposito";
        double monto = 100.0;
        Timestamp fecha = new Timestamp(System.currentTimeMillis());

        Transaccion transaccion = new Transaccion(id, usuarioId, tipo, monto, fecha);

        // Verificar que los valores se han inicializado correctamente
        assertEquals(id, transaccion.getId());
        assertEquals(usuarioId, transaccion.getUsuarioId());
        assertEquals(tipo, transaccion.getTipo());
        assertEquals(monto, transaccion.getMonto());
        assertEquals(fecha, transaccion.getFecha());
    }

    @Test
    void testSetters() {
        // Crear una instancia de Transaccion
        Transaccion transaccion = new Transaccion();

        // Establecer nuevos valores usando los setters
        int id = 1;
        int usuarioId = 2;
        String tipo = "retiro";
        double monto = 50.0;
        Timestamp fecha = new Timestamp(System.currentTimeMillis());

        transaccion.setId(id);
        transaccion.setUsuarioId(usuarioId);
        transaccion.setTipo(tipo);
        transaccion.setMonto(monto);
        transaccion.setFecha(fecha);

        // Verificar que los valores se han establecido correctamente
        assertEquals(id, transaccion.getId());
        assertEquals(usuarioId, transaccion.getUsuarioId());
        assertEquals(tipo, transaccion.getTipo());
        assertEquals(monto, transaccion.getMonto());
        assertEquals(fecha, transaccion.getFecha());
    }
}
