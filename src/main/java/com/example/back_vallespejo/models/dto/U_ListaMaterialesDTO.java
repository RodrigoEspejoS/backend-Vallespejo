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
public class U_ListaMaterialesDTO {

    @NotBlank(message = "El nombre de la lista es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

    @Valid
    private List<U_ItemListaMaterialesDTO> items;

    public U_ListaMaterialesDTO() {}

    public U_ListaMaterialesDTO(String nombre, Long usuarioId, List<U_ItemListaMaterialesDTO> items) {
        this.nombre = nombre;
        this.usuarioId = usuarioId;
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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<U_ItemListaMaterialesDTO> getItems() {
        return items;
    }

    public void setItems(List<U_ItemListaMaterialesDTO> items) {
        this.items = items;
    }
}
