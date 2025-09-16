package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.entities.U_ManodeObra;
import java.util.List;

public interface IUManodeObraService {
    U_ManodeObra registrar(U_ManodeObra entity);
    List<U_ManodeObra> getAll();
    U_ManodeObra findById(Long id);
    void delete(U_ManodeObra entity);
}
