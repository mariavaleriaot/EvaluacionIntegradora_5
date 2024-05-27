package daoImpl;

import dao.DAO;
import dao.UsuarioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImpl extends DAO implements UsuarioDAO {

    @Override
    public double consultarSaldo(String nombreUsuario) throws SQLException {
        double saldo = 0.0;
        String query = "SELECT saldo FROM usuario WHERE nombre = ?";
       
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombreUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    saldo = rs.getDouble("saldo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return saldo;
    }

    @Override
    public void realizarDeposito(String nombreUsuario, double monto) throws SQLException {
        String query = "UPDATE usuario SET saldo = saldo + ? WHERE nombre = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, monto);
            stmt.setString(2, nombreUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void realizarRetiro(String nombreUsuario, double monto) throws SQLException {
        String query = "UPDATE usuario SET saldo = saldo - ? WHERE nombre = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, monto);
            stmt.setString(2, nombreUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
