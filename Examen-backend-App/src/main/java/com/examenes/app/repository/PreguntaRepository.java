package com.examenes.app.repository;

import com.examenes.app.model.Examen;
import com.examenes.app.model.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
    //Buscar preguntas de un examen
    Set<Pregunta> findByExamen(Examen examen);
}
