package com.proyectito.views;

import com.proyectito.utils.TextStyles;
import com.proyectito.views.validaciones.ActualizarStudents;
import com.proyectito.views.validaciones.BuscarStudent;
import com.proyectito.views.validaciones.CrearStudent;
import com.proyectito.views.validaciones.EliminarStudent;

public class ViewAlumno extends ViewMain{


    private static final int SALIR = 6;

    public static void startMenu() {

        int op = 0;

        do {

            op = mostrarMenu();

            switch (op) {
                case 1:
                    CrearStudent.crear();
                break;
                case 2:
                    BuscarStudent.buscar(2);
                break;
                case 3:
                    BuscarStudent.buscar(1);
                break;
                case 4:
                    ActualizarStudents.actualizarStud();
                    break;
                case 5:
                    EliminarStudent.eliminar();;
                    break;
                case 6:
                    System.out.println("\nSaliendo...\n");

                    try {
                        Thread.sleep(2000); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    break;
                default:
                    TextStyles.msgError("Opcion no valida");
                    break;
            }

        } while (op != SALIR);
    }



    public static int mostrarMenu() {

        System.out.println("\n");
        TextStyles.prettyTitle("MENU ALUMNO");
        System.out.println("\n");
        TextStyles.opMenu("Crear alumno", 1);
        TextStyles.opMenu("Listar alumnos", 2);
        TextStyles.opMenu("Buscar alumno", 3);
        TextStyles.opMenu("Modificar alumno", 4);
        TextStyles.opMenu("Eliminar alumno", 4);
        TextStyles.opMenu("Salir", SALIR);
        System.out.println("\n");
        System.out.print("-> Opcion: ");
        return leer.nextInt();

    }
}
