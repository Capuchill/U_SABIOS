package com.unisabio.repository;

import java.util.List;

import com.unisabio.repository.models.models_extends.Teacher;

public interface RepositoryTeacher {

    void crearTeacher(Teacher teacher);

    void actualizarTeacher(Teacher teacher);

    Teacher buscarDocumTeacher(long documento);
    
    List<Teacher> buscarTeachers();

    void elimiarTeacher(Teacher teacher);

}
