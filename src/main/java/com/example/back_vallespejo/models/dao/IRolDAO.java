package com.example.back_vallespejo.models.dao;

import com.example.back_vallespejo.models.entities.Rol;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface IRolDAO extends CrudRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
}
