package com.example.back_vallespejo.models.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/* Nota Rodri: Este DTO sirve para la creación (el modelo JSON de POST) de un ItemMaterial */
public class ItemMaterialDTO {

    @NotNull(message = "El ID del material es obligatorio")
    private Long materialId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    private String observaciones;

    
    public ItemMaterialDTO() {}

    // Nota: Este constructor sirve especificamente como lo minimo requerido para crear una item_Material para una listaMaterial.
    public ItemMaterialDTO(Long materialId, Integer cantidad) {
        this.materialId = materialId;
        this.cantidad = cantidad;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
