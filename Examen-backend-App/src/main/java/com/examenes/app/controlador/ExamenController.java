package com.examenes.app.controlador;
import com.examenes.app.model.Categoria;
import com.examenes.app.model.Examen;
import com.examenes.app.service.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/examen")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @PostMapping("/")
    public ResponseEntity<Examen> guardarExamen(@RequestBody Examen examen){
        Examen guardadaExa= examenService.crearExamen(examen);
        return new ResponseEntity<>(guardadaExa, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Examen> actualizarExamen(@RequestBody Examen examen){
        return  new ResponseEntity<>(examenService.actualizarExamen(examen),HttpStatus.OK);
    }

    @GetMapping("/{examenId}")
    public Examen listExaPorId(@PathVariable Long examenId){
        return examenService.obtenerExamen(examenId);
    }

    @GetMapping("/")
    public ResponseEntity<?> listarExamenes(){
        return ResponseEntity.ok(examenService.listarExamen());
    }


    @DeleteMapping("/{examenId}")
    public void eliminarExamen(@PathVariable Long examenId){
        examenService.eliminarExamen(examenId);
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Examen>>  listarExamensDeUnaCategoria(@PathVariable Long categoriaId){
        Categoria categoria= new Categoria();
        categoria.setCategoriaId(categoriaId);
        return new ResponseEntity(examenService.listarExamenDeUnaCategoria(categoria),HttpStatus.OK);
    }
    @GetMapping("/activo")
    public List<Examen> listarExamenesActivos(){
        return examenService.obtenerExamenesActivos();
    }

    @GetMapping("/categoria/activo/{categoriaId}")
    public List<Examen> listarExamenesActivosDeUnaCategoria(@PathVariable Long categoriaId){
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(categoriaId);
        return examenService.obtgenerExamenesActivosDeUnaCategoria(categoria);

    }
}
