package com.example.back_vallespejo.service;

import com.example.back_vallespejo.models.entities.Usuario;
import com.example.back_vallespejo.models.dto.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> getAll();
    public Usuario registrarUsuarioDTO(UsuarioDTO usuarioDTO);
    public Usuario findById(Long id);
    public Usuario findByEmail(String email);
    public void delete(Usuario usuario);
}
