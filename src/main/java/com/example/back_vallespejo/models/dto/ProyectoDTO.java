package com.example.back_vallespejo.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;


/* Nota : Este dto sirve para el POST de Proyecto. Para crear un proyecto 
necesariamente necesita un nombre, un usuario responsable y un presupuesto */
public class ProyectoDTO {

    @NotBlank(message = "El nombre del proyecto es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El ID del usuario responsable es obligatorio")
    private Long usuarioResponsableId;

    private Long listaMaterialesId;

    private LocalDate fechaInicio;

    private LocalDate fechaFinEstimada;

    @NotNull(message = "El presupuesto es obligatorio")
    private Double presupuesto;

    private String estado;

    private String ubicacion;

    public ProyectoDTO() {}

    // Atributos minimos para la creación de un proyecto
    public ProyectoDTO(String nombre, Long usuarioResponsableId, Double presupuesto) {
        this.nombre = nombre;
        this.usuarioResponsableId = usuarioResponsableId;
        this.presupuesto = presupuesto;
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

    public Long getUsuarioResponsableId() {
        return usuarioResponsableId;
    }

    public void setUsuarioResponsableId(Long usuarioResponsableId) {
        this.usuarioResponsableId = usuarioResponsableId;
    }

    public Long getListaMaterialesId() {
        return listaMaterialesId;
    }

    public void setListaMaterialesId(Long listaMaterialesId) {
        this.listaMaterialesId = listaMaterialesId;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinEstimada() {
        return fechaFinEstimada;
    }

    public void setFechaFinEstimada(LocalDate fechaFinEstimada) {
        this.fechaFinEstimada = fechaFinEstimada;
    }

    public Double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
