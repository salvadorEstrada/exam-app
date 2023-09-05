package com.examenes.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaId;

    private String titulo;
    private String descripcion;
    //Establecer la relacion
    //Si elimino una categoria, se van a eliminar todos los examenes correspondientes a dicah cat
    @OneToMany(mappedBy = "categoria", cascade=CascadeType.ALL)
    @JsonIgnore
    private Set<Examen> examenes= new HashSet<>();

    public Categoria(){}

    public Categoria(Long categoriaId, String titulo, String descripcion, Set<Examen> examenes) {
        this.categoriaId = categoriaId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.examenes = examenes;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
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

    public Set<Examen> getExamenes() {
        return examenes;
    }

    public void setExamenes(Set<Examen> examenes) {
        this.examenes = examenes;
    }
}
