package com.proyectito.views;

import com.proyectito.utils.TextStyles;
import com.proyectito.views.validaciones.ActualizarTeacher;
import com.proyectito.views.validaciones.BuscarSTeacher;
import com.proyectito.views.validaciones.CrearTeacher;
import com.proyectito.views.validaciones.EliminarTeacher;

public class ViewProfesor extends ViewMain{
    
    private static final int SALIR = 6;

    public static void startMenu() {

        int op = 0;

        do {

            op = mostrarMenu();

            switch (op) {
                case 1:
                    CrearTeacher.crear();
                break;
                case 2:
                    BuscarSTeacher.buscar(2);
                break;
                case 3:
                    BuscarSTeacher.buscar(1);
                break;
                case 4:
                    ActualizarTeacher.actualizarStud();
                    break;
                case 5:
                    EliminarTeacher.eliminar();
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
        TextStyles.prettyTitle("MENU PROFESOR");
        System.out.println("\n");
        TextStyles.opMenu("Crear profesor", 1);
        TextStyles.opMenu("Listar profesores", 2);
        TextStyles.opMenu("Buscar profesor", 3);
        TextStyles.opMenu("Modificar profesor", 4);
        TextStyles.opMenu("Eliminar profesor", 4);
        TextStyles.opMenu("Salir", SALIR);
        System.out.println("\n");
        System.out.print("-> Opcion: ");
        return leer.nextInt();

    }
}
