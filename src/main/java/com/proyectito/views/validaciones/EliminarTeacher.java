package com.proyectito.views.validaciones;

import com.proyectito.repository.models.Profesor;
import com.proyectito.views.ViewMain;

public class EliminarTeacher {
    
    private EliminarTeacher(){}

    public static void eliminar() {
        System.out.println("\n> Ingrese número de documento: ");
        try {
            long documento = ViewMain.leer.nextLong();
            Profesor student = ViewMain.serviceProfesor.buscarDocumProfesor(documento);
            System.out.println(student.toString());

            System.out.println("¿Está seguro de eliminar este estudiante? S/N: ");

            if(ViewMain.leer.next().equalsIgnoreCase("s")) {
               ViewMain.serviceProfesor.elimiarProfesor(student);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
