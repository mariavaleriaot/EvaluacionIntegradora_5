package dao;

import java.sql.SQLException;

public interface UsuarioDAO {
    double consultarSaldo(String nombreUsuario) throws SQLException;
    void realizarDeposito(String nombreUsuario, double monto) throws SQLException;
    void realizarRetiro(String nombreUsuario, double monto) throws SQLException;
}
