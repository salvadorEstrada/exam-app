package com.examenes.app.service;

import com.examenes.app.model.Examen;
import com.examenes.app.model.Pregunta;

import java.util.Set;

public interface PreguntaService {
    public Pregunta guardarPregunta(Pregunta pregunta);
    public Pregunta actualizarPregunta(Pregunta pregunta);
    public Set<Pregunta> obtenerPreguntas();
    public Pregunta obtenerPregunta(Long pregunta);
    public Set<Pregunta> obtenerPreguntasDelExamen(Examen examen);
    public void eliminarPregunta(Long preguntaId);
    public Pregunta listarPregunta(Long  preguntaId);

}
