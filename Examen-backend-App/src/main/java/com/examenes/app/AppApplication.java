package com.examenes.app;

import com.examenes.app.excepciones.UsuarioFoundException;
import com.examenes.app.model.Rol;
import com.examenes.app.model.Usuario;
import com.examenes.app.model.UsuarioRol;
import com.examenes.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);

	}

	//Para evitar el error de cors en el front end cuando se hace una petición
	@Override
	public void run(String... args) throws Exception {
		/*try {
			Usuario usuario = new Usuario();
			usuario.setNombre("Salvador");
			usuario.setApellido("Estrada");
			usuario.setUsername("salva");
			usuario.setPassword(bCryptPasswordEncoder.encode("12345"));
			usuario.setEmail("s1@gmail.com");
			usuario.setTelefono("123456");
			usuario.setPerfil("foto.png");
			//Rol Asignar
			Rol rol = new Rol();
			rol.setId(1L);
			rol.setNombre("ADMIN");
			//Relacionar Usuario con Rol

			Set<UsuarioRol> usuarioRoles = new HashSet<>();
			UsuarioRol usuarioRol = new UsuarioRol();
			usuarioRol.setRol(rol);
			usuarioRol.setUsuario(usuario);
			usuarioRoles.add(usuarioRol);
			//guardar en la base de datos

			Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario, usuarioRoles);

			System.out.println(usuarioGuardado.getUsername());
		}catch(UsuarioFoundException exception){
			exception.printStackTrace();
		}*/

	}
	//Solo hay que tener cuidado con los /**, ya que en producción esto puede representar una bulneravilidad
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST","PUT", "DELETE");
			}
		};
	}

}
