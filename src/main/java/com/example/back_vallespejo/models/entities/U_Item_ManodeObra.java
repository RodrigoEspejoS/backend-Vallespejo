package com.example.back_vallespejo.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Unitario_Item_ManodeObra")
public class U_Item_ManodeObra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Lista_ManodeObra_id",nullable = false)
    private U_ManodeObra ListaManodeObra;




}
