package Modelo.DAO;

import Modelo.Conexion.Conexion;
import Modelo.Entidades.Clientes;
import Modelo.Entidades.Transacciones;
import Modelo.Tipos.TipoOperacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TransaccionesDAO {

    private ArrayList<Transacciones> listaTransacciones;
    private Conexion conexion;

    public TransaccionesDAO(Conexion conexion) {
        this.conexion = conexion;
        listaTransacciones = new ArrayList<Transacciones>();
    }


    public  ArrayList<Transacciones> listaTransacciones() {
        String sql = "select * from transacciones";
        try {
            Statement stmt = conexion.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                    Transacciones t = new Transacciones(
                            rs.getInt("idCliente"),
                            rs.getString("NombreCliente"),
                            rs.getString("Apellido"),
                            TipoOperacion.valueOf(rs.getString("TipoOperacion")),
                            rs.getInt("numeroAcciones"),
                            rs.getString("nombreEmpresa")
                    );
                   listaTransacciones.add(t);;


            }
        } catch (SQLException e) {
            e.getLocalizedMessage();
        }
        return listaTransacciones;

    }

    public void actualizarTransaccionCompra(Clientes c , String nombreEmpresa){
        String sql = "INSERT INTO Transacciones (idCliente, NombreCliente, Apellido, TipoOperacion, numeroAcciones, nombreEmpresa) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement preparedStatement = conexion.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, c.getId());
            preparedStatement.setString(2, c.getNombre());
            preparedStatement.setString(3,c.getApellido());
            preparedStatement.setString(4, TipoOperacion.valueOf("Compra").toString() );
            preparedStatement.setInt(5, c.getNumeroAcciones());
            preparedStatement.setString(6,nombreEmpresa);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        System.out.println("Transacción realizada");

    }
    public void actualizarTransaccionVenta(Clientes c , String nombreEmpresa){
        String sql = "INSERT INTO Transacciones (idCliente, NombreCliente, Apellido, TipoOperacion, numeroAcciones, nombreEmpresa) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try{
        PreparedStatement preparedStatement = conexion.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, c.getId());
        preparedStatement.setString(2, c.getNombre());
        preparedStatement.setString(3,c.getApellido());
        preparedStatement.setString(4, TipoOperacion.valueOf("Venta").toString() );
        preparedStatement.setInt(5, c.getNumeroAcciones());
        preparedStatement.setString(6,nombreEmpresa);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        System.out.println("Transacción realizada");

    }
}
