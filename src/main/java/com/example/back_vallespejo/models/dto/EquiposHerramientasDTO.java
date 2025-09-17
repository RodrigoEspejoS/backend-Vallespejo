package com.example.back_vallespejo.models.dto;
import java.util.List;

public class EquiposHerramientasDTO {
    private Long id;
    private Double subtotal;
    private List<ItemEquiposHerramientasDTO> items;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    public List<ItemEquiposHerramientasDTO> getItems() { return items; }
    public void setItems(List<ItemEquiposHerramientasDTO> items) { this.items = items; }
}
