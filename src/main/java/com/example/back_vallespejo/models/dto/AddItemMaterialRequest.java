package com.example.back_vallespejo.models.dto;

// Petición mínima: solo id de material (cantidad opcional, default 1)
public class AddItemMaterialRequest {
    private Long materialId;
    private Integer cantidad; // opcional

    public Long getMaterialId() { return materialId; }
    public void setMaterialId(Long materialId) { this.materialId = materialId; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}
