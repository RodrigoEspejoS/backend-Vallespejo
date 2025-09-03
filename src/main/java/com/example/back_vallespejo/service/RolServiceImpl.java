package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.dao.IRolDAO;
import com.example.back_vallespejo.models.entities.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    private IRolDAO rolDAO;

    @Override
    public Rol registrarRol(Rol rol) {
        return rolDAO.save(rol);
    }

    @Override
    public List<Rol> getAll() {
        List<Rol> roles = new ArrayList<>();
        rolDAO.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Rol findById(Long id) {
        return rolDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(Rol rol) {
        rolDAO.delete(rol);
    }
}
