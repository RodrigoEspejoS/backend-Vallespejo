package com.example.back_vallespejo.models.dto;

/**
 * DTO para proporcionar información sobre el rendimiento calculado
 */
public class RendimientoInfoDTO {
    
    private Double rendimiento;
    private String unidad;
    private String tiempoRendimiento;
    private String descripcionCalculo;
    private String tipoActividad;
    private String nivelComplejidad;
    
    public RendimientoInfoDTO() {}

    public RendimientoInfoDTO(Double rendimiento, String unidad, String tiempoRendimiento, 
                             String descripcionCalculo, String tipoActividad, String nivelComplejidad) {
        this.rendimiento = rendimiento;
        this.unidad = unidad;
        this.tiempoRendimiento = tiempoRendimiento;
        this.descripcionCalculo = descripcionCalculo;
        this.tipoActividad = tipoActividad;
        this.nivelComplejidad = nivelComplejidad;
    }

    public Double getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(Double rendimiento) {
        this.rendimiento = rendimiento;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getTiempoRendimiento() {
        return tiempoRendimiento;
    }

    public void setTiempoRendimiento(String tiempoRendimiento) {
        this.tiempoRendimiento = tiempoRendimiento;
    }

    public String getDescripcionCalculo() {
        return descripcionCalculo;
    }

    public void setDescripcionCalculo(String descripcionCalculo) {
        this.descripcionCalculo = descripcionCalculo;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public String getNivelComplejidad() {
        return nivelComplejidad;
    }

    public void setNivelComplejidad(String nivelComplejidad) {
        this.nivelComplejidad = nivelComplejidad;
    }
}
