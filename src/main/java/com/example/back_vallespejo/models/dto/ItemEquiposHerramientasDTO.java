package com.example.back_vallespejo.models.dto;

public class ItemEquiposHerramientasDTO {
    private Long id;
    private String codigo;
    private String descRecurso;
    private Double cantidad;
    private Double precioUnitarioRecurso;
    private Double subtotal;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDescRecurso() { return descRecurso; }
    public void setDescRecurso(String descRecurso) { this.descRecurso = descRecurso; }
    public Double getCantidad() { return cantidad; }
    public void setCantidad(Double cantidad) { this.cantidad = cantidad; }
    public Double getPrecioUnitarioRecurso() { return precioUnitarioRecurso; }
    public void setPrecioUnitarioRecurso(Double precioUnitarioRecurso) { this.precioUnitarioRecurso = precioUnitarioRecurso; }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
}
