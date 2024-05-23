package com.example.demo.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


 //La clase ConexionBD proporciona un método estático para establecer una conexión con una base de datos MySQL.

public class ConexionBD {
    // URL de conexión a la base de datos MySQL
    private static final String URL = "jdbc:mysql://localhost:3307/crudtfg";

    // Nombre de usuario de la base de datos MySQL
    private static final String USER = "root";

    // Contraseña de la base de datos MySQL
    private static final String PASSWORD = "";


    //Establece una conexión con la base de datos MySQL.
    //@return La conexión establecida con la base de datos, o null si no se pudo establecer la conexión.

    public static Connection conectar() {
        Connection connection = null;
        try {
            // Intenta establecer la conexión con la base de datos utilizando la URL, usuario y contraseña proporcionados
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            // Captura cualquier excepción que ocurra al intentar establecer la conexión
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return connection; // Devuelve la conexión establecida (o null si no se pudo establecer la conexión)
    }
}

