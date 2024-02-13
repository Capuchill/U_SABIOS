package com.proyectito.views.validaciones;



import java.util.List;

import com.proyectito.repository.models.Alumno;
import com.proyectito.views.ViewMain;


public class BuscarStudent {
    
    private BuscarStudent(){}

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
            Alumno student = ViewMain.serviceAlumno.buscarDocumAlumno(documento);
            System.out.println(student.toString());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void todosEstudents() {
        try {
            List<Alumno> students = ViewMain.serviceAlumno.buscarAlumnos();
            System.out.println(students);
            students.forEach(student -> System.out.println(student.toString()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
