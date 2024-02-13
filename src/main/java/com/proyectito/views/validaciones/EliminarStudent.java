package com.proyectito.views.validaciones;


import com.proyectito.repository.models.Alumno;
import com.proyectito.views.ViewMain;

public class EliminarStudent {
    
    private EliminarStudent(){}

    public static void eliminar() {
        System.out.println("\n> Ingrese número de documento: ");
        try {
            long documento = ViewMain.leer.nextLong();
            Alumno student = ViewMain.serviceAlumno.buscarDocumAlumno(documento);
            System.out.println(student.toString());

            System.out.println("¿Está seguro de eliminar este estudiante? S/N: ");

            if(ViewMain.leer.next().equalsIgnoreCase("s")) {
               ViewMain.serviceAlumno.elimiarAlumno(student);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
