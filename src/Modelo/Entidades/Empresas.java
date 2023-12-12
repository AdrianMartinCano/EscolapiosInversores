package Modelo.Entidades;

/*
* CREATE TABLE Empresas (
   idEmpresa INT AUTO_INCREMENT PRIMARY KEY,
   nombreEmpresa VARCHAR(255) NOT NULL,
   ticker VARCHAR(10) NOT NULL
);
* */
public class Empresas {

    private int idEmpresa;
    private String nombreEmpresa;
    private String ticker;

    public Empresas() {
    }

    public Empresas(int idEmpresa, String nombreEmpresa, String ticker) {
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.ticker = ticker;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
