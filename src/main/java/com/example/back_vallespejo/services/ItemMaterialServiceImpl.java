package com.example.back_vallespejo.services;

import com.example.back_vallespejo.models.dao.IItemMaterialDAO;
import com.example.back_vallespejo.models.entities.ItemMaterial;
import com.example.back_vallespejo.models.entities.ListaMateriales;
import com.example.back_vallespejo.models.entities.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemMaterialServiceImpl implements IItemMaterialService {

    @Autowired
    private IItemMaterialDAO itemMaterialDAO;

    @Override
    public ItemMaterial registrarItemMaterial(ItemMaterial itemMaterial) {
        itemMaterial.calcularSubtotal();
        return itemMaterialDAO.save(itemMaterial);
    }

    @Override
    public List<ItemMaterial> getAll() {
        List<ItemMaterial> items = new ArrayList<>();
        itemMaterialDAO.findAll().forEach(items::add);
        return items;
    }

    @Override
    public ItemMaterial findById(Long id) {
        return itemMaterialDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(ItemMaterial itemMaterial) {
        itemMaterialDAO.delete(itemMaterial);
    }

    @Override
    public List<ItemMaterial> findByListaMateriales(ListaMateriales listaMateriales) {
        return itemMaterialDAO.findByListaMateriales(listaMateriales);
    }

    @Override
    public List<ItemMaterial> findByMaterial(Material material) {
        return itemMaterialDAO.findByMaterial(material);
    }
}
