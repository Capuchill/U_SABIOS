package com.proyectito.repository;

import java.util.List;

import com.proyectito.repository.models.Departamento;

public interface RepositoryDepartamento {

    List<Departamento> listar();

    Departamento porId(int ID);

    void crear(Departamento departamento);

    void editar(Departamento departamento, int id);

    void eliminar(Departamento departamento, int id);

}
