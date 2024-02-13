package com.proyectito.services;

import java.util.List;

import com.proyectito.exceptiones.alumnoexceptions.AlumnoNullException;
import com.proyectito.repository.models.Alumno;

public interface ServiceAlumno {
    void crearAlumno(Alumno Alumno);

    void actualizarAlumno(Alumno Alumno);

    Alumno buscarDocumAlumno(long documento) throws AlumnoNullException;
    
    List<Alumno> buscarAlumnos();

    void elimiarAlumno(Alumno Alumno);
}
