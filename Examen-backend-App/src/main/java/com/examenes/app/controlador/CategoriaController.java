package com.examenes.app.controlador;

import com.examenes.app.model.Categoria;
import com.examenes.app.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/")
    public ResponseEntity<Categoria> guardarCategoria(@RequestBody Categoria categoria){
        Categoria catGuardada= categoriaService.guardarCategoria(categoria);
        return new ResponseEntity<>(catGuardada, HttpStatus.CREATED);
    }

    @GetMapping("/{categoriaId}")
    public Categoria listCatPorId(@PathVariable Long categoriaId){
        return categoriaService.obtenerCategoria(categoriaId);
    }

    @GetMapping("/")
    public ResponseEntity<?> listarCategorias(){
        return ResponseEntity.ok(categoriaService.obtenerCategorias());
    }
    @PutMapping()
    public ResponseEntity<Categoria> actualizarCategoria(@RequestBody Categoria categoria){
        return  new ResponseEntity<>(categoriaService.actualizarCategoria(categoria),HttpStatus.OK);
    }

    @DeleteMapping("/{categoriaId}")
    public void eliminarCategoria(@PathVariable Long categoriaId){
        categoriaService.eliminarCategoria(categoriaId);
    }
}
