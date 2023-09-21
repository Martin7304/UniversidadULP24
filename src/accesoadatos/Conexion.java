
package accesoadatos;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
    private static final String URL = "jdbc:mariadb://localhost/";
    private static final String DB = "UniversidadULP24";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";
    private static Connection conexion;
    
    private Conexion() {} // Constructor vacio.
    
    public static Connection getConexion() {
        if (conexion == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                conexion = DriverManager.getConnection(URL + DB, USUARIO, PASSWORD);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "El driver de conexión no fue encontrado. " + ex.getMessage());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "La conexión con la base de datos ha fallado. " + ex.getMessage());
            }
        }
        return conexion;
    }
}
