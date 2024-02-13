package com.proyectito.services.impl;

import java.util.List;

import com.proyectito.exceptiones.cursoexceptions.CursoNullException;
import com.proyectito.repository.RepositoryCurso;
import com.proyectito.repository.models.Curso;
import com.proyectito.services.ServiceCurso;

public class ServiceCursoImpl implements ServiceCurso {

    private final RepositoryCurso crudRepositoryCurso;

    public ServiceCursoImpl(RepositoryCurso crudRepositoryCurso) {
        this.crudRepositoryCurso = crudRepositoryCurso;
    }

    @Override
    public List<Curso> listar() {
        return this.crudRepositoryCurso.listar();
    }

    @Override
    public Curso porID(int id) throws CursoNullException {
         Curso curso = this.crudRepositoryCurso.porId(id);

        if (curso!=null) {
            return curso;
        }else{
            throw new CursoNullException("Vaya! no se ha encontrado el curso solicitado");
        }
    }

    @Override
    public void crear(Curso Curso) {
        this.crudRepositoryCurso.crear(Curso);
    }

    @Override
    public void editar(Curso Curso, int id) {
        this.crudRepositoryCurso.editar(Curso, id);
    }

    @Override
    public void eliminar(Curso Curso, int id) {
        this.crudRepositoryCurso.eliminar(Curso, id);
    }
    
}
