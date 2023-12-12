package Modelo.DAO;

import Modelo.Conexion.Conexion;
import Modelo.Entidades.Clientes;
import Modelo.Entidades.Empresas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmpresaDAO {


    private ArrayList<Empresas> listaEmpresas;
    private Conexion conexion;

    public EmpresaDAO(Conexion conexion){
        this.conexion=conexion;
        listaEmpresas= new ArrayList<Empresas>();

    }
    public ArrayList<Empresas> listaEmpresas(){
        String sql = "select * from empresas";
        try {
            Statement stmt = conexion.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Empresas c = new Empresas(rs.getInt("idEmpresa"), rs.getString("nombreEmpresa"), rs.getString("ticker"));
                    listaEmpresas.add(c);

            }
        } catch (SQLException e) {
            e.getLocalizedMessage();
        }

        return listaEmpresas;
    }
}
