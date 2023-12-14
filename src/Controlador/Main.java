package Controlador;

import Modelo.Conexion.Conexion;
import Modelo.DAO.AccionesDAO;
import Modelo.DAO.TransaccionesDAO;
import Modelo.Entidades.Clientes;
import Modelo.Entidades.Transacciones;
import Vista.Login;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
     Login l = new Login();

      String DB_URL = "jdbc:mysql://localhost/escolapios";
      String USER = "root";
      String PASS = "root";
     // Conexion conexion = new Conexion(DB_URL, USER, PASS);
       // AccionesDAO accionesDAO = new AccionesDAO(conexion);
        //System.out.println(accionesDAO.buscarAccionesPorID(5).size());
        //Clientes c = new Clientes(1, "Juan", "Pérez", "AAAA", "AAAA", 10, "a", "a", false);
        //accionesDAO.agregarAcciones(c, "EmpresaZ");

    }
}