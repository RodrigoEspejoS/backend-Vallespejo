package com.example.back_vallespejo.services;

import com.example.back_vallespejo.models.entities.U_ItemListaMateriales;
import com.example.back_vallespejo.models.entities.Material;
import com.example.back_vallespejo.models.entities.U_ListaMateriales;

import java.util.List;

public interface IU_ItemListaMaterialesService {
    public List<U_ItemListaMateriales> getAll();
    public U_ItemListaMateriales registrarItemMaterial(U_ItemListaMateriales itemMaterial);
    public U_ItemListaMateriales findById(Long id);
    public void delete(U_ItemListaMateriales itemMaterial);
    public List<U_ItemListaMateriales> findByListaMateriales(U_ListaMateriales listaMateriales);
    public List<U_ItemListaMateriales> findByMaterial(Material material);
}
