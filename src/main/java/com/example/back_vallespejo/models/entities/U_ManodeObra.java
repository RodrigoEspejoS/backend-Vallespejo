package com.example.back_vallespejo.models.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "Unitario_ManodeObra")
public class U_ManodeObra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "ListaManodeObra",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<U_Item_ManodeObra> itemManodeObra;

    public List<U_Item_ManodeObra> getItemManodeObra() {
        return itemManodeObra;
    }

    public void setItemManodeObra(List<U_Item_ManodeObra> itemManodeObra) {
        this.itemManodeObra = itemManodeObra;
    }
        private Double subtotal;
    
    public Long getId() {
        return id; }
    
    public Long setId() {
        return id;
    }
    
        public Double getSubtotal() {
            if (itemManodeObra == null || itemManodeObra.isEmpty()) {
                this.subtotal = 0.0;
                return subtotal;
            }
            double suma = 0.0;
            for (U_Item_ManodeObra item : itemManodeObra) {
                if (item != null) {
                    item.calcularSubtotal();
                    suma += item.subtotal;
                }
            }
            this.subtotal = suma;
            return subtotal;
        }

}
