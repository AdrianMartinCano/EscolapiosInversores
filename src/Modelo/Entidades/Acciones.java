package Modelo.Entidades;

public class Acciones {

    /*create table Acciones(
id int auto_increment primary key,
idCliente int,
NombreCliente varchar(255),
Apellido Varchar(255),
numeroAcciones int,
nombreEmpresaVarchar varchar(255));*/

    private int idCliente;
    private String Nombrecliente;
    private String Apellido;
    private int numeroAcciones;
    private String nombreEmpresa;

    public Acciones() {
    }

    public Acciones(int idCliente, String nombrecliente, String apellido, int numeroAcciones, String nombreEmpresa) {
        this.idCliente = idCliente;
        Nombrecliente = nombrecliente;
        Apellido = apellido;
        this.numeroAcciones = numeroAcciones;
        this.nombreEmpresa = nombreEmpresa;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombrecliente() {
        return Nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        Nombrecliente = nombrecliente;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
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

    @Override
    public String toString() {
        return "Acciones{" +
                "idCliente=" + idCliente +
                ", Nombrecliente='" + Nombrecliente + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", numeroAcciones=" + numeroAcciones +
                ", nombreEmpresa='" + nombreEmpresa + '\'' +
                '}';
    }
}
