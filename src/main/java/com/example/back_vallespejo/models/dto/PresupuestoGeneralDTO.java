package com.example.back_vallespejo.models.dto;

public class PresupuestoGeneralDTO {
    private Long id;
    private String descripcion;
    private Integer cantidadActividades;
    private Double presupuestoEstimado;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Integer getCantidadActividades() { return cantidadActividades; }
    public void setCantidadActividades(Integer cantidadActividades) { this.cantidadActividades = cantidadActividades; }
    public Double getPresupuestoEstimado() { return presupuestoEstimado; }
    public void setPresupuestoEstimado(Double presupuestoEstimado) { this.presupuestoEstimado = presupuestoEstimado; }
}
