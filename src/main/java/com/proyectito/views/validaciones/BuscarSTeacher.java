package com.proyectito.views.validaciones;

import java.util.List;

import com.proyectito.repository.models.Profesor;
import com.proyectito.views.ViewMain;

public class BuscarSTeacher {
    
    private BuscarSTeacher(){}

    public static void buscar(int sel){
        switch (sel) {
            case 1:
                buscarDocument();
                break;

            case 2:
                todosEstudents();
                break;
        
            default:
                break;
        }
    }

    private static void buscarDocument() {
        System.out.println("\n> Ingrese número de documento: ");
        try {
            long documento = ViewMain.leer.nextLong();
            Profesor student = ViewMain.serviceProfesor.buscarDocumProfesor(documento);
            System.out.println(student.toString());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void todosEstudents() {
        try {
            List<Profesor> students = ViewMain.serviceProfesor.buscarProfesors();
            System.out.println(students);
            students.forEach(student -> System.out.println(student.toString()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
