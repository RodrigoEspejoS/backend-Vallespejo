package com.example.back_vallespejo.models.dto;

public class ItemCatalogoResponseDTO {
    private Long id; // id del item
    private String tipo; // EQUIPO | MANO_OBRA
    private Long catalogoId; // tdPresupuestoId
    private String codigo;
    private String descripcion;
    private String unidad;
    private Double cuadrilla; // puede ser null en materiales
    private Double cantidad;
    private Double precioUnitario;
    private Double subtotal;

    // Datos del catálogo (para mostrar en GET incluso si cambia luego)
    private String catalogoCodigo;
    private String catalogoDescripcion;
    private Double catalogoPrecioUnitario;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Long getCatalogoId() { return catalogoId; }
    public void setCatalogoId(Long catalogoId) { this.catalogoId = catalogoId; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public Double getCuadrilla() { return cuadrilla; }
    public void setCuadrilla(Double cuadrilla) { this.cuadrilla = cuadrilla; }
    public Double getCantidad() { return cantidad; }
    public void setCantidad(Double cantidad) { this.cantidad = cantidad; }
    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    public String getCatalogoCodigo() { return catalogoCodigo; }
    public void setCatalogoCodigo(String catalogoCodigo) { this.catalogoCodigo = catalogoCodigo; }
    public String getCatalogoDescripcion() { return catalogoDescripcion; }
    public void setCatalogoDescripcion(String catalogoDescripcion) { this.catalogoDescripcion = catalogoDescripcion; }
    public Double getCatalogoPrecioUnitario() { return catalogoPrecioUnitario; }
    public void setCatalogoPrecioUnitario(Double catalogoPrecioUnitario) { this.catalogoPrecioUnitario = catalogoPrecioUnitario; }
}
