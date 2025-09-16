package com.example.back_vallespejo.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "presupuestos_generales")
public class Presupuesto_General {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String descripcion;

    //cada presupuesto general cuanta con Actividades que dentro cada actividad se crea automaticamente
    //un presupuesto unitario con sus 3 sub niveles.
}
