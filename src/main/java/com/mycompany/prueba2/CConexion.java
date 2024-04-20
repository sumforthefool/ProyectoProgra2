package com.mycompany.prueba2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CConexion {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USUARIO = "C##PRUEBA";
    private static final String CONTRASENA = "123";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el controlador JDBC de Oracle");
            ex.printStackTrace();
        }
        Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        if (conexion != null) {
            System.out.println("Conexión exitosa a la base de datos Oracle");
        } else {
            System.out.println("No se pudo establecer la conexión a la base de datos Oracle");
        }
        return conexion;
    }
}
