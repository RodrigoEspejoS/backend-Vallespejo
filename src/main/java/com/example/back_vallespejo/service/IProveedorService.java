package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.entities.Proveedor;

import java.util.List;

public interface IProveedorService {
    public List<Proveedor> getAll();
    public Proveedor registrarProveedor(Proveedor proveedor);
    public Proveedor findById(Long id);
    public void delete(Proveedor proveedor);
}
