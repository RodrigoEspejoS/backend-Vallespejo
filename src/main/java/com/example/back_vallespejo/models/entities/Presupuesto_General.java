package com.example.back_vallespejo.models.entities;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "presupuestos_generales")
public class Presupuesto_General {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String descripcion;

    @OneToMany(mappedBy = "presupuestoGeneral", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Actividades> actividades;

    @Column(name = "presupuesto_estimado")
    private Double presupuestoEstimado = 0.0;

    public List<Actividades> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividades> actividades) {
        this.actividades = actividades;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Double getPresupuestoEstimado() {
        return presupuestoEstimado;
    }

    public void setPresupuestoEstimado(Double presupuestoEstimado) {
        this.presupuestoEstimado = presupuestoEstimado;
    }

    //cada presupuesto general cuanta con Actividades que dentro cada actividad se crea automaticamente
    //un presupuesto unitario con sus 3 sub niveles.
}
