package com.example.back_vallespejo.models.entities;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;

@Entity
@Table(name = "presupuestos_unitarios")
public class Presupuesto_unitario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String nombre;

    //cada presupuesto unitario está vinculado a su propia lista de Mano de obra, servicios generales y Materiales
}
