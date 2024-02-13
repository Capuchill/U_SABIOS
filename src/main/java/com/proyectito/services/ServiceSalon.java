package com.proyectito.services;

import java.util.List;

import com.proyectito.exceptiones.salonexceptions.SalonNullException;
import com.proyectito.repository.models.Salon;

public interface ServiceSalon {
    
    
    List<Salon> listar();

    Salon porID(int id) throws SalonNullException;

    void crear(Salon salon);
    
    void editar(Salon salon, int id);
    
    void eliminar(Salon salon, int id);
    
} 
