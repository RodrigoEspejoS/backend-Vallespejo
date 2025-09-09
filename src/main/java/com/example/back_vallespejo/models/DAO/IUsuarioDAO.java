package com.example.back_vallespejo.models.DAO;

import com.example.back_vallespejo.models.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {
}
