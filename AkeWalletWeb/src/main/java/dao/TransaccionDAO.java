package dao;

import java.sql.SQLException;
import java.util.List;
import model.Transaccion;

public interface TransaccionDAO {
    void realizarDeposito(String nombreUsuario, double monto) throws SQLException;
    void realizarRetiro(String nombreUsuario, double monto) throws SQLException;
    List<Transaccion> obtenerTransacciones(int usuarioId);
}
