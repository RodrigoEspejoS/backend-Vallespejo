package com.example.back_vallespejo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

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

    @NotNull
    @Min(value = 1)
    @Column(nullable = false)
    private Integer cantidad;

    @DecimalMin(value = "0.0", inclusive = false)
    @Column
    private Double precioUnitarioRecurso;

    @Column
    private Double subtotal_PrecioUnitario;

    
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

    public Double getPrecioUnitarioRecurso() {
        return precioUnitarioRecurso;
    }

    public void setPrecioUnitarioRecurso(Double precioUnitarioRecurso) {
        this.precioUnitarioRecurso = precioUnitarioRecurso;
        calcularSubtotal();
    }

    public Double getSubtotal_PrecioUnitario() {
        return subtotal_PrecioUnitario;
    }

    public void setSubtotal_PrecioUnitario(Double subtotal_PrecioUnitario) {
        this.subtotal_PrecioUnitario = subtotal_PrecioUnitario;
    }


    // Métodos de conveniencia
    public void calcularSubtotal() {
        if (cantidad != null && precioUnitarioRecurso != null) {
            this.subtotal_PrecioUnitario = precioUnitarioRecurso * cantidad;
        } else {
            this.subtotal_PrecioUnitario = 0.0;
        }
    }

    // Método para sincronizar precio con el material
    public void sincronizarPrecioConMaterial() {
        if (material != null && material.getCostoUnitario() != null) {
            this.precioUnitarioRecurso = material.getCostoUnitario();
            calcularSubtotal();
        }
    }

    // Método para obtener el precio del material si no se especifica uno
    @Deprecated
    public void usarPrecioDelMaterial() {
        sincronizarPrecioConMaterial();
    }


}
