package com.examenes.app.controlador;

import com.examenes.app.excepciones.UsuarioNotFoundException;
import com.examenes.app.model.JwtRequest;
import com.examenes.app.model.JwtResponse;
import com.examenes.app.model.Usuario;
import com.examenes.app.security.JwtUtil;
import com.examenes.app.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
 @Autowired
private AuthenticationManager authenticationManager;

   @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/generate-token")
    public ResponseEntity<?> crearToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            auhenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
        }catch(UsuarioNotFoundException exception){
            exception.printStackTrace();
         throw new Exception("Usuario no encontrado");
        }
        UserDetails userDetails= this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);
        System.out.println(token);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void auhenticar(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException disabledException) {
            throw new Exception("Usuario deshabilitado" + disabledException.getMessage());
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("Credenciales inválidas" + badCredentialsException.getMessage());
        }
    }
   //Devuelve el usuario actual
  // @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/usuario-actual")
    public Usuario obtenerUsaurioActual(Principal principal){
        return (Usuario) userDetailsService.loadUserByUsername(principal.getName());
    }
}
