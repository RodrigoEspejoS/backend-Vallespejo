package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.entities.Rol;

import java.util.List;

public interface IRolService {
    public List<Rol> getAll();
    public Rol registrarRol(Rol rol);
    public Rol findById(Long id);
    public void delete(Rol rol);
}
