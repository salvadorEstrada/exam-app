package com.examenes.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="examenes")
@Transactional
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examenId;

    private String titulo;
    private String descripcion;
    private String puntosMax;
    private String numeroDePreguntas;
    private boolean activo=false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;

    //Si elimino un examen, se eliminan las preguntas correspondiente al examen
    //Con lazy, la coleccion de preguntas dentro de examen es lazy
    @OneToMany(mappedBy = "examen", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Pregunta> preguntas;

    public Examen(){}

    public Examen(Long examenId, String titulo, String descripcion, String puntosMax, String numeroDePreguntas, boolean activo, Categoria categoria, Set<Pregunta> preguntas) {
        this.examenId = examenId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.puntosMax = puntosMax;
        this.numeroDePreguntas = numeroDePreguntas;
        this.activo = activo;
        this.categoria = categoria;
        this.preguntas = preguntas;
    }

    public Long getExamenId() {
        return examenId;
    }

    public void setExamenId(Long examenId) {
        this.examenId = examenId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPuntosMax() {
        return puntosMax;
    }

    public void setPuntosMax(String puntosMax) {
        this.puntosMax = puntosMax;
    }

    public String getNumeroDePreguntas() {
        return numeroDePreguntas;
    }

    public void setNumeroDePreguntas(String numeroDePreguntas) {
        this.numeroDePreguntas = numeroDePreguntas;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Set<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
}
