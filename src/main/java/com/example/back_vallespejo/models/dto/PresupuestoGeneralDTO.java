package com.example.back_vallespejo.models.dto;

public class PresupuestoGeneralDTO {
    private Long id;
    private String descripcion;
    private Integer cantidadActividades;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Integer getCantidadActividades() { return cantidadActividades; }
    public void setCantidadActividades(Integer cantidadActividades) { this.cantidadActividades = cantidadActividades; }
}
