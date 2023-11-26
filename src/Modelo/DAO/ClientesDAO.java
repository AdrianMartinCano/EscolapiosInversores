package Modelo.DAO;

import Modelo.Entidades.Clientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAO {
    private Connection conexion;

    public ClientesDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarCliente(Clientes cliente) throws SQLException {
        String sql = "INSERT INTO Clientes (nombre, apellido, DNI, usuario, contraseña, esAdmin) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getDNI());
            statement.setString(4, cliente.getUsuario());
            statement.setString(5, cliente.getContraseña());
            statement.setBoolean(6, cliente.isEsAdmin());
            statement.executeUpdate();
        }
    }

    public Clientes obtenerClientePorId(int idCliente) throws SQLException {
        String sql = "SELECT * FROM Clientes WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idCliente);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Clientes(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getString("DNI"),
                            resultSet.getString("usuario"),
                            resultSet.getString("contraseña"),
                            resultSet.getBoolean("esAdmin")
                    );
                }
            }
        }
        return null;
    }

    // Otros métodos CRUD para Cliente
}
