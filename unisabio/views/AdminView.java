package com.unisabio.views;

import com.unisabio.Main;
import com.unisabio.exceptions.errors.ErrorU;
import com.unisabio.views.logic_views.logic_students.ActualizarStudents;
import com.unisabio.views.logic_views.logic_students.BuscarStudent;
import com.unisabio.views.logic_views.logic_students.CrearStudent;
import com.unisabio.views.logic_views.logic_students.EliminarStudent;
import com.unisabio.views.logic_views.logic_teachers.ActualizarTeacher;
import com.unisabio.views.logic_views.logic_teachers.BuscarSTeacher;
import com.unisabio.views.logic_views.logic_teachers.CrearTeacher;
import com.unisabio.views.logic_views.logic_teachers.EliminarTeacher;

public class AdminView {
    private static final int EXITMS = 9;

    private AdminView(){}

    private static int menuAdmin() {
        System.out.println(
            "\u001B[35m\n---------------------------------------\n" +
            "|           MENU ADMINISTRADOR          |\n" +
            "|                                       |\n" +
            "|  1) Crear estudiantes                 |\n" +
            "|  2) Buscar estudiantes                |\n" +
            "|  3) Actualizar estudiantes            |\n" +
            "|  4) Eliminar estudiantes              |\n" +
            "|  5) Crear profesores                  |\n" +
            "|  6) Buscar profesores                 |\n" +
            "|  7) Actualizar profesores             |\n" +
            "|  8) Eliminar profesores               |\n" +
            "|  "+EXITMS+") Salir                             |\n" +
            "|                                       |\n" +
            "-----------------------------------------"
        );

        System.out.println("\n@ Selecciona una opción:");
        System.out.print("    > ");
        return Main.input.nextInt();
    }

    private static int menuBuscarStudent(int opc) {
        switch (opc) {
            case 1:
                System.out.println(
                    "\u001B[35m\n---------------------------------------\n" +
                    "|          MENU ADMINISTRADOR           |\n" +
                    "|                                       |\n" +
                    "|  1) Buscar estudiante                 |\n" +
                    "|  2) Mostrar todos los estudiantes     |\n" +
                    "|                                       |\n" +
                    "-----------------------------------------"
                );
                break;

            case 2:
                System.out.println(
                    "\u001B[35m\n---------------------------------------\n" +
                    "|          MENU ADMINISTRADOR           |\n" +
                    "|                                       |\n" +
                    "|  1) Buscar profesor                   |\n" +
                    "|  2) Mostrar todos los profesores      |\n" +
                    "|                                       |\n" +
                    "-----------------------------------------"
                );
                break;
        
            default:
                break;
        }

        System.out.println("\n@ Selecciona una opción:");
        System.out.print("    > ");
        return Main.input.nextInt();
    }

    public static void adminInit() {
        int sel = 0;

        do {
            try {                
                sel = menuAdmin();
                switch (sel) {
                    case 1:
                        CrearStudent.crear();
                        break;

                    case 2:
                        BuscarStudent.buscar(menuBuscarStudent(1));
                        break;

                    case 3:
                        ActualizarStudents.actualizarStud();
                        break;

                    case 4:
                        EliminarStudent.eliminar();
                        break;

                    case 5:
                        CrearTeacher.crear();
                        break;
                    case 6:
                        BuscarSTeacher.buscar(menuBuscarStudent(2));
                        break;

                    case 7:
                        ActualizarTeacher.actualizarTeach();
                        break;

                    case 8:
                        EliminarTeacher.eliminar();
                        break;
                
                    default:
                        break;
                }

            } catch (Exception e) {
                ErrorU.errorNum();
                Main.input.nextLine();
            }
            
        } while (sel != EXITMS);
    }


}
