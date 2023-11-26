package Modelo.DAO;

import Modelo.Entidades.Cuenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CuentaDAO {
    private Connection conexion;

    public CuentaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void actualizarSaldo(Cuenta cuenta) throws SQLException {
        String sql = "UPDATE Cuenta SET saldoDinero = ?, saldoAccion = ? WHERE idCliente = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setDouble(1, cuenta.getSaldoDinero());
            statement.setDouble(2, cuenta.getSaldoAccion());
            statement.setInt(3, cuenta.getIdCliente());
            statement.executeUpdate();
        }
    }

    public Cuenta obtenerCuentaPorIdCliente(int idCliente) throws SQLException {
        String sql = "SELECT * FROM Cuenta WHERE idCliente = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idCliente);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Cuenta(
                            resultSet.getInt("IdCuenta"),
                            resultSet.getInt("idCliente"),
                            resultSet.getDouble("saldoDinero"),
                            resultSet.getDouble("saldoAccion")
                    );
                }
            }
        }
        return null;
    }

    // Otros métodos CRUD para Cuenta
}
