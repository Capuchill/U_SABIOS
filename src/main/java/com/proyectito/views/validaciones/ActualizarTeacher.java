package com.proyectito.views.validaciones;

import com.proyectito.exceptiones.ErrorU;
import com.proyectito.repository.models.Profesor;
import com.proyectito.utils.DataInfo;
import com.proyectito.views.ViewMain;

public class ActualizarTeacher {
    
    private static final int EXITAS = 8;

    private ActualizarTeacher(){}

    private static int menuMod() {
        System.out.println(
            "\u001B[35m\n---------------------------------------\n" +
            "|             MENU MODIFICAR            |\n" +
            "|                                       |\n" +
            "|  1) Tipo DNI                          |\n" +
            "|  2) Nombres                           |\n" +
            "|  3) Apellidos                         |\n" +
            "|  4) Ciudad residencia                 |\n" +
            "|  5) Dirección residencia              |\n" +
            "|  6) Teléfono                          |\n" +
            "|  7) Guardar                           |\n" +
            "|  "+EXITAS+") Salir                             |\n" +
            "|                                       |\n" +
            "-----------------------------------------"
        );

        System.out.println("\n@ Selecciona una opción:");
        System.out.print("    > ");
        return ViewMain.leer.nextInt();

    }

    public static void actualizarStud() {
        System.out.println("\n> Ingrese número de documento: ");
        try {
            long documento = ViewMain.leer.nextLong();
            Profesor student = ViewMain.serviceProfesor.buscarDocumProfesor(documento);
            modificar(student);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void modificar(Profesor student) {
        int sel = 0;
        do {
            System.out.println(student.toString());
            try {
                sel = menuMod();

                switch (sel) {
                    case 1:
                        valTypeDni(student);
                        break;
                    
                    case 2:
                        valName(student);
                        break;

                    case 3:
                        valApellido(student);
                        break;
                    
                    case 4:
                        ViewMain.leer.nextLine();
                        System.out.println("> Ingrese ciudad de residencia: ");
                        student.setResidingCity(ViewMain.leer.nextLine());
                        break;

                    case 5:
                        ViewMain.leer.nextLine();
                        System.out.println("> Ingrese dirección de la vivienda: ");
                        student.setAddressHome(ViewMain.leer.nextLine());
                        break;

                    case 6:
                        ViewMain.leer.nextLine();
                        System.out.println("> Ingrese teléfono: ");
                        student.setPhone(ViewMain.leer.nextLine());
                        break;

                    case 7:

                        ViewMain.serviceProfesor.actualizarProfesor(student);
                        break;
                
                    default:
                        break;
                }

                                
            } catch (Exception e) {
                ErrorU.errorNum();
                ViewMain.leer.nextLine();
            }
            
        } while (sel != EXITAS);
    }

    //234567890

    private static void valTypeDni(Profesor student) {
        System.out.println(
            "\n 1) " + DataInfo.docType.get(1) +
            "   2) " + DataInfo.docType.get(2) +
            "   3) " + DataInfo.docType.get(3) +
            "   4) " + DataInfo.docType.get(4) + "\n"
        );
        System.out.println("\n> Seleccione tipo de documento: ");
        int typeDocument = ViewMain.leer.nextInt();
        if(typeDocument > 0 && typeDocument < 5) {
            String dniType = DataInfo.docType.get(typeDocument);
            student.setDniType(dniType);
        }
        else {
            ErrorU.errorInvalid();
            ViewMain.leer.nextLine();
        }
    }

    private static void valName(Profesor student) {
        int sel = 0;

        do {
            try {
                System.out.println(
                    "Seleccione una opción:\n" +
                    "\n1) Primer nombre     2) Segundo nombre   3) Terminar\n"
                );

                sel = ViewMain.leer.nextInt();

                switch (sel) {
                    case 1:
                        ViewMain.leer.nextLine();
                        System.out.println("> Ingrese primer nombre: ");            
                        student.setPName(ViewMain.leer.nextLine());
                        break;

                    case 2:
                        ViewMain.leer.nextLine();
                        System.out.println("> Ingrese segundo nombre: ");
                        student.setSName(ViewMain.leer.nextLine());
                        break;
                
                    default:
                        break;
                }

            } catch (Exception e) {
                ErrorU.errorNum();
                ViewMain.leer.nextLine();
            }
            
        } while (sel != 3);
        
    }

    private static void valApellido(Profesor student) {
        int sel = 0;

        do {
            try {
                System.out.println(
                    "Seleccione una opción:\n" +
                    "\n1) Primer apellido     2) Segundo apellido   3) Terminar"
                );

                sel = ViewMain.leer.nextInt();
                switch (sel) {
                    case 1:
                        ViewMain.leer.nextLine();
                        System.out.println("> Ingrese primer apellido: ");
                        student.setPLastName(ViewMain.leer.nextLine());
                        break;

                    case 2:
                        ViewMain.leer.nextLine();
                        System.out.println("> Ingrese segundo apellido: ");
                        student.setSLastName(ViewMain.leer.nextLine());
                        break;
                
                    default:
                        break;
                }

            } catch (Exception e) {
                ErrorU.errorNum();
                ViewMain.leer.nextLine();
            }
            
        } while (sel != 3);
        
    }

}