package com.example.back_vallespejo.models.dto;

public class UpdatePresupuestoUnitarioRequest {
    private String descripcion;
    private String unidad;
    private Double u_rendimiento;
    private String t_rendimiento;

    public UpdatePresupuestoUnitarioRequest() {}

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Double getU_rendimiento() {
        return u_rendimiento;
    }

    public void setU_rendimiento(Double u_rendimiento) {
        this.u_rendimiento = u_rendimiento;
    }

    public String getT_rendimiento() {
        return t_rendimiento;
    }

    public void setT_rendimiento(String t_rendimiento) {
        this.t_rendimiento = t_rendimiento;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}