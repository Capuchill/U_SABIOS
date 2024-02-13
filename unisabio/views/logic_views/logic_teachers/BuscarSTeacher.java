package com.unisabio.views.logic_views.logic_teachers;

import java.util.List;

import com.unisabio.Main;
import com.unisabio.repository.models.models_extends.Teacher;

public class BuscarSTeacher {
    
    private BuscarSTeacher(){}

    public static void buscar(int sel){
        switch (sel) {
            case 1:
                buscarDocument();
                break;

            case 2:
                todosTeachers();
                break;
        
            default:
                break;
        }
    }

    private static void buscarDocument() {
        System.out.println("\n> Ingrese número de documento: ");
        try {
            long documento = Main.input.nextLong();
            Teacher teacher = Main.teacherServices.buscarDocumTeacher(documento);
            System.out.println(teacher.toString());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void todosTeachers() {
        try {
            List<Teacher> teachers = Main.teacherServices.buscarTeachers();

            teachers.forEach(teacher -> System.out.println(teacher.toString()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
