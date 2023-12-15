package Modelo.Entidades;

public class Acciones {

    private int idCliente;
    private String Nombrecliente;
    private String Apellido;
    private int numeroAcciones;
    private String nombreEmpresa;

    private int totalAcciones;

    public Acciones() {
    }

    public Acciones(int idCliente, String Nombrecliente, String Apellido, int numeroAcciones, String nombreEmpresa, int totalAcciones) {
        this.idCliente = idCliente;
        this.Nombrecliente = Nombrecliente;
        this.Apellido = Apellido;
        this.numeroAcciones = numeroAcciones;
        this.nombreEmpresa = nombreEmpresa;
        this.totalAcciones=totalAcciones;
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
