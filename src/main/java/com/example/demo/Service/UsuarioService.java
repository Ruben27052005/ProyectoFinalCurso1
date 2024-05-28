package com.example.demo.Service;

import com.example.demo.Database.ConexionBD;
import com.example.demo.Entity.Usuarios;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/usuario")

public class UsuarioService {

  static ConexionBD connection = new ConexionBD();

    // Método para crear un nuevo usuario en la base de datos
    public static void crearUsuario(Usuarios usuario) throws SQLException {
        // Obtiene el nuevo ID disponible para el usuario

        String insertSql = "INSERT INTO Usuarios ( nombre, edad, email) VALUES (?, ?, ?)";
            // Prepara la sentencia SQL para insertar el nuevo usuario en la base de datos
            PreparedStatement insertStatement = connection.conectar().prepareStatement(insertSql);
            insertStatement.setString(1, usuario.getNombre());
            insertStatement.setInt(2, usuario.getEdad());
            insertStatement.setString(3, usuario.getEmail());
            insertStatement.executeUpdate(); // Ejecuta la sentencia SQL para insertar el nuevo usuario
            System.out.println("Usuario creado exitosamente.");
    }

    // Método para obtener todos los usuarios almacenados en la base de datos
    public List<Usuarios> obtenerUsuarios() throws SQLException{
        List<Usuarios> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios";

            // Prepara la sentencia SQL para obtener todos los usuarios de la base de datos
            PreparedStatement statement = ConexionBD.conectar().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(); // Ejecuta la consulta y obtiene el resultado
            // Itera sobre el resultado y crea objetos Usuario para cada registro
            while (resultSet.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setId(resultSet.getInt("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setEdad(resultSet.getInt("edad"));
                usuario.setEmail(resultSet.getString("email"));
                usuarios.add(usuario); // Agrega el usuario a la lista de usuarios
            }

        return usuarios;
    }




    // Método para obtener el nuevo ID disponible para un nuevo usuario


    // Método para actualizar los datos de un usuario existente en la base de datos
    public static void actualizarUsuario(Usuarios usuario) throws SQLException {
        String sql = "UPDATE Usuarios SET nombre = ?, edad = ?, email = ? WHERE id = ?";

            // Prepara la sentencia SQL para actualizar los datos del usuario
            PreparedStatement statement = ConexionBD.conectar().prepareStatement(sql);
            statement.setString(1, usuario.getNombre());
            statement.setInt(2, usuario.getEdad());
            statement.setString(3, usuario.getEmail());
            statement.setInt(4, usuario.getId());
            statement.executeUpdate(); // Ejecuta la sentencia SQL para actualizar los datos del usuario
            System.out.println("Usuario actualizado exitosamente.");

    }

    public Usuarios buscar(int id) throws SQLException {
        Statement consulta = connection.conectar().createStatement();
        ResultSet rs = consulta.executeQuery("SELECT * FROM usuarios WHERE id = " + id);
        Usuarios usuario = null;
        if (rs.next()) {
            usuario = new Usuarios(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("edad"),
                    rs.getString("email")
            );
        }
        rs.close();
        consulta.close();
        return usuario;
    }

    // Método para eliminar un usuario existente de la base de datos
    public Object eliminarUsuario(int id) throws SQLException{
        String deleteSql = "DELETE FROM Usuarios WHERE id = ?";

            // Prepara la sentencia SQL para eliminar el usuario de la base de datos
            PreparedStatement deleteStatement = connection.conectar().prepareStatement(deleteSql);
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate(); // Ejecuta la sentencia SQL para eliminar el usuario
            System.out.println("Usuario eliminado exitosamente.");

        return null;
    }


}
