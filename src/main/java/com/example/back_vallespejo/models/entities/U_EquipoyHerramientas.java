package com.example.back_vallespejo.models.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "Lista_equipoyherramientas")
public class U_EquipoyHerramientas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "uEquipoyHerramientas",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<U_Item_EquipoyHerramientas> uItemEquipoyHerramientas;

    // Sub total de todos los items dentro de la lista
    // Inicializado para cumplir la validación @NotNull al momento de persistir
    
    @Column(name = "sub_total_eyh", nullable = false)
    private Double subTotal_EyH = 0.0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<U_Item_EquipoyHerramientas> getUItemEquipoyHerramientas() {
        return uItemEquipoyHerramientas;
    }

    public void setUItemEquipoyHerramientas(List<U_Item_EquipoyHerramientas> uItemEquipoyHerramientas) {
        this.uItemEquipoyHerramientas = uItemEquipoyHerramientas;
    }

    public Double getSubTotal_EyH() {
        return subTotal_EyH;
    }

    public void setSubTotal_EyH(Double subTotal_EyH) {
        this.subTotal_EyH = subTotal_EyH;
    }

    public Double getSubtotal() {
        if (uItemEquipoyHerramientas == null || uItemEquipoyHerramientas.isEmpty()) {
            this.subTotal_EyH = 0.0;
            return subTotal_EyH;
        }
        double suma = 0.0;
        for (U_Item_EquipoyHerramientas item : uItemEquipoyHerramientas) {
            if (item != null) {
                item.calcularSubtotal();
                suma += item.subTotal_precio_unitario;
            }
        }
        this.subTotal_EyH = suma;
        return subTotal_EyH;
    }

}
