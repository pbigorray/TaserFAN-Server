package model;


import java.io.Serializable;
import java.util.Date;

public class Vehiculo {
    private String matricula,marca,descripcion,color,estado;
    private String fechaAdq;
    private double precioHora;
    private int bateria;
    private String tipoCarnet;
    private Tipo tipo;

    public Vehiculo(String matricula, String marca, String descripcion, int bateria, String tipoCarnet, String color, String estado, String fechaAdq, double precioHora, Tipo tipo) {
        this.matricula = matricula;
        this.marca = marca;
        this.descripcion = descripcion;
        this.bateria = bateria;
        this.tipoCarnet = tipoCarnet;
        this.color = color;
        this.estado = estado;
        this.fechaAdq = fechaAdq;
        this.precioHora = precioHora;
        this.tipo = tipo;
    }

    public Vehiculo(String matricula, String marca, String color, String estado, Tipo tipo) {
        this.matricula = matricula;
        this.marca = marca;
        this.color = color;
        this.estado = estado;
        this.tipo=tipo;
    }


    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getBateria() {
        return bateria;
    }

    public  String getTipoCarnet() {
        return tipoCarnet;
    }

    public String getColor() {
        return color;
    }

    public String getEstado() {
        return estado;
    }

    public String getFechaAdq() {
        return fechaAdq;
    }

    public double getPrecioHora() {
        return precioHora;
    }


}
