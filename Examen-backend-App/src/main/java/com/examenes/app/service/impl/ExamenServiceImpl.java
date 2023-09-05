package com.examenes.app.service.impl;

import com.examenes.app.model.Categoria;
import com.examenes.app.model.Examen;
import com.examenes.app.repository.ExamenRepository;
import com.examenes.app.service.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
@Service
@Transactional
public class ExamenServiceImpl implements ExamenService {

    @Autowired
    private ExamenRepository examenRepository;
    @Override
    public Examen crearExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    public Examen actualizarExamen(Examen examen ) {
        return examenRepository.save(examen);
    }

    @Override
    public Set<Examen> listarExamen() {
        return new LinkedHashSet(examenRepository.listarExamenes());

    }

    @Override
    public Examen obtenerExamen(Long examenId) {
        return examenRepository.findByExamenId(examenId);
    }

    @Override
    public void eliminarExamen(Long examenId) {
        examenRepository.deleteById(examenId);
        /*Examen examen = new Examen();
           examen.setExamenId(examenId);
           examenRepository.deleteById(examenId);*/
    }

    @Override
    public List<Examen> listarExamenDeUnaCategoria(Categoria categoria) {
        return this.examenRepository.findByCategoria(categoria);
    }

    @Override
    public List<Examen> obtenerExamenesActivos() {
        return examenRepository.findByActivo(true);
    }

    @Override
    public List<Examen> obtgenerExamenesActivosDeUnaCategoria(Categoria categoria) {
        return examenRepository.findByCategoriaAndActivo(categoria,true);
    }


}
