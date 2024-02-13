package com.proyectito.repository;

import java.util.List;

import com.proyectito.repository.models.Alumno;

public interface RepositoryAlumno {
   
    void crearAlumno(Alumno Alumno);

    void actualizarAlumno(Alumno Alumno);

    Alumno buscarDocumAlumno(long documento);
    
    List<Alumno> buscarAlumnos();

    void elimiarAlumno(Alumno Alumno);
}
