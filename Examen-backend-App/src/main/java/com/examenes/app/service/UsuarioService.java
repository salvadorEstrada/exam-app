package com.examenes.app.service;

import com.examenes.app.model.Usuario;
import com.examenes.app.model.UsuarioRol;

import java.util.Set;

public interface UsuarioService {
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    public Usuario ObtenerUsuario(String usuario);

    public void eliminarUsuario(Long usuarioId);
}
