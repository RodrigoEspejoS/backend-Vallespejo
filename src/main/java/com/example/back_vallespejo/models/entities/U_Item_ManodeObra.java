package com.example.back_vallespejo.models.entities;

import jakarta.persistence.*;

// Relación con catálogo TD_Presupuestos para reutilizar datos base del recurso de mano de obra

@Entity
@Table(name = "Unitario_Item_ManodeObra")
public class U_Item_ManodeObra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Lista_ManodeObra_id",nullable = false)
    private U_ManodeObra ListaManodeObra;

    // Snapshot de datos (se copian desde TD_Presupuestos al crear el item)
    private String codigo;
    private String desc_recurso;
    private String unidad;
    private Double cuadrilla;
    private Double cantidad;
    @Column(name = "precio_unitario")
    private Double precio_unitario;
    Double subtotal;

    // Nueva relación al catálogo de presupuesto base
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "td_presupuesto_id")
    private TD_Presupuestos tdPresupuesto;


    public TD_Presupuestos getTdPresupuesto() { return tdPresupuesto; }
    public void setTdPresupuesto(TD_Presupuestos tdPresupuesto) { this.tdPresupuesto = tdPresupuesto; }

    public U_ManodeObra getListaManodeObra() {
        return ListaManodeObra;
    }

    public void setListaManodeObra(U_ManodeObra listaManodeObra) {
        this.ListaManodeObra = listaManodeObra;
    }
    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDesc_recurso() { return desc_recurso; }
    public void setDesc_recurso(String desc_recurso) { this.desc_recurso = desc_recurso; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public Double getCantidad() { return cantidad; }
    public void setCantidad(Double cantidad) { this.cantidad = cantidad; calcularSubtotal(); }
    public Double getPrecio_unitario() { return precio_unitario; }
    public void setPrecio_unitario(Double precio_unitario) { this.precio_unitario = precio_unitario; calcularSubtotal(); }
    public Double getSubtotal() { return subtotal; }
    public Double getCuadrilla() { return cuadrilla; }
    public void setCuadrilla(Double cuadrilla) { this.cuadrilla = cuadrilla; }

        public void calcularSubtotal() {
            if (cantidad != null && precio_unitario != null) {
                this.subtotal = cantidad * precio_unitario;
            } else {
                this.subtotal = 0.0;
            }
        }
        

}
