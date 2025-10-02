package com.example.back_vallespejo.models.dto;

public class ObtenerTotalesPresupuestoUnitarioDTO {
    private Double subTotalMateriales;
    private Double subTotalEquipos;
    private Double subTotalManoObra;
    private Double total;
    private Double totalParcial;

    public ObtenerTotalesPresupuestoUnitarioDTO() {}

    public ObtenerTotalesPresupuestoUnitarioDTO(Double subTotalMateriales, Double subTotalEquipos, Double subTotalManoObra) {
        this.subTotalMateriales = subTotalMateriales;
        this.subTotalEquipos = subTotalEquipos;
        this.subTotalManoObra = subTotalManoObra;
        this.total = (subTotalMateriales != null ? subTotalMateriales : 0.0)
                  + (subTotalEquipos != null ? subTotalEquipos : 0.0)
                  + (subTotalManoObra != null ? subTotalManoObra : 0.0);
    }

    public Double getSubTotalMateriales() { return subTotalMateriales; }
    public void setSubTotalMateriales(Double subTotalMateriales) { this.subTotalMateriales = subTotalMateriales; }

    public Double getSubTotalEquipos() { return subTotalEquipos; }
    public void setSubTotalEquipos(Double subTotalEquipos) { this.subTotalEquipos = subTotalEquipos; }

    public Double getSubTotalManoObra() { return subTotalManoObra; }
    public void setSubTotalManoObra(Double subTotalManoObra) { this.subTotalManoObra = subTotalManoObra; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    
    public Double getTotalParcial() { return totalParcial; }
    public void setTotalParcial(Double totalParcial) { this.totalParcial = totalParcial; }
}
