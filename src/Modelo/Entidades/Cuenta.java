package Modelo.Entidades;

public class Cuenta {

    private int idCuenta;

    private int idCliente;

    private double saldoDinero;
    private double saldoAccion;

    public Cuenta(int idCuenta, int idCliente, double saldoDinero, double saldoAccion) {
        this.idCuenta = idCuenta;
        this.idCliente = idCliente;
        this.saldoDinero = saldoDinero;
        this.saldoAccion = saldoAccion;
    }

    public Cuenta() {
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getSaldoDinero() {
        return saldoDinero;
    }

    public void setSaldoDinero(double saldoDinero) {
        this.saldoDinero = saldoDinero;
    }

    public double getSaldoAccion() {
        return saldoAccion;
    }

    public void setSaldoAccion(double saldoAccion) {
        this.saldoAccion = saldoAccion;
    }
}
