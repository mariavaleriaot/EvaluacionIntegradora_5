package modelTest;



import org.junit.jupiter.api.Test;

import model.Usuario;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void testConstructorAndGetters() {
        // Crear una instancia de Usuario usando el constructor con parámetros
        int id = 1;
        String nombre = "usuario1";
        String clave = "password123";
        double saldo = 100.0;

        Usuario usuario = new Usuario(id, nombre, clave, saldo);

        // Verificar que los valores se han inicializado correctamente
        assertEquals(id, usuario.getId());
        assertEquals(nombre, usuario.getNombre());
        assertEquals(clave, usuario.getClave());
        assertEquals(saldo, usuario.getSaldo());
    }

    @Test
    void testSetters() {
        // Crear una instancia de Usuario
        Usuario usuario = new Usuario();

        // Establecer nuevos valores usando los setters
        int id = 2;
        String nombre = "usuario2";
        String clave = "newPassword";
        double saldo = 200.0;

        usuario.setId(id);
        usuario.setNombre(nombre);
        usuario.setClave(clave);
        usuario.setSaldo(saldo);

        // Verificar que los valores se han establecido correctamente
        assertEquals(id, usuario.getId());
        assertEquals(nombre, usuario.getNombre());
        assertEquals(clave, usuario.getClave());
        assertEquals(saldo, usuario.getSaldo());
    }
}
