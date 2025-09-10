package com.example.back_vallespejo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lista_materiales")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class U_ListaMateriales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column
    private LocalDateTime fechaActualizacion;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'PENDIENTE'")
    private EstadoLista estado;

    @Column
    private Double totalEstimado;

    @OneToOne(mappedBy = "UListaMateriales", fetch = FetchType.LAZY)
    @JsonIgnore
    private Proyecto proyecto;

    @OneToMany(mappedBy = "UListaMateriales", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<U_ItemListaMateriales> items = new ArrayList<>();


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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public EstadoLista getEstado() {
        return estado;
    }

    public void setEstado(EstadoLista estado) {
        this.estado = estado;
    }

    public Double getTotalEstimado() {
        return totalEstimado;
    }

    public void setTotalEstimado(Double totalEstimado) {
        this.totalEstimado = totalEstimado;
    }

    public List<U_ItemListaMateriales> getItems() {
        return items;
    }

    public void setItems(List<U_ItemListaMateriales> items) {
        this.items = items;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

   
    public void actualizarFechaModificacion() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    // Enum para el estado de la lista
    public enum EstadoLista {
        PENDIENTE,
        APROBADA,
        RECHAZADA,
        EN_PROCESO,
        COMPLETADA,
        CANCELADA
    }

}
