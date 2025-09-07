package com.example.cerveza.Modelo;

public class Produccion {
    private int idProduccion;
    private int idCerveza;
    private String fechaProduccion;
    private int cantidad;

    public Produccion() {
    }

    public Produccion(int idProduccion, int idCerveza, String fechaProduccion, int cantidad) {
        this.idProduccion = idProduccion;
        this.idCerveza = idCerveza;
        this.fechaProduccion = fechaProduccion;
        this.cantidad = cantidad;
    }

    public int getIdProduccion() {
        return idProduccion;
    }

    public void setIdProduccion(int idProduccion) {
        this.idProduccion = idProduccion;
    }

    public int getIdCerveza() {
        return idCerveza;
    }

    public void setIdCerveza(int idCerveza) {
        this.idCerveza = idCerveza;
    }

    public String getFechaProduccion() {
        return fechaProduccion;
    }

    public void setFechaProduccion(String fechaProduccion) {
        this.fechaProduccion = fechaProduccion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
