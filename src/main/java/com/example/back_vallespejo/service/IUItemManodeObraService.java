package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.entities.U_Item_ManodeObra;
import java.util.List;

public interface IUItemManodeObraService {
    U_Item_ManodeObra registrar(U_Item_ManodeObra entity);
    List<U_Item_ManodeObra> getAll();
    U_Item_ManodeObra findById(Long id);
    void delete(U_Item_ManodeObra entity);
}
