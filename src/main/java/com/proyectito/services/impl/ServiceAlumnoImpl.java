package com.proyectito.services.impl;

import java.util.List;

import com.proyectito.exceptiones.alumnoexceptions.AlumnoNullException;
import com.proyectito.repository.RepositoryAlumno;
import com.proyectito.repository.models.Alumno;
import com.proyectito.services.ServiceAlumno;

public class ServiceAlumnoImpl implements ServiceAlumno {

    private final RepositoryAlumno crudRepositoryAlumno;

    

    public ServiceAlumnoImpl(RepositoryAlumno crudRepositoryAlumno) {
        this.crudRepositoryAlumno = crudRepositoryAlumno;
    }

    @Override
    public void crearAlumno(Alumno Alumno) {
       this.crudRepositoryAlumno.crearAlumno(Alumno);
    }

    @Override
    public void actualizarAlumno(Alumno Alumno) {
       this.crudRepositoryAlumno.actualizarAlumno(Alumno);
    }

    @Override
    public Alumno buscarDocumAlumno(long documento) throws AlumnoNullException{
       Alumno alum = this.crudRepositoryAlumno.buscarDocumAlumno(documento);

        if (alum!=null) {
            return alum;
        }else{
            throw new AlumnoNullException("Vaya! no se ha encontrado el estudiante solicitado");
        }
    }

    @Override
    public List<Alumno> buscarAlumnos() {
        return this.crudRepositoryAlumno.buscarAlumnos();
    }

    @Override
    public void elimiarAlumno(Alumno Alumno) {
        this.elimiarAlumno(Alumno);
    }
    
}
