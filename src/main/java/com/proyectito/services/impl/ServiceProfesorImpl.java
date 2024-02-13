package com.proyectito.services.impl;

import java.util.List;

import com.proyectito.exceptiones.profesorexceptions.ProfesorNullException;
import com.proyectito.repository.RepositoryProfesor;
import com.proyectito.repository.models.Profesor;
import com.proyectito.services.ServiceProfesor;

public class ServiceProfesorImpl implements ServiceProfesor{

   private final RepositoryProfesor crudRepositoryProfesor;

    

    public ServiceProfesorImpl(RepositoryProfesor crudRepositoryProfesor) {
        this.crudRepositoryProfesor = crudRepositoryProfesor;
    }

    @Override
    public void crearProfesor(Profesor Profesor) {
       this.crudRepositoryProfesor.crearProfesor(Profesor);
    }

    @Override
    public void actualizarProfesor(Profesor Profesor) {
       this.crudRepositoryProfesor.actualizarProfesor(Profesor);
    }

    @Override
    public Profesor buscarDocumProfesor(long documento) throws ProfesorNullException{
       Profesor alum = this.crudRepositoryProfesor.buscarDocumProfesor(documento);

        if (alum!=null) {
            return alum;
        }else{
            throw new ProfesorNullException("Vaya! no se ha encontrado el estudiante solicitado");
        }
    }

    @Override
    public List<Profesor> buscarProfesors() {
        return this.crudRepositoryProfesor.buscarProfesors();
    }

    @Override
    public void elimiarProfesor(Profesor Profesor) {
        this.elimiarProfesor(Profesor);
    }
    
}
