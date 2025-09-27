package com.example.back_vallespejo.models.dto;

import jakarta.validation.constraints.NotBlank;

public class CambiarPasswordDTO {
    @NotBlank
    private String contraseñaActual;
    @NotBlank
    private String nuevaContraseña;

    public String getContraseñaActual() {
        return contraseñaActual;
    }

    public void setContraseñaActual(String contraseñaActual) {
        this.contraseñaActual = contraseñaActual;
    }

    public String getNuevaContraseña() {
        return nuevaContraseña;
    }

    public void setNuevaContraseña(String nuevaContraseña) {
        this.nuevaContraseña = nuevaContraseña;
    }
}
