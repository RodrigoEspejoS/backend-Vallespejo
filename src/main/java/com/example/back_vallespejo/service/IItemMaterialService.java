package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.entities.ItemMaterial;
import com.example.back_vallespejo.models.entities.ListaMateriales;
import com.example.back_vallespejo.models.entities.Material;

import java.util.List;

public interface IItemMaterialService {
    public List<ItemMaterial> getAll();
    public ItemMaterial registrarItemMaterial(ItemMaterial itemMaterial);
    public ItemMaterial findById(Long id);
    public void delete(ItemMaterial itemMaterial);
    public List<ItemMaterial> findByListaMateriales(ListaMateriales listaMateriales);
    public List<ItemMaterial> findByMaterial(Material material);
}
