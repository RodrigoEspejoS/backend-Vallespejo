package com.example.back_vallespejo.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "Lista_equipoyherramientas")
public class U_EquipoyHerramientas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "uEquipoyHerramientas",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<U_Item_EquipoyHerramientas> uItemEquipoyHerramientas;

    //sub total de todos los items dentro de la lista que se genere en un solo id;
    @NotNull
    private Double subTotal_EyH;



}
