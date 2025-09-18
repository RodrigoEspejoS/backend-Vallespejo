package com.example.back_vallespejo.models.dto;
import java.util.List;

/* Nota: Este DTO sirve para usarlo en PresupuestoUnitarioDTO 
para mostrar la lista de mano de obra*/
public class ManoDeObraDTO {
    private Long id;
    private Double subtotal;
    private List<ItemManoDeObraDTO> items;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    public List<ItemManoDeObraDTO> getItems() { return items; }
    public void setItems(List<ItemManoDeObraDTO> items) { this.items = items; }
}
