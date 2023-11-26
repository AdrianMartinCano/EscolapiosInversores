package Modelo.DAO;

import Modelo.Entidades.Operador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OperadorDAO {
    private Connection conexion;

    public OperadorDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarOperador(Operador operador) throws SQLException {
        String sql = "INSERT INTO Operador (esAdmin) VALUES (?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setBoolean(1, operador.isEsAdmin());
            statement.executeUpdate();
        }
    }

    // Otros métodos CRUD para Operador
}
