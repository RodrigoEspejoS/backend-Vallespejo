package com.example.back_vallespejo.models.dto;

public class ItemPresupuestoDTO {
    private Long id;
    private String tipo; // EQUIPO_HERRAMIENTA, MATERIAL, MANO_OBRA
    private String codigo;
    private String descripcion;
    private String unidad;
    private Double cantidad;
    private Double precioUnitario;
    private Double subtotal;
    private Double cuadrilla;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public Double getCantidad() { return cantidad; }
    public void setCantidad(Double cantidad) { this.cantidad = cantidad; }
    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    public Double getCuadrilla() { return cuadrilla; }
    public void setCuadrilla(Double cuadrilla) { this.cuadrilla = cuadrilla; }
}
