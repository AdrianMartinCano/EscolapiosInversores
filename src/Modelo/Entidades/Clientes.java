package Modelo.Entidades;

public class Clientes {

    private int id;
    private String nombre;
    private String apellido;
    private String DNI;
    private String numeroCuenta;
    private int NumeroAcciones;
    private String usuario;
    private String contraseña;
    private boolean esAdmin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public int getNumeroAcciones() {
        return NumeroAcciones;
    }

    public void setNumeroAcciones(int numeroAcciones) {
        NumeroAcciones = numeroAcciones;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Clientes() {
    }
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Apellido: " + apellido + ", DNI: " + DNI;
    }

    public Clientes(int id, String nombre, String apellido, String DNI, String numeroCuenta, int numeroAcciones, String usuario, String contraseña, boolean esAdmin) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.numeroCuenta = numeroCuenta;
        this.NumeroAcciones = numeroAcciones;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.esAdmin = esAdmin;
    }
}
