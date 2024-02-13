package com.proyectito.repository;

import java.util.List;

import com.proyectito.repository.models.Profesor;

public interface RepositoryProfesor {

    void crearProfesor(Profesor Profesor);

    void actualizarProfesor(Profesor Profesor);

    Profesor buscarDocumProfesor(long documento);
    
    List<Profesor> buscarProfesors();

    void elimiarProfesor(Profesor Profesor);
}
