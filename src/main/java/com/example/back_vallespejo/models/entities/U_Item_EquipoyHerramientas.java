package com.example.back_vallespejo.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "unitario_item_equiposyherramientas")
public class U_Item_EquipoyHerramientas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "U_EquipoyHerramientas_id",nullable = false)
    private U_EquipoyHerramientas uEquipoyHerramientas;



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
    private Double cantidad;

    @NotNull
    private Double precio_unitario_recurso;

    private Double subTotal_precio_unitario;

}
