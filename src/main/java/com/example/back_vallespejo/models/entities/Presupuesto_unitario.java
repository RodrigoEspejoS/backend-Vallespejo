package com.example.back_vallespejo.models.entities;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;

@Entity
@Table(name = "presupuestos_unitarios")
public class Presupuesto_unitario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //vincular onetoone con Actividades

    @Column(length = 50)
    private String descripcion;
    //tipo de unidad (glb,und)
    @Column(length = 10)
    private String unidad;

    //unidad de rendimiento
    private Double u_rendimiento;
    //tiempo de rendimiento
    @Column(length = 20)
    private String t_rendimiento;

    //este será el total de la suma de sub total Mano de obra, Lista de materiales y EquiposyHerramientas
    private Double Total_presupuesto_unitario;

    //cada presupuesto unitario está vinculado a su propia lista de Mano de obra, servicios generales y Materiales
}
