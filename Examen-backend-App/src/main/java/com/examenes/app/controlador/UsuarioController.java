package com.examenes.app.controlador;

import com.examenes.app.model.Rol;
import com.examenes.app.model.Usuario;
import com.examenes.app.model.UsuarioRol;
import com.examenes.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception {
        usuario.setPerfil("default.png");
        //Encriptar password del usuario
        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        Set<UsuarioRol> usuarioRoles = new HashSet<>();
        Rol rol= new Rol();
        rol.setId(2L);
        rol.setNombre("STANDAR");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRoles.add(usuarioRol);

        return usuarioService.guardarUsuario(usuario, usuarioRoles);

    }

    @GetMapping("/{username}")
    public Usuario ObtenerUsuario(@PathVariable String username){
        return usuarioService.ObtenerUsuario(username);
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable Long usuarioId){
        usuarioService.eliminarUsuario(usuarioId);
    }



}
