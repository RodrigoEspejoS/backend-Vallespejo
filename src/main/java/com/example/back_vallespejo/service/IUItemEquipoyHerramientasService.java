package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.entities.U_Item_EquipoyHerramientas;
import java.util.List;

public interface IUItemEquipoyHerramientasService {
    U_Item_EquipoyHerramientas registrar(U_Item_EquipoyHerramientas entity);
    List<U_Item_EquipoyHerramientas> getAll();
    U_Item_EquipoyHerramientas findById(Long id);
    void delete(U_Item_EquipoyHerramientas entity);
}
