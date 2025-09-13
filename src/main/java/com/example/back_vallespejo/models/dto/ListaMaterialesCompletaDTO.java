package com.example.back_vallespejo.models.dto;

import java.time.LocalDateTime;
import java.util.List;


/*Nota Rodri: Este DTO es para el GET de ListaMateriales donde se muestra todo 
 * el contenido de una lista ya completa.*/
public class ListaMaterialesCompletaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private Double totalEstimado;
    private List<ItemMaterialResponseDTO> items;

    
    public ListaMaterialesCompletaDTO() {
    }

    // Este constructor es todo lo que se va a mostrar en la respuesta JSON.
    public ListaMaterialesCompletaDTO(Long id, String nombre, String descripcion, 
                                    LocalDateTime fechaCreacion, LocalDateTime fechaModificacion, 
                                    Double totalEstimado, List<ItemMaterialResponseDTO> items) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.totalEstimado = totalEstimado;
        this.items = items;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Double getTotalEstimado() {
        return totalEstimado;
    }

    public void setTotalEstimado(Double totalEstimado) {
        this.totalEstimado = totalEstimado;
    }

    public List<ItemMaterialResponseDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemMaterialResponseDTO> items) {
        this.items = items;
    }
}
