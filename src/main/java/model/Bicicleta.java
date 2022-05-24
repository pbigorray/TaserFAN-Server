package model;

import java.util.Date;

public class Bicicleta extends Vehiculo{
    private String tipo;

    public Bicicleta(String matricula, String marca, String color, String estado,Tipo tipo) {
        super(matricula, marca, color, estado,tipo);
    }

    public Bicicleta(String matricula, String marca, String descripcion, int bateria, String tipoCarnet, String color, String estado, String fechaAdq, double precioHora, Tipo tipo, String tipo1) {
        super(matricula, marca, descripcion, bateria, tipoCarnet, color, estado, fechaAdq, precioHora, tipo);
        this.tipo = tipo1;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
//    public Bicicleta(String matricula, String marca, String descripcion, String bateria, String tipoCarnet, String color, String estado, Date fechaAdq, double precioHora, String tipo) {
//        super(matricula, marca, descripcion, bateria, tipoCarnet, color, estado, fechaAdq, precioHora);
//        this.tipo = tipo;
//    }
}