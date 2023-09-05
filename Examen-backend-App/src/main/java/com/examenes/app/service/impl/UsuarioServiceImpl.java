package com.examenes.app.service.impl;

import com.examenes.app.excepciones.UsuarioFoundException;
import com.examenes.app.model.Usuario;
import com.examenes.app.model.UsuarioRol;
import com.examenes.app.repository.RolRepository;
import com.examenes.app.repository.UsuarioRepository;
import com.examenes.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

        @Autowired
        private UsuarioRepository usuarioRepository ;

        @Autowired
        private RolRepository rolRepository;
        @Override
        public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
            Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
            if(usuarioLocal !=null){
                System.out.println("El usuario ya existe");
                throw new UsuarioFoundException("El usuario ya está presente");

            }else{
                for(UsuarioRol usuarioRol:usuarioRoles){
                    rolRepository.save(usuarioRol.getRol());//guardo el rol que le estoy pasandp
                }
                usuario.getUsuarioRoles().addAll(usuarioRoles);
                usuarioLocal=usuarioRepository.save(usuario);
            }
            return usuarioLocal;
        }

        @Override
        public Usuario ObtenerUsuario(String usuario) {

            return usuarioRepository.findByUsername(usuario);
        }

        @Override
        public void eliminarUsuario(Long usuarioId) {
            usuarioRepository.deleteById(usuarioId);

        }
}
