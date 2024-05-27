package dao;

import java.util.List;
import model.Transaccion;

public interface TransaccionDAO {
    void realizarDeposito(String nombreUsuario, double monto);
    void realizarRetiro(String nombreUsuario, double monto);
    List<Transaccion> obtenerTransacciones(int usuarioId);
}
