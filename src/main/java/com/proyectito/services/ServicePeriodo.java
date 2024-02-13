package com.proyectito.services;

import java.util.List;

import com.proyectito.exceptiones.periodoexceptions.PeriodoNullException;
import com.proyectito.repository.models.Periodo;


public interface ServicePeriodo {

    List<Periodo> listar();

    Periodo porID(int id) throws PeriodoNullException;

    void crear(Periodo period);
    
    void editar(Periodo period, int id);
    
    void eliminar(Periodo period, int id);
    
} 
