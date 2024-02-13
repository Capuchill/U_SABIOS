package com.proyectito.services.impl;

import java.util.List;

import com.proyectito.exceptiones.periodoexceptions.PeriodoNullException;
import com.proyectito.repository.RepositoryPeriodo;
import com.proyectito.repository.models.Periodo;
import com.proyectito.services.ServicePeriodo;

public class ServicePeriodoImpl implements ServicePeriodo {

     private final RepositoryPeriodo crudRepositoryPeriodo;

    public ServicePeriodoImpl(RepositoryPeriodo crudRepositoryPeriodo) {
        this.crudRepositoryPeriodo = crudRepositoryPeriodo;
    }

    @Override
    public List<Periodo> listar() {
        return this.crudRepositoryPeriodo.listar();
    }

    @Override
    public Periodo porID(int id) throws PeriodoNullException {
      Periodo periodo = this.crudRepositoryPeriodo.porId(id);

        if (periodo!=null) {
            return periodo;
        }else{
            throw new PeriodoNullException("Vaya! no se ha encontrado elPeriodo solicitado");
        }
    }

    @Override
    public void crear(Periodo period) {
        this.crudRepositoryPeriodo.crear(period);
    }

    @Override
    public void editar(Periodo period, int id) {
        this.crudRepositoryPeriodo.editar(period, id);
    }

    @Override
    public void eliminar(Periodo period, int id) {
        this.crudRepositoryPeriodo.eliminar(period, id);
    }

    
   
    
}
