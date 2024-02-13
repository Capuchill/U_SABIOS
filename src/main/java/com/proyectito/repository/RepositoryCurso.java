package com.proyectito.repository;

import java.util.List;

import com.proyectito.repository.models.Curso;

public interface RepositoryCurso {

    List<Curso> listar();

    Curso porId(int ID);

    void crear(Curso curso);

    void editar(Curso curso, int id);

    void eliminar(Curso curso, int id);
    
} 
    

