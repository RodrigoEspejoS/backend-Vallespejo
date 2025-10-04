package com.example.back_vallespejo.models.entities;

import jakarta.persistence.*;

// Relaciona cada item de equipos y herramientas con una fila base de tabla de datos de presupuestos
// (similar a ItemMaterial.material) sin afectar las otras entidades.

@Entity
@Table(name = "unitario_item_equiposyherramientas")
public class U_Item_EquipoyHerramientas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "U_EquipoyHerramientas_id",nullable = false)
    private U_EquipoyHerramientas uEquipoyHerramientas;

    // Snapshot de datos (cuando se crea el item se copian o se toman del JSON)
    private String codigo;
    private String desc_recurso;
    private String unidad;
    private Double cuadrilla;
    private Double cantidad;

    // Precio unitario snapshot (se guarda para histórico aunque cambie en catálogo)
    @Column(name = "precio_unitario")
    private Double precio_unitario;

    Double subTotal_precio_unitario;

    // Nueva relación con tabla de datos de presupuestos (catálogo base)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "td_presupuesto_id")
    private TD_Presupuestos tdPresupuesto;

    public TD_Presupuestos getTdPresupuesto() { return tdPresupuesto; }
    public void setTdPresupuesto(TD_Presupuestos tdPresupuesto) { this.tdPresupuesto = tdPresupuesto; }


    public U_EquipoyHerramientas getUEquipoyHerramientas() {
        return uEquipoyHerramientas;
    }

    public void setUEquipoyHerramientas(U_EquipoyHerramientas uEquipoyHerramientas) {
        this.uEquipoyHerramientas = uEquipoyHerramientas;
    }

    public Long getId() { return id; }
    public Double getCantidad() { return cantidad; }
    public Double getPrecio_unitario() { return precio_unitario != null ? precio_unitario : (tdPresupuesto != null ? tdPresupuesto.getPrecioUnitario() : null); }
    public Double getSubTotal_precio_unitario() { return subTotal_precio_unitario; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDesc_recurso() { return desc_recurso; }
    public void setDesc_recurso(String desc_recurso) { this.desc_recurso = desc_recurso; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public Double getCuadrilla() { return cuadrilla; }
    public void setCuadrilla(Double cuadrilla) { this.cuadrilla = cuadrilla; }
    // Alias para compatibilidad con código que espera este nombre
    public Double getPrecio_unitario_recurso() { return getPrecio_unitario(); }


    public void setCantidad(Double cantidad) { this.cantidad = cantidad; calcularSubtotal(); }
    public void setPrecio_unitario(Double precio_unitario) { this.precio_unitario = precio_unitario; calcularSubtotal(); }

        @PrePersist
        @PreUpdate
        public void calcularSubtotal() {
            Double precioBase = getPrecio_unitario();
            if (cantidad != null && precioBase != null) {
                this.subTotal_precio_unitario = cantidad * precioBase;
            } else {
                this.subTotal_precio_unitario = 0.0;
            }
        }
}