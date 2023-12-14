package Modelo.DAO;

import Modelo.Conexion.Conexion;
import Modelo.Entidades.Acciones;
import Modelo.Entidades.Clientes;
import Modelo.Tipos.TipoOperacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccionesDAO {

    private ArrayList<Acciones> listaAcciones;
    private Conexion conexion;

    public AccionesDAO(Conexion conexion) {
        this.conexion = conexion;
        listaAcciones = new ArrayList<Acciones>();
    }


    /*create table Acciones(
id int auto_increment primary key,
idCliente int,
NombreCliente varchar(255),
Apellido Varchar(255),
numeroAcciones int,
nombreEmpresaVarchar varchar(255));*/


    public void agregarAcciones(Clientes c, String nombreEmpresa, TipoOperacion tipoOperacion){
        String sql = "INSERT INTO Acciones (idCliente, NombreCliente, Apellido, numeroAcciones, nombreEmpresa, TipoOperacion) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.getConnection().prepareStatement(sql)) {
            statement.setInt(1, c.getId());
            statement.setString(2, c.getNombre());
            statement.setString(3, c.getApellido());
            statement.setInt(4, c.getNumeroAcciones());
            statement.setString(5, nombreEmpresa);
            statement.setString(6, tipoOperacion.toString());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Acciones> buscarAccionesPorID(int idCliente){
        String sql = "select * from Acciones where idCliente = ?";
        try (PreparedStatement statement = conexion.getConnection().prepareStatement(sql)) {
            statement.setInt(1, idCliente);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                if(resultSet.getInt("idCliente")==idCliente){
                Acciones a = new Acciones(
                        resultSet.getInt("idCliente"),
                        resultSet.getString("NombreCliente"),
                        resultSet.getString("Apellido"),
                        resultSet.getInt("numeroAcciones"),
                        resultSet.getString("nombreEmpresa")
                );
                listaAcciones.add(a);
                }
            }
            return listaAcciones;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
