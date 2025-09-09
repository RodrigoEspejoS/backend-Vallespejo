package com.example.back_vallespejo.services;

import com.example.back_vallespejo.models.dao.IProveedorDAO;
import com.example.back_vallespejo.models.entities.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProveedorServiceImpl implements IProveedorService {

    @Autowired
    private IProveedorDAO proveedorDAO;

    @Override
    public Proveedor registrarProveedor(Proveedor proveedor) {
        return proveedorDAO.save(proveedor);
    }

    @Override
    public List<Proveedor> getAll() {
        List<Proveedor> proveedores = new ArrayList<>();
        proveedorDAO.findAll().forEach(proveedores::add);
        return proveedores;
    }

    @Override
    public Proveedor findById(Long id) {
        return proveedorDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(Proveedor proveedor) {
        proveedorDAO.delete(proveedor);
    }
}
