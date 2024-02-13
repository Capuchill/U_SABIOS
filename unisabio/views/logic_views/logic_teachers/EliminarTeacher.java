package com.unisabio.views.logic_views.logic_teachers;

import com.unisabio.Main;
import com.unisabio.repository.models.models_extends.Teacher;

public class EliminarTeacher {
    
    private EliminarTeacher(){}

    public static void eliminar() {
        System.out.println("\n> Ingrese número de documento: ");
        try {
            long documento = Main.input.nextLong();
            Teacher teacher = Main.teacherServices.buscarDocumTeacher(documento);
            System.out.println(teacher.toString());

            System.out.println("¿Está seguro de eliminar este estudiante? S/N: ");

            if(Main.input.next().equalsIgnoreCase("s")) {
                Main.teacherServices.elimiarTeacher(teacher);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
