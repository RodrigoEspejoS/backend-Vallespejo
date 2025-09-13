package com.example.back_vallespejo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Random;

@Entity
@Table(name = "materiales")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @NotNull
    @Min(value = 0)
    @Column(nullable = false)
    private Integer unidades;

    @DecimalMin(value = "0.0", inclusive = false)
    @Column
    private Double costoUnitario;

    @Column(length = 10, unique = true)
    private String serie;

    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private Integer estado;

    // Método para generar serie automáticamente
    private String generarSerie() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder serie = new StringBuilder();
        
        for (int i = 0; i < 10; i++) {
            serie.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        
        return serie.toString();
    }

    @PrePersist
    public void antesDeGuardar() {
        if (this.serie == null || this.serie.trim().isEmpty()) {
            this.serie = generarSerie();
        }
        if (this.estado == null) {
            this.estado = 1;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public Double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

        public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }    
}
