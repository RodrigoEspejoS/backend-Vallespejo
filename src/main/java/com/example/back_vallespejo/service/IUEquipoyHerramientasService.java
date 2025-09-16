package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.entities.U_EquipoyHerramientas;
import java.util.List;

public interface IUEquipoyHerramientasService {
    U_EquipoyHerramientas registrar(U_EquipoyHerramientas entity);
    List<U_EquipoyHerramientas> getAll();
    U_EquipoyHerramientas findById(Long id);
    void delete(U_EquipoyHerramientas entity);
}
