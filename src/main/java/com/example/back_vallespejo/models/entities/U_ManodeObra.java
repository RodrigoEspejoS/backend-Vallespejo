package com.example.back_vallespejo.models.entities;

import jakarta.persistence.*;

import java.lang.module.FindException;
import java.util.List;

@Entity
@Table(name = "Unitario_ManodeObra")
public class U_ManodeObra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "ListaManodeObra",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<U_Item_ManodeObra> itemManodeObra;


}
