package com.proyectito.services;

import java.util.List;

import com.proyectito.exceptiones.cursoexceptions.CursoNullException;
import com.proyectito.repository.models.Curso;

public interface ServiceCurso {

    List<Curso> listar();

    Curso porID(int id) throws CursoNullException;

    void crear(Curso curso);
    
    void editar(Curso curso, int id);
    
    void eliminar(Curso curso, int id);
}
