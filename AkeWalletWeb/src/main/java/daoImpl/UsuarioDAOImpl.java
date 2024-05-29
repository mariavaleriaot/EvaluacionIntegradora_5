package daoImpl;

import dao.DAO;
import dao.UsuarioDAO;
import model.Transaccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación concreta de UsuarioDAO que proporciona métodos para interactuar con la base de datos
 * relacionados con la gestión de usuarios y sus transacciones.
 */
public class UsuarioDAOImpl extends DAO implements UsuarioDAO {

    /**
     * Consulta el saldo actual de un usuario en la base de datos.
     *
     * @param nombreUsuario Nombre del usuario del que se desea consultar el saldo.
     * @return El saldo actual del usuario.
     * @throws SQLException Si ocurre algún error al interactuar con la base de datos.
     */
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

    /**
     * Realiza un depósito en la cuenta de un usuario en la base de datos.
     *
     * @param nombreUsuario Nombre del usuario al que se le realizará el depósito.
     * @param monto         Monto del depósito a realizar.
     * @throws SQLException Si ocurre algún error al interactuar con la base de datos.
     */
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

    /**
     * Realiza un retiro de la cuenta de un usuario en la base de datos.
     *
     * @param nombreUsuario Nombre del usuario al que se le realizará el retiro.
     * @param monto         Monto del retiro a realizar.
     * @throws SQLException Si ocurre algún error al interactuar con la base de datos.
     */
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

    /**
     * Obtiene el historial de transacciones de un usuario desde la base de datos.
     *
     * @param nombreUsuario Nombre del usuario del que se desea obtener el historial de transacciones.
     * @return Lista de transacciones del usuario.
     * @throws SQLException Si ocurre algún error al interactuar con la base de datos.
     */
    @Override
    public List<Transaccion> obtenerHistorial(String nombreUsuario) throws SQLException {
        List<Transaccion> historial = new ArrayList<>();
        String query = "SELECT t.tipo, t.monto, t.fecha FROM transaccion t JOIN usuario u ON t.usuario_id = u.id WHERE u.nombre = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombreUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transaccion transaccion = new Transaccion();
                    transaccion.setTipo(rs.getString("tipo"));
                    transaccion.setMonto(rs.getDouble("monto"));
                    transaccion.setFecha(rs.getTimestamp("fecha"));
                    historial.add(transaccion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return historial;
    }
}
