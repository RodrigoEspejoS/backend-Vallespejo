package com.example.back_vallespejo.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tabla_de_datos_presupuestos")
public class TD_Presupuestos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private Double precio_unitario_recurso;

    @NotBlank
    @Column(length = 20)
    private String categoria;
}
