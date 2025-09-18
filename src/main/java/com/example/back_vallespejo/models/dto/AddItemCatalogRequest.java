package com.example.back_vallespejo.models.dto;

// Nota Rodrigo para crear item de equipo o mano de obra a partir de un TD_Presupuestos
public class AddItemCatalogRequest {
    private Long tdPresupuestoId;
    private Double cantidad; 
    private Double precioOverride; // Permite sobreescribir el precio unitario si es necesario

    public Long getTdPresupuestoId() { return tdPresupuestoId; }
    public void setTdPresupuestoId(Long tdPresupuestoId) { this.tdPresupuestoId = tdPresupuestoId; }
    public Double getCantidad() { return cantidad; }
    public void setCantidad(Double cantidad) { this.cantidad = cantidad; }
    public Double getPrecioOverride() { return precioOverride; }
    public void setPrecioOverride(Double precioOverride) { this.precioOverride = precioOverride; }
}
