package com.example.cerveza.Modelo;

public class Envase {
    private int idEnvase;
    private String nombre;
    private String material;
    private String capacidad;
    private String descripcion;

    public Envase() {

    }

    public Envase(int idEnvase, String nombre, String material, String capacidad, String descripcion) {
        this.idEnvase = idEnvase;
        this.nombre = nombre;
        this.material = material;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
    }

    public int getIdEnvase() {
        return idEnvase;
    }

    public void setIdEnvase(int idEnvase) {
        this.idEnvase = idEnvase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
