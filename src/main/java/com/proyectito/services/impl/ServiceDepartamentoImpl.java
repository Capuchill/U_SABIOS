package com.proyectito.services.impl;

import java.util.List;

import com.proyectito.exceptiones.departamentoexceptions.DepartamentoNullException;
import com.proyectito.repository.RepositoryDepartamento;
import com.proyectito.repository.models.Departamento;
import com.proyectito.services.ServiceDepartamento;

public class ServiceDepartamentoImpl implements ServiceDepartamento{

    private final RepositoryDepartamento crudRepositoryDepartamento;

    public ServiceDepartamentoImpl(RepositoryDepartamento crudRepositoryDepartamento){
        this.crudRepositoryDepartamento = crudRepositoryDepartamento;
    }
    
    @Override
    public List<Departamento> listar() {
        return this.crudRepositoryDepartamento.listar();
    }

    @Override
    public Departamento porID(int id) throws DepartamentoNullException {
        Departamento depart = this.crudRepositoryDepartamento.porId(id);

        if (depart!=null) {
            return depart;
        }else{
            throw new DepartamentoNullException("Vaya! no se ha encontrado el departamento solicitado");
        }
    }

    @Override
    public void crear(Departamento depart) {
        this.crudRepositoryDepartamento.crear(depart);
    }

    @Override
    public void editar(Departamento depart, int id) {
        this.crudRepositoryDepartamento.editar(depart,id);
    }

    @Override
    public void eliminar(Departamento depart, int id) {
        this.crudRepositoryDepartamento.eliminar(depart,id);
    }
    
}
