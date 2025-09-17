package com.example.back_vallespejo.models.dto;

// Usado para crear item de equipo o mano de obra a partir de un TD_Presupuestos
public class AddItemCatalogRequest {
    private Long tdPresupuestoId;
    private Double cantidad; // opcional (default 1.0)
    private Double precioOverride; // opcional para permitir override (si se desea)

    public Long getTdPresupuestoId() { return tdPresupuestoId; }
    public void setTdPresupuestoId(Long tdPresupuestoId) { this.tdPresupuestoId = tdPresupuestoId; }
    public Double getCantidad() { return cantidad; }
    public void setCantidad(Double cantidad) { this.cantidad = cantidad; }
    public Double getPrecioOverride() { return precioOverride; }
    public void setPrecioOverride(Double precioOverride) { this.precioOverride = precioOverride; }
}
