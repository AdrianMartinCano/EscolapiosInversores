package Modelo.DAO;

import Modelo.Conexion.Conexion;
import Modelo.Entidades.Clientes;

import javax.xml.transform.Result;
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

    public void actualizarAcciones(int id, int cantidad) {
        String sql = "UPDATE clientes SET NumeroAcciones = NumeroAcciones + ? WHERE id = ?";
        PreparedStatement statement = null;
        try {
            statement = conexion.getConnection().prepareStatement(sql);
            statement.setInt(1, cantidad);
            statement.setInt(2, id); // Suponiendo que 'id' es la clave primaria de la tabla
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Actualización exitosa. Filas afectadas: " + filasAfectadas);
            } else {
                System.out.println("No se actualizó ninguna fila. Puede que no haya una fila con el ID proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime la pila de errores para obtener más información
        }
    }

    public void cerrarConexion(){
        conexion.cerrarConexion();
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

    public Clientes seleccionarCliente(int id){
        String sql = "select * from clientes where id = ?";
        try (PreparedStatement statement = conexion.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){

                Clientes c = new Clientes(resultSet.getInt("id"), resultSet.getString("nombre"),
                        resultSet.getString("apellido"), resultSet.getString("DNI"), resultSet.getString("numeroCuenta"),
                        resultSet.getInt("NumeroAcciones"), resultSet.getString("usuario"), resultSet.getString("contraseña"),
                        false);
                return c;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
