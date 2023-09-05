package com.examenes.app.repository;

import com.examenes.app.model.Categoria;
import com.examenes.app.model.Examen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {

  /*@Query("SELECT e FROM Examen e JOIN FETCH e.preguntas Where e.examenId=:id")
  Examen findByExamenId(@Param("id") Long id);*/

  @Query("SELECT e FROM Examen e JOIN FETCH e.categoria Where e.examenId=:id")
  Examen findByExamenId(@Param("id") Long id);

  @Query("SELECT e FROM Examen e JOIN FETCH e.categoria")
  List<Examen> listarExamenes();

  public List<Examen> findByCategoria(Categoria categoria);

  public List<Examen> findByActivo(Boolean estado);

  public List<Examen> findByCategoriaAndActivo(Categoria categoria, Boolean estado);





}



