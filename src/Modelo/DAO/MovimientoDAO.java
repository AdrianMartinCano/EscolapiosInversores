package Modelo.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modelo.Entidades.Movientos;

public class MovimientoDAO {
    private Connection conexion;

    public MovimientoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarMovimiento(Movientos movimiento) throws SQLException {
        String sql = "INSERT INTO Movimientos (idCliente, TipoMovimiento, Fecha, Cantidad) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, movimiento.getIdCliente());
            statement.setString(2, movimiento.getTipoMovimiento().toString());
            statement.setDate(3, (Date) movimiento.getFecha());
            statement.setInt(4, movimiento.getCantidad());
            statement.executeUpdate();
        }
    }

    // Otros métodos CRUD para Movimiento
}

