package com.example.back_vallespejo.models.dto;

public class PresupuestoUnitarioDTO {
    private Long id;
    private String descripcion;
    private String unidad;
    private Double u_rendimiento;
    private String t_rendimiento;
    private Double totalPresupuestoUnitario;
    
    private ListaMaterialesDTO listaMateriales; 
    private EquiposHerramientasDTO equiposHerramientas;
    private ManoDeObraDTO manoDeObra;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public Double getU_rendimiento() { return u_rendimiento; }
    public void setU_rendimiento(Double u_rendimiento) { this.u_rendimiento = u_rendimiento; }
    public String getT_rendimiento() { return t_rendimiento; }
    public void setT_rendimiento(String t_rendimiento) { this.t_rendimiento = t_rendimiento; }
    public Double getTotalPresupuestoUnitario() { return totalPresupuestoUnitario; }
    public void setTotalPresupuestoUnitario(Double totalPresupuestoUnitario) { this.totalPresupuestoUnitario = totalPresupuestoUnitario; }
    public ListaMaterialesDTO getListaMateriales() { return listaMateriales; }
    public void setListaMateriales(ListaMaterialesDTO listaMateriales) { this.listaMateriales = listaMateriales; }
    public EquiposHerramientasDTO getEquiposHerramientas() { return equiposHerramientas; }
    public void setEquiposHerramientas(EquiposHerramientasDTO equiposHerramientas) { this.equiposHerramientas = equiposHerramientas; }
    public ManoDeObraDTO getManoDeObra() { return manoDeObra; }
    public void setManoDeObra(ManoDeObraDTO manoDeObra) { this.manoDeObra = manoDeObra; }
}
