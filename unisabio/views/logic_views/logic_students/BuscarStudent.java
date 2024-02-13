package com.unisabio.views.logic_views.logic_students;

import java.util.List;

import com.unisabio.Main;
import com.unisabio.repository.models.models_extends.Student;

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
            long documento = Main.input.nextLong();
            Student student = Main.studentServices.buscarDocumStudent(documento);
            System.out.println(student.toString());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void todosEstudents() {
        try {
            List<Student> students = Main.studentServices.buscarStudents();

            students.forEach(student -> System.out.println(student.toString()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
