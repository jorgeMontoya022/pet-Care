package petcare.com.petcare.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Datos por defecto de Oracle Express Edition
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "system";
    private static final String PASS = "1097032932";

    public static Connection getConexion() {
        try {
            // Cargar el driver de Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
            return null;
        }
    }
}