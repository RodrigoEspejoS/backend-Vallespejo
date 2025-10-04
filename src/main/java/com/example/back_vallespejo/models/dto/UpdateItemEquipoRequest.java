package com.example.back_vallespejo.models.dto;

public class UpdateItemEquipoRequest {
    private String codigo;
    private String descripcion;
    private String unidad;
    private Double cantidad;
    private Double cuadrilla;
    private Double precioUnitario;

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCuadrilla() {
        return cuadrilla;
    }

    public void setCuadrilla(Double cuadrilla) {
        this.cuadrilla = cuadrilla;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
