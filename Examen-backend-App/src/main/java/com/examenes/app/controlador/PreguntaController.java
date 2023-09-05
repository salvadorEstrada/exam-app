package com.examenes.app.controlador;

import com.examenes.app.model.Categoria;
import com.examenes.app.model.Examen;
import com.examenes.app.model.Pregunta;
import com.examenes.app.repository.PreguntaRepository;
import com.examenes.app.service.CategoriaService;
import com.examenes.app.service.ExamenService;
import com.examenes.app.service.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/pregunta")
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;

    @Autowired
    private ExamenService examenService;

    @PostMapping("/")
    public ResponseEntity<Pregunta> guardarPregunta(@RequestBody Pregunta pregunta) {
        return new ResponseEntity<>(preguntaService.guardarPregunta(pregunta), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Pregunta> actualizarPregunta(@RequestBody Pregunta pregunta) {
        return new ResponseEntity<>(preguntaService.actualizarPregunta(pregunta), HttpStatus.CREATED);
    }

    @GetMapping("/examen/{examenId}")
    private ResponseEntity<?> listarPreguntasExamen(@PathVariable Long examenId) {
        Examen examen = examenService.obtenerExamen(examenId); //2 preguntas
        Set<Pregunta> preguntas = examen.getPreguntas();//6 preguntas
        List examenes = new ArrayList(preguntas);//guardamos 6
        if (examenes.size() > Integer.parseInt(examen.getNumeroDePreguntas())) {
            examenes = examenes.subList(0, Integer.parseInt(examen.getNumeroDePreguntas() + 1));

        }//6=6.subList(0,3)
        Collections.shuffle(examenes);//barajear
        return ResponseEntity.ok(examenes);
    }

    @GetMapping("/{preguntaId}")
    public Pregunta listarPreguntaPorId(@PathVariable Long preguntaId){
        return preguntaService.obtenerPregunta(preguntaId);
   }

   @DeleteMapping("/{preguntaId}")
    public void eliminarPregunta(@PathVariable("preguntaId")Long preguntaId){
       preguntaService.eliminarPregunta(preguntaId);
   }
  @GetMapping("/examen/todos/{examenId}")
  public ResponseEntity<?> listarPreguntasDelExamenAdmin(@PathVariable Long examenId){
    Examen  examen = new Examen();
    examen.setExamenId(examenId);
    Set<Pregunta> preguntas =preguntaService.obtenerPreguntasDelExamen(examen);
    return ResponseEntity.ok(preguntas);
  }

  @PostMapping("/evaluar-examen")
    public ResponseEntity<?> evaluarExamen(@RequestBody List<Pregunta> preguntas){
        double puntosMaximos =0;
        Integer respuestasCorrectas=0;
        Integer intentos=0;

        for(Pregunta p: preguntas){
            Pregunta pregunta= this.preguntaService.listarPregunta(p.getPreguntaId());
            if(pregunta.getRespuesta()==pregunta.getRespuestaDada()){
                respuestasCorrectas++;
                double puntos =Double.parseDouble(preguntas.get(0).getExamen().getPuntosMax())/ preguntas.size();
                puntosMaximos +=puntos;
            }
            if(p.getRespuestaDada()!=null){
                intentos++;
            }
        }

        Map<String, Object> respuestas= new HashMap<>();
        respuestas.put("puntosMaximos",puntosMaximos);
        respuestas.put("respuestasCorrectas", respuestasCorrectas);
        respuestas.put("intentos",intentos);
        return ResponseEntity.ok(respuestas);
  }


}
