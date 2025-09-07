package com.example.cerveza.Modelo;

public class Marca {
    private int idMarca;
    private int idFabricante;
    private String nombre;
    private String descripcion;

    public Marca() {
    }

    public Marca(int idMarca, int idFabricante, String nombre, String descripcion) {
        this.idMarca = idMarca;
        this.idFabricante = idFabricante;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(int idFabricante) {
        this.idFabricante = idFabricante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
