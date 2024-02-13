package com.unisabio.views.logic_views.logic_students;

import com.unisabio.Main;
import com.unisabio.repository.models.models_extends.Student;

public class EliminarStudent {
    
    private EliminarStudent(){}

    public static void eliminar() {
        System.out.println("\n> Ingrese número de documento: ");
        try {
            long documento = Main.input.nextLong();
            Student student = Main.studentServices.buscarDocumStudent(documento);
            System.out.println(student.toString());

            System.out.println("¿Está seguro de eliminar este estudiante? S/N: ");

            if(Main.input.next().equalsIgnoreCase("s")) {
                Main.studentServices.elimiarStudent(student);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
