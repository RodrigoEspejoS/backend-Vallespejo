package com.example.back_vallespejo.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Unitario_ManodeObra")
public class U_ManodeObra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
