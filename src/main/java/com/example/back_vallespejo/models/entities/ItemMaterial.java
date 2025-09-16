package com.example.back_vallespejo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "item_materiales")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ItemMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lista_materiales_id", nullable = false)
    private ListaMateriales listaMateriales;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    @NotNull
    @Min(value = 1)
    @Column(nullable = false)
    private Integer cantidad;

    @DecimalMin(value = "0.0", inclusive = false)
    @Column
    private Double precioUnitario;

    @Column
    private Double subtotal;

    @Column(length = 500)
    private String observaciones;

    @Column(nullable = false)
    private LocalDateTime fechaAgregado;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ListaMateriales getListaMateriales() {
        return listaMateriales;
    }

    public void setListaMateriales(ListaMateriales listaMateriales) {
        this.listaMateriales = listaMateriales;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
        // Sincronizar precio unitario con el costo del material
        sincronizarPrecioConMaterial();
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
        calcularSubtotal();
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDateTime getFechaAgregado() {
        return fechaAgregado;
    }

    public void setFechaAgregado(LocalDateTime fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }

    // Métodos de conveniencia
    public void calcularSubtotal() {
        if (cantidad != null && precioUnitario != null) {
            this.subtotal = precioUnitario * cantidad;
        } else {
            this.subtotal = 0.0;
        }
    }

    // Método para sincronizar precio con el material
    public void sincronizarPrecioConMaterial() {
        if (material != null && material.getCostoUnitario() != null) {
            this.precioUnitario = material.getCostoUnitario();
            calcularSubtotal();
        }
    }

    // Método para obtener el precio del material si no se especifica uno
    @Deprecated
    public void usarPrecioDelMaterial() {
        sincronizarPrecioConMaterial();
    }

    // Hook de JPA para sincronizar precio antes de persistir
    @PrePersist
    @PreUpdate
    public void antesDeGuardar() {
        this.fechaAgregado = LocalDateTime.now();
        sincronizarPrecioConMaterial();
    }

}
