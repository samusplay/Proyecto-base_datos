
package com.mycompany.proyect_entrada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/pruebas"; // Cambia "localhost" si es necesario
    private static final String USUARIO = "root"; // Cambia por tu usuario MySQL
    private static final String CONTRASEÑA = "MyCode2024@"; // Cambia por tu contraseña MySQL

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }
}



