package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dao.ITDPresupuestosDAO;
import com.example.back_vallespejo.models.entities.TD_Presupuestos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TDPresupuestosServiceImpl implements ITDPresupuestosService {
    @Autowired
    private ITDPresupuestosDAO dao;

    @Override
    public TD_Presupuestos crear(TD_Presupuestos entity) {
        entity.setFechaAgregado(LocalDateTime.now());
        return dao.save(entity);
    }

    @Override
    public TD_Presupuestos actualizar(Long id, TD_Presupuestos entity) {
        return dao.findById(id).map(existing -> {
            existing.setCodigo(entity.getCodigo());
            existing.setDesc_recurso(entity.getDesc_recurso());
            existing.setCuadrilla(entity.getCuadrilla());
            existing.setUnidad(entity.getUnidad());
            existing.setCategoria(entity.getCategoria());
            existing.setPrecioUnitario(entity.getPrecioUnitario());
            return dao.save(existing);
        }).orElseThrow(() -> new RuntimeException("TD_Presupuestos no encontrado"));
    }

    @Override
    public Optional<TD_Presupuestos> obtener(Long id) { return dao.findById(id); }

    @Override
    public Optional<TD_Presupuestos> obtenerPorCodigo(String codigo) { return dao.findByCodigo(codigo); }

    @Override
    public List<TD_Presupuestos> listar() {
        List<TD_Presupuestos> list = new ArrayList<>();
        dao.findAll().forEach(list::add);
        return list;
    }

    @Override
    public void eliminar(Long id) { dao.deleteById(id); }
}
