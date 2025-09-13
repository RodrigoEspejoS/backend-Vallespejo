package com.example.back_vallespejo.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/* Nota R: dTO de usuario para el Post de creación de un nuevo usuario. Especificando
 * los atributos obligatorios 
 */
public class UsuarioDTO {

    @NotBlank(message = "El DNI es obligatorio")
    private String dni;

    @Email(message = "El email debe tener un formato válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @NotBlank(message = "El rol es obligatorio")
    private String nombreRol; // Se pedira solo el nombre de rol y debe existir previamente

    private Integer estado = 1; // El 1 se usa por defecto para validar en la base de datos

    public UsuarioDTO() {
    }

    // Atributos minimos para crear un usuario
    public UsuarioDTO(String dni, String email, String nombre, String apellido, String password, String nombreRol) {
        this.dni = dni;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.nombreRol = nombreRol;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

}
