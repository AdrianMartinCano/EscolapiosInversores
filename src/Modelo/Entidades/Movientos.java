package Modelo.Entidades;

import Modelo.Tipos.TipoMovimiento;

import java.util.Date;

public class Movientos {

    private int id;
    private int idCliente;
    private TipoMovimiento tipoMovimiento;
    private Date Fecha;
    private int Cantidad;

    public Movientos() {
    }

    public Movientos(int id, int idCliente, TipoMovimiento tipoMovimiento, Date fecha, int cantidad) {
        this.id = id;
        this.idCliente = idCliente;
        this.tipoMovimiento = tipoMovimiento;
        Fecha = fecha;
        Cantidad = cantidad;
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

    public TipoMovimiento getTipoMovimiento() { return tipoMovimiento; }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }
}
