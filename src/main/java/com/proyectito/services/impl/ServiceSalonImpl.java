package com.proyectito.services.impl;

import java.util.List;

import com.proyectito.exceptiones.salonexceptions.SalonNullException;
import com.proyectito.repository.RepositorySalon;
import com.proyectito.repository.models.Salon;
import com.proyectito.services.ServiceSalon;

public class ServiceSalonImpl implements ServiceSalon{

    private final RepositorySalon crudRepositorySalon;

    public ServiceSalonImpl(RepositorySalon crudRepositorySalon) {
        this.crudRepositorySalon = crudRepositorySalon;
    }

    @Override
    public List<Salon> listar() {
        return this.crudRepositorySalon.listar();
    }

    @Override
    public Salon porID(int id) throws SalonNullException {
         Salon salon = this.crudRepositorySalon.porId(id);

        if (salon!=null) {
            return salon;
        }else{
            throw new SalonNullException("Vaya! no se ha encontrado el salon solicitado");
        }
    }

    @Override
    public void crear(Salon salon) {
        this.crudRepositorySalon.crear(salon);
    }

    @Override
    public void editar(Salon salon, int id) {
        this.crudRepositorySalon.editar(salon, id);
    }

    @Override
    public void eliminar(Salon salon, int id) {
        this.crudRepositorySalon.eliminar(salon, id);
    }
    
}
