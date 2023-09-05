package com.examenes.app.service;

import com.examenes.app.model.Categoria;
import com.examenes.app.model.Examen;

import java.util.List;
import java.util.Set;

public interface ExamenService {
    public Examen crearExamen(Examen examen);
    public Examen actualizarExamen(Examen examen);
    public Set<Examen> listarExamen();
    public Examen obtenerExamen(Long examenId);
    public void eliminarExamen(Long examenId);

    public List<Examen> listarExamenDeUnaCategoria(Categoria categoria);

    public List<Examen> obtenerExamenesActivos();

    public List<Examen> obtgenerExamenesActivosDeUnaCategoria(Categoria categoria);



}
