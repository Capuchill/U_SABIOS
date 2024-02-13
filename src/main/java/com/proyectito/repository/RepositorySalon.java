package com.proyectito.repository;

import java.util.List;

import com.proyectito.repository.models.Salon;

public interface RepositorySalon {
    
    List<Salon> listar();

    Salon porId(int ID);

    Salon porReferencia(String ref);

    void crear(Salon salon);

    void editar(Salon salon, int id);

    void eliminar(Salon salon, int id);
    
}  


