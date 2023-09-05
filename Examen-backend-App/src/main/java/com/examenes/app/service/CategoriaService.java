package com.examenes.app.service;

import com.examenes.app.model.Categoria;

import java.util.Set;

public interface CategoriaService {
    public Categoria guardarCategoria(Categoria categoria);

    public Categoria actualizarCategoria(Categoria categoria);

    public Set<Categoria> obtenerCategorias();

    public Categoria obtenerCategoria(Long categoriaId);

    public void eliminarCategoria(Long categoriaId);
}
