package com.examenes.app.service.impl;

import com.examenes.app.model.Categoria;
import com.examenes.app.repository.CategoriaRepository;
import com.examenes.app.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }







    @Override
    public Categoria actualizarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Set<Categoria> obtenerCategorias() {
        return new LinkedHashSet<>(categoriaRepository.findAll());
    }

    @Override
    public Categoria obtenerCategoria(Long categoriaId) {
        return categoriaRepository.findById(categoriaId)
                .orElseThrow(null);

    }

    @Override
    public void eliminarCategoria(Long categoriaId) {
        categoriaRepository.deleteById(categoriaId);
        /*Categoria categoria = new Categoria();
        categoria.setCategoriaId(categoriaId);
        categoriaRepository.delete(categoria);*/
    }



}
