package Modelo.Entidades;

import Modelo.Tipos.TipoOperacion;

import java.util.Date;

public class Operacion {
    private int idOperacion;
    private int idCliente;
    private int idoperador;
    private TipoOperacion tipooperacion;
    private Date FechaOperacion;
    private int CantidadAcciones;
    private double ValorAccion;
    private double Monto;

    public Operacion() {
    }

    public Operacion(int idOperacion, int idCliente, int idoperador, TipoOperacion tipooperacion, Date fechaOperacion, int cantidadAcciones, double valorAccion, double monto) {
        this.idOperacion = idOperacion;
        this.idCliente = idCliente;
        this.idoperador = idoperador;
        this.tipooperacion = tipooperacion;
        FechaOperacion = fechaOperacion;
        CantidadAcciones = cantidadAcciones;
        ValorAccion = valorAccion;
        Monto = monto;
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdoperador() {
        return idoperador;
    }

    public void setIdoperador(int idoperador) {
        this.idoperador = idoperador;
    }

    public TipoOperacion getTipooperacion() {
        return tipooperacion;
    }

    public void setTipooperacion(TipoOperacion tipooperacion) {
        this.tipooperacion = tipooperacion;
    }

    public Date getFechaOperacion() {
        return FechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        FechaOperacion = fechaOperacion;
    }

    public int getCantidadAcciones() {
        return CantidadAcciones;
    }

    public void setCantidadAcciones(int cantidadAcciones) {
        CantidadAcciones = cantidadAcciones;
    }

    public double getValorAccion() {
        return ValorAccion;
    }

    public void setValorAccion(double valorAccion) {
        ValorAccion = valorAccion;
    }

    public double getMonto() {
        return Monto;
    }

    public void setMonto(double monto) {
        Monto = monto;
    }
}
