package com.example.back_vallespejo.models.dto;

import java.time.LocalDateTime;
import java.util.List;


/* nOTA R: Este DTO sirve especificamente para el GET de Proyecto, mostrando todos
 * los atributos necesarios incluida la lista de materiales.
 */
public class ProyectoCompletoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaEstimadaFinalizacion;
    private String estado;
    private Double presupuestoEstimado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioCreadorNombre;
    private List<U_ListaMaterialesCompletaDTO> listasMateriales;

    public ProyectoCompletoDTO() {
    }

    // Todos los atributos que se muestran en la respuesta JSON
    public ProyectoCompletoDTO(Long id, String nombre, String descripcion, String ubicacion, 
                             LocalDateTime fechaInicio, LocalDateTime fechaEstimadaFinalizacion, 
                             String estado, Double presupuestoEstimado, LocalDateTime fechaCreacion, 
                             LocalDateTime fechaModificacion, String usuarioCreadorNombre, 
                             List<U_ListaMaterialesCompletaDTO> listasMateriales) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.fechaInicio = fechaInicio;
        this.fechaEstimadaFinalizacion = fechaEstimadaFinalizacion;
        this.estado = estado;
        this.presupuestoEstimado = presupuestoEstimado;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreadorNombre = usuarioCreadorNombre;
        this.listasMateriales = listasMateriales;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaEstimadaFinalizacion() {
        return fechaEstimadaFinalizacion;
    }

    public void setFechaEstimadaFinalizacion(LocalDateTime fechaEstimadaFinalizacion) {
        this.fechaEstimadaFinalizacion = fechaEstimadaFinalizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getPresupuestoEstimado() {
        return presupuestoEstimado;
    }

    public void setPresupuestoEstimado(Double presupuestoEstimado) {
        this.presupuestoEstimado = presupuestoEstimado;
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

    public String getUsuarioCreadorNombre() {
        return usuarioCreadorNombre;
    }

    public void setUsuarioCreadorNombre(String usuarioCreadorNombre) {
        this.usuarioCreadorNombre = usuarioCreadorNombre;
    }

    public List<U_ListaMaterialesCompletaDTO> getListasMateriales() {
        return listasMateriales;
    }

    public void setListasMateriales(List<U_ListaMaterialesCompletaDTO> listasMateriales) {
        this.listasMateriales = listasMateriales;
    }
}
