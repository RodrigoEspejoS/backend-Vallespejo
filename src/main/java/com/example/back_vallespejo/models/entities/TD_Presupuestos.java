package com.example.back_vallespejo.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "tabla_de_datos_presupuestos")
public class TD_Presupuestos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, length = 7)
    private String codigo;

    @NotNull
    @Column(length = 50, unique = true)
    private String desc_recurso;

    @NotNull
    private Double cuadrilla;

    //unidad de medida (h-h, VIAJE,%MO)
    @NotNull
    private String unidad;

    @Column(nullable = false)
    private LocalDateTime fechaAgregado;

    @NotBlank
    @Column(length = 20)
    private String categoria;

    // Precio unitario base referenciado por items; se permite copiar como snapshot en cada item
    @Column(name = "precio_unitario")
    private Double precioUnitario;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDesc_recurso() { return desc_recurso; }
    public void setDesc_recurso(String desc_recurso) { this.desc_recurso = desc_recurso; }
    public Double getCuadrilla() { return cuadrilla; }
    public void setCuadrilla(Double cuadrilla) { this.cuadrilla = cuadrilla; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public LocalDateTime getFechaAgregado() { return fechaAgregado; }
    public void setFechaAgregado(LocalDateTime fechaAgregado) { this.fechaAgregado = fechaAgregado; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
    // Alias para compatibilidad si algún código antiguo esperaba getPrecio_unitario_recurso
    @Deprecated
    public Double getPrecio_unitario_recurso() { return precioUnitario; }
}
