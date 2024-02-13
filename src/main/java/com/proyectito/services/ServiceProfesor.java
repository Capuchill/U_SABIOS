package com.proyectito.services;

import java.util.List;

import com.proyectito.exceptiones.profesorexceptions.ProfesorNullException;
import com.proyectito.repository.models.Profesor;

public interface ServiceProfesor {

    
    void crearProfesor(Profesor Profesor);

    void actualizarProfesor(Profesor Profesor);

    Profesor buscarDocumProfesor(long documento) throws ProfesorNullException;
    
    List<Profesor> buscarProfesors();

    void elimiarProfesor(Profesor Profesor);
}
