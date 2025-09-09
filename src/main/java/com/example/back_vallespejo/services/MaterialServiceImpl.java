package com.example.back_vallespejo.services;

import com.example.back_vallespejo.models.dao.IMaterialDAO;
import com.example.back_vallespejo.models.entities.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialServiceImpl implements IMaterialService {

    @Autowired
    private IMaterialDAO materialDAO;

    @Override
    public Material registrarMaterial(Material material) {
        return materialDAO.save(material);
    }

    @Override
    public List<Material> getAll() {
        List<Material> materiales = new ArrayList<>();
        materialDAO.findAll().forEach(materiales::add);
        return materiales;
    }

    @Override
    public Material findById(Long id) {
        return materialDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(Material material) {
        materialDAO.delete(material);
    }

    @Override
    public Material actualizarCantidad(Long id, Integer nuevaCantidad) {
        Material material = findById(id);
        if (material != null && nuevaCantidad != null && nuevaCantidad >= 0) {
            material.setUnidades(nuevaCantidad);
            return materialDAO.save(material);
        }
        return null;
    }

    @Override
    public Material agregarCantidad(Long id, Integer cantidadAAgregar) {
        Material material = findById(id);
        if (material != null && cantidadAAgregar != null && cantidadAAgregar > 0) {
            Integer cantidadActual = material.getUnidades() != null ? material.getUnidades() : 0;
            material.setUnidades(cantidadActual + cantidadAAgregar);
            return materialDAO.save(material);
        }
        return null;
    }

    @Override
    public Material reducirCantidad(Long id, Integer cantidadAReducir) {
        Material material = findById(id);
        if (material != null && cantidadAReducir != null && cantidadAReducir > 0) {
            Integer cantidadActual = material.getUnidades() != null ? material.getUnidades() : 0;
            Integer nuevaCantidad = cantidadActual - cantidadAReducir;
            
            // Evitar cantidades negativas
            if (nuevaCantidad < 0) {
                nuevaCantidad = 0;
            }
            
            material.setUnidades(nuevaCantidad);
            return materialDAO.save(material);
        }
        return null;
    }
}
