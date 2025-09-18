package com.example.back_vallespejo.models.dto;

// Nota Rodrigo para crear item de material a partir de un Material
public class AddItemMaterialRequest {
    private Long materialId;
    private Integer cantidad;

    public Long getMaterialId() { return materialId; }
    public void setMaterialId(Long materialId) { this.materialId = materialId; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}
