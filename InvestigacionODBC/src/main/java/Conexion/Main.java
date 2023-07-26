package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        String sqlUser = "pruebaODBC";
        String sqlPassword = "asdf1234";
        String connectURL = "jdbc:odbc://DESKTOP-O1BOIEC:1433;databaseName=SEMESTRAL";
        
        Connection conexion = null;
        PreparedStatement statement;
        try {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexion = DriverManager.getConnection(connectURL, sqlUser, sqlPassword);
            
            System.out.println("Conexión establecida!");
            
            statement = conexion.prepareStatement("SELECT * FROM PROVEEDORES");
            ResultSet resultados = statement.executeQuery();
            
            while (resultados.next()) {
                String nombre = resultados.getString("nombre");
                String ciudad = resultados.getString("ciudad");
                String pais = resultados.getString("pais");
                System.out.println("nombre: " + nombre + ", ciudad: " + ciudad + ", pais: " + pais);
            }
            
        } catch (SQLException ex) {
            System.err.println("No se pudo establecer la conexión con la base de datos");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch ( ClassNotFoundException ex) {
            System.err.println("No se pudo cargar el controlador JDBC-ODBC");
        }
    }
}
