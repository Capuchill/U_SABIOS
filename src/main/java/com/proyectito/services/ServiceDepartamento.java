package com.proyectito.services;

import java.util.List;

import com.proyectito.exceptiones.departamentoexceptions.DepartamentoNullException;
import com.proyectito.repository.models.Departamento;

public interface ServiceDepartamento {

    List<Departamento> listar();

    Departamento porID(int id) throws DepartamentoNullException;

    void crear(Departamento depart);
    
    void editar(Departamento depart, int id);
    
    void eliminar(Departamento depart, int id);
    
}
