package com.example.back_vallespejo.models.dto;

public class ItemGenericoPresupuestoDTO {
    private Long tdPresupuestoId; // opcional, si se quiere enlazar catálogo
    private String codigo;
    private String desc_recurso;
    private Double cuadrilla;
    private String unidad;
    private Double cantidad; // para equipos / mano de obra (double)
    private Integer cantidadEntera; // si en algún caso se requiere entero
    private Double precioUnitarioRecurso; // override permitido

    public Long getTdPresupuestoId() { return tdPresupuestoId; }
    public void setTdPresupuestoId(Long tdPresupuestoId) { this.tdPresupuestoId = tdPresupuestoId; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDesc_recurso() { return desc_recurso; }
    public void setDesc_recurso(String desc_recurso) { this.desc_recurso = desc_recurso; }
    public Double getCuadrilla() { return cuadrilla; }
    public void setCuadrilla(Double cuadrilla) { this.cuadrilla = cuadrilla; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public Double getCantidad() { return cantidad; }
    public void setCantidad(Double cantidad) { this.cantidad = cantidad; }
    public Integer getCantidadEntera() { return cantidadEntera; }
    public void setCantidadEntera(Integer cantidadEntera) { this.cantidadEntera = cantidadEntera; }
    public Double getPrecioUnitarioRecurso() { return precioUnitarioRecurso; }
    public void setPrecioUnitarioRecurso(Double precioUnitarioRecurso) { this.precioUnitarioRecurso = precioUnitarioRecurso; }
}
