package com.examenes.app.service.impl;

import com.examenes.app.model.Usuario;
import com.examenes.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
//Cuando se crea esta clase e implementa de UserDetailsService, spring ya no me proporcionará el usuario por defecto
//Ahora debo de implemntar mi propio usuario
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    //Busca usuario por su username,una vez que ha sido guardado en la BD
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if(usuario==null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return usuario;
    }

}
