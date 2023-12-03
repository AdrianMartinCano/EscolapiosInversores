package Modelo.DAO;

import Modelo.Conexion.Conexion;
import Modelo.Entidades.Clientes;

import java.sql.*;
import java.util.ArrayList;

public class ClientesDAO {

    private ArrayList<Clientes> listaClientes;
    private Conexion conexion;

    public ClientesDAO(Conexion conexion) {
        this.conexion = conexion;
        listaClientes = new ArrayList<Clientes>();
    }

    public void agregarCliente(Clientes cliente) throws SQLException {
        String sql = "INSERT INTO Clientes (nombre, apellido, DNI, usuario, contraseña, esAdmin) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.getConnection().prepareStatement(sql)) {
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getDNI());
            statement.setString(4, cliente.getUsuario());
            statement.setString(5, cliente.getContraseña());
            statement.setBoolean(6, cliente.isEsAdmin());
            statement.executeUpdate();
        }
    }

    public ArrayList<Clientes> listaClientes() {
        String sql = "select * from clientes";
        try {
            Statement stmt = conexion.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (!rs.getBoolean("esAdmin")) {
                    Clientes c = new Clientes(rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("DNI"),
                            rs.getString("numeroCuenta"),
                            rs.getInt("NumeroAcciones"),
                            rs.getString("usuario"),
                            rs.getString("contraseña").toString(),
                            rs.getBoolean("esAdmin"));
                    listaClientes.add(c);
                }
            }
        } catch (SQLException e) {
            e.getLocalizedMessage();
        }
        return listaClientes;
    }
}
