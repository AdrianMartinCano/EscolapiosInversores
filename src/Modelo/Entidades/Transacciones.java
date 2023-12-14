package Modelo.Entidades;

import Modelo.Tipos.TipoOperacion;

public class Transacciones {

    private int id;
    private int idCliente;
    private String NombreCliente;
    private String Apellido;
    private TipoOperacion tipoOperacion;
    private int numeroAcciones;
    private String nombreEmpresa;


    @Override
    public String toString() {
        return "Transacciones{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", NombreCliente='" + NombreCliente + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", tipoOperacion=" + tipoOperacion +
                ", numeroAcciones=" + numeroAcciones +
                ", nombreEmpresa='" + nombreEmpresa + '\'' +
                '}';
    }

    public Transacciones() {
    }

    public Transacciones( int idCliente, String nombreCliente, String apellido, TipoOperacion tipoOperacion, int numeroAcciones, String nombreEmpresa) {
        this.idCliente = idCliente;
        NombreCliente = nombreCliente;
        Apellido = apellido;
        this.tipoOperacion = tipoOperacion;
        this.numeroAcciones = numeroAcciones;
        this.nombreEmpresa = nombreEmpresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        NombreCliente = nombreCliente;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public int getNumeroAcciones() {
        return numeroAcciones;
    }

    public void setNumeroAcciones(int numeroAcciones) {
        this.numeroAcciones = numeroAcciones;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
}
