package com.example.back_vallespejo.models.DAO;

import com.example.back_vallespejo.models.entities.ItemMaterial;
import com.example.back_vallespejo.models.entities.ListaMateriales;
import com.example.back_vallespejo.models.entities.Material;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IItemMaterialDAO extends CrudRepository<ItemMaterial, Long> {
    List<ItemMaterial> findByListaMateriales(ListaMateriales listaMateriales);
    List<ItemMaterial> findByMaterial(Material material);
}
