package com.proyectito.repository;

import java.util.List;

import com.proyectito.repository.models.Periodo;

public interface RepositoryPeriodo {
    
    List<Periodo> listar();

    Periodo porId(int ID);

    void crear(Periodo periodo);

    void editar(Periodo periodo, int id);

    void eliminar(Periodo periodo, int id);
    
} 