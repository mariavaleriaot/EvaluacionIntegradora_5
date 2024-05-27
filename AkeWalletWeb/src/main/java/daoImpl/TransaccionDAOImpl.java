package daoImpl;

import dao.DAO;
import dao.TransaccionDAO;
import model.Transaccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransaccionDAOImpl extends DAO implements TransaccionDAO {

    @Override
    public void realizarDeposito(String nombreUsuario, double monto) {
        String query = "UPDATE usuario SET saldo = saldo + ? WHERE nombre = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, monto);
            stmt.setString(2, nombreUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void realizarRetiro(String nombreUsuario, double monto) {
        String query = "UPDATE usuario SET saldo = saldo - ? WHERE nombre = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, monto);
            stmt.setString(2, nombreUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaccion> obtenerTransacciones(int usuarioId) {
        String query = "SELECT * FROM transaccion WHERE usuario_id = ?";
        List<Transaccion> transacciones = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transaccion transaccion = new Transaccion();
                    transaccion.setId(rs.getInt("id"));
                    transaccion.setUsuarioId(rs.getInt("usuario_id"));
                    transaccion.setFecha(rs.getTimestamp("fecha"));
                    transaccion.setTipo(rs.getString("tipo"));
                    transaccion.setMonto(rs.getDouble("monto"));
                    transacciones.add(transaccion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transacciones;
    }
}


