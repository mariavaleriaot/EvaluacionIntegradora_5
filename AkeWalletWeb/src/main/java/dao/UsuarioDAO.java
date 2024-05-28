package dao;

import java.sql.SQLException;
import java.util.List;
import model.Transaccion;

public interface UsuarioDAO {
    double consultarSaldo(String nombreUsuario) throws SQLException;
    void realizarDeposito(String nombreUsuario, double monto) throws SQLException;
    void realizarRetiro(String nombreUsuario, double monto) throws SQLException;
    List<Transaccion> obtenerHistorial(String nombreUsuario) throws SQLException;
}
