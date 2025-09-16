package com.example.back_vallespejo.models.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;


/* Nota R: Este DTO sirve especificamente para el POST en JSON de la Lista Materiales
 * pero se automatiza con la creación de un Proyecto. (Colocando nombre:
 *  Lista de materiales - <proyecto>, toma el mismo usuarioid del que creó el proyecto.)
 * Creando una lista de materiales vacia automaticamente por cada Proyecto.
 */
public class ListaMaterialesDTO {

    @NotBlank(message = "El nombre de la lista es obligatorio")
    private String nombre;

    private String descripcion;


    @Valid
    private List<ItemMaterialDTO> items;

    public ListaMaterialesDTO() {}

    public ListaMaterialesDTO(String nombre, List<ItemMaterialDTO> items) {
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


    public List<ItemMaterialDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemMaterialDTO> items) {
        this.items = items;
    }
}
