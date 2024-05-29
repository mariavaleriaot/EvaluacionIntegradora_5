package daoImpl;

import dao.DAO;
import dao.TransaccionDAO;
import model.Transaccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TransaccionDAOImpl extends DAO implements TransaccionDAO {

	 @Override
	    public void realizarDeposito(String nombreUsuario, double monto) throws SQLException {
	        Connection conn = getConnection();
	        try {
	            conn.setAutoCommit(false);

	            // Actualizar saldo del usuario
	            String updateSaldo = "UPDATE usuario SET saldo = saldo + ? WHERE nombre = ?";
	            try (PreparedStatement stmt = conn.prepareStatement(updateSaldo)) {
	                stmt.setDouble(1, monto);
	                stmt.setString(2, nombreUsuario);
	                stmt.executeUpdate();
	            }

	            // Obtener id del usuario
	            String queryUsuarioId = "SELECT id FROM usuario WHERE nombre = ?";
	            int usuarioId = 0;
	            try (PreparedStatement stmt = conn.prepareStatement(queryUsuarioId)) {
	                stmt.setString(1, nombreUsuario);
	                try (ResultSet rs = stmt.executeQuery()) {
	                    if (rs.next()) {
	                        usuarioId = rs.getInt("id");
	                    }
	                }
	            }

	            // Registrar transacción
	            String insertTransaccion = "INSERT INTO transaccion (usuario_id, tipo, monto) VALUES (?, ?, ?)";
	            try (PreparedStatement stmt = conn.prepareStatement(insertTransaccion)) {
	                stmt.setInt(1, usuarioId);
	                stmt.setString(2, "deposito");
	                stmt.setDouble(3, monto);
	                stmt.executeUpdate();
	            }

	            conn.commit();
	        } catch (SQLException e) {
	            conn.rollback();
	            throw e;
	        } finally {
	            conn.setAutoCommit(true);
	            close();
	        }
	    }

	    @Override
	    public void realizarRetiro(String nombreUsuario, double monto) throws SQLException {
	        Connection conn = getConnection();
	        try {
	            conn.setAutoCommit(false);

	            // Actualizar saldo del usuario
	            String updateSaldo = "UPDATE usuario SET saldo = saldo - ? WHERE nombre = ?";
	            try (PreparedStatement stmt = conn.prepareStatement(updateSaldo)) {
	                stmt.setDouble(1, monto);
	                stmt.setString(2, nombreUsuario);
	                stmt.executeUpdate();
	            }

	            // Obtener id del usuario
	            String queryUsuarioId = "SELECT id FROM usuario WHERE nombre = ?";
	            int usuarioId = 0;
	            try (PreparedStatement stmt = conn.prepareStatement(queryUsuarioId)) {
	                stmt.setString(1, nombreUsuario);
	                try (ResultSet rs = stmt.executeQuery()) {
	                    if (rs.next()) {
	                        usuarioId = rs.getInt("id");
	                    }
	                }
	            }

	            // Registrar transacción
	            String insertTransaccion = "INSERT INTO transaccion (usuario_id, tipo, monto) VALUES (?, ?, ?)";
	            try (PreparedStatement stmt = conn.prepareStatement(insertTransaccion)) {
	                stmt.setInt(1, usuarioId);
	                stmt.setString(2, "retiro");
	                stmt.setDouble(3, monto);
	                stmt.executeUpdate();
	            }

	            conn.commit();
	        } catch (SQLException e) {
	            conn.rollback();
	            throw e;
	        } finally {
	            conn.setAutoCommit(true);
	            close();
	        }
	    }

		@Override
		public List<Transaccion> obtenerTransacciones(int usuarioId) {
			// TODO Auto-generated method stub
			return null;
		}
	}