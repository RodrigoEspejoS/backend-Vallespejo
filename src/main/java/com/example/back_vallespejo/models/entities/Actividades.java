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

}
