package com.example.back_vallespejo.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "actividades")
public class Actividades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    @NotBlank
    private String nombre;

    @Column(length = 150)
    @NotBlank
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presupuesto_general_id", nullable = false)
    private Presupuesto_General presupuestoGeneral;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "presupuesto_unitario_id", referencedColumnName = "id", nullable = false)
    private Presupuesto_unitario presupuestoUnitario;

    private String estado;

    @Column(name = "subtotal_presupuesto_unitario")
    private Double subtotalPresupuestoUnitario = 0.0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotBlank String descripcion) {
        this.descripcion = descripcion;
    }

    public Presupuesto_General getPresupuestoGeneral() {
        return presupuestoGeneral;
    }

    public void setPresupuestoGeneral(Presupuesto_General presupuestoGeneral) {
        this.presupuestoGeneral = presupuestoGeneral;
    }

    public Presupuesto_unitario getPresupuestoUnitario() {
        return presupuestoUnitario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPresupuestoUnitario(Presupuesto_unitario presupuestoUnitario) {
        this.presupuestoUnitario = presupuestoUnitario;
    }

    public Double getSubtotalPresupuestoUnitario() {
        return subtotalPresupuestoUnitario;
    }

    public void setSubtotalPresupuestoUnitario(Double subtotalPresupuestoUnitario) {
        this.subtotalPresupuestoUnitario = subtotalPresupuestoUnitario;
    }

    /**
     * Método para sincronizar automáticamente el subtotal de la actividad
     * con el total del presupuesto unitario vinculado
     */
    public void sincronizarSubtotalConPresupuestoUnitario() {
        if (this.presupuestoUnitario != null) {
            Double totalPresupuestoUnitario = this.presupuestoUnitario.getTotal_presupuesto_unitario();
            this.subtotalPresupuestoUnitario = totalPresupuestoUnitario != null ? totalPresupuestoUnitario : 0.0;
        }
    }

    /**
     * Método para obtener el subtotal actualizado automáticamente
     * desde el presupuesto unitario vinculado
     */
    public Double getSubtotalActualizado() {
        sincronizarSubtotalConPresupuestoUnitario();
        return this.subtotalPresupuestoUnitario;
    }

}
