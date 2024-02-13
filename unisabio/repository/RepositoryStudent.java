package com.unisabio.repository;

import java.util.List;

import com.unisabio.repository.models.models_extends.Student;

public interface RepositoryStudent {

    void crearStudent(Student student);

    void actualizarStudent(Student student);

    Student buscarDocumStudent(long documento);
    
    List<Student> buscarStudents();

    void elimiarStudent(Student student);

}
