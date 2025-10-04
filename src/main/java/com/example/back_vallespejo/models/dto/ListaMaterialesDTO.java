package com.example.back_vallespejo.models.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;


/* Nota R: Este DTO sirve especificamente para el DTO de PResupuestoUnitarioDTO 
para añadir la lista de materiales*/
public class ListaMaterialesDTO {

    @NotBlank(message = "El nombre de la lista es obligatorio")
    private String nombre;

    private String descripcion;

    private Double subTotal;

    @Valid
    private List<ItemMaterialResponseDTO> items;

    public ListaMaterialesDTO() {}

    public ListaMaterialesDTO(String nombre, List<ItemMaterialResponseDTO> items) {
        this.nombre = nombre;
        this.items = items;
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

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public List<ItemMaterialResponseDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemMaterialResponseDTO> items) {
        this.items = items;
    }
}
