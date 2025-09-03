package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.entities.Material;

import java.util.List;

public interface IMaterialService {
    public List<Material> getAll();
    public Material registrarMaterial(Material material);
    public Material findById(Long id);
    public void delete(Material material);
    
    // Métodos para gestión de cantidades
    public Material actualizarCantidad(Long id, Integer nuevaCantidad);
    public Material agregarCantidad(Long id, Integer cantidadAAgregar);
    public Material reducirCantidad(Long id, Integer cantidadAReducir);
}
