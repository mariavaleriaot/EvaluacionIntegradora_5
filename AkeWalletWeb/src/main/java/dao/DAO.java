package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase abstracta que proporciona métodos comunes para interactuar con la base de datos.
 */
public abstract class DAO {

    private Connection conn;
    private ResultSet resultSet;
    private Statement stmt;

    /**
     * Método para conectar a la base de datos.
     */
    protected void conectarDb() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String stringConnection = "jdbc:mysql://localhost:3306/alke_wallet_E5";
                String user = "mariavaleriaot";
                String pass = "Zapallo_03";
                conn = DriverManager.getConnection(stringConnection, user, pass);
                System.out.println("Conectado a la Base de Datos");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: Driver no encontrado");
        } catch (SQLException ex) {
            System.out.println("Fallo:");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @return La conexión a la base de datos.
     * @throws SQLException Si ocurre algún error al conectar a la base de datos.
     */
    protected Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conectarDb();
        }
        return conn;
    }

    /**
     * Ejecuta una consulta SQL y devuelve un ResultSet con los resultados.
     *
     * @param sql La consulta SQL a ejecutar.
     * @return Un ResultSet con los resultados de la consulta.
     */
    protected ResultSet consultar(String sql) {
        try {
            conectarDb();
            this.stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            return resultSet;
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            return null;
        }
    }

    /**
     * Ejecuta una instrucción SQL (INSERT, UPDATE, DELETE) y devuelve la cantidad de registros modificados.
     *
     * @param sql La instrucción SQL a ejecutar.
     * @return La cantidad de registros modificados por la instrucción.
     */
    protected int ejecutarSql(String sql) {
        try {
            conectarDb();
            this.stmt = conn.createStatement();
            int regModificados = stmt.executeUpdate(sql);
            return regModificados;
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            return 0;
        }
    }

    /**
     * Cierra la conexión a la base de datos, el statement y el resultSet.
     */
    protected void close() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.print("Error Cerrando" + e.getMessage());
        }
    }
}
