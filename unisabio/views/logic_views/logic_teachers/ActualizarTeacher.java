package com.unisabio.views.logic_views.logic_teachers;

import com.unisabio.Main;
import com.unisabio.exceptions.errors.ErrorU;
import com.unisabio.repository.models.models_extends.Teacher;
import com.unisabio.util.DataInfo;

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
        return Main.input.nextInt();

    }

    public static void actualizarTeach() {
        System.out.println("\n> Ingrese número de documento: ");
        try {
            long documento = Main.input.nextLong();
            Teacher teacher = Main.teacherServices.buscarDocumTeacher(documento);
            modificar(teacher);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void modificar(Teacher teacher) {
        int sel = 0;
        do {
            System.out.println(teacher.toString());
            try {
                sel = menuMod();

                switch (sel) {
                    case 1:
                        valTypeDni(teacher);
                        break;
                    
                    case 2:
                        valName(teacher);
                        break;

                    case 3:
                        valApellido(teacher);
                        break;
                    
                    case 4:
                        Main.input.nextLine();
                        System.out.println("> Ingrese ciudad de residencia: ");
                        teacher.setResidingCity(Main.input.nextLine());
                        break;

                    case 5:
                        Main.input.nextLine();
                        System.out.println("> Ingrese dirección de la vivienda: ");
                        teacher.setAddressHome(Main.input.nextLine());
                        break;

                    case 6:
                        Main.input.nextLine();
                        System.out.println("> Ingrese teléfono: ");
                        teacher.setPhone(Main.input.nextLine());
                        break;

                    case 7:
                        Main.teacherServices.actualizarTeacher(teacher);
                        break;
                
                    default:
                        break;
                }

                                
            } catch (Exception e) {
                ErrorU.errorNum();
                Main.input.nextLine();
            }
            
        } while (sel != EXITAS);
    }

    //234567890

    private static void valTypeDni(Teacher teacher) {
        System.out.println(
            "\n 1) " + DataInfo.docType.get(1) +
            "   2) " + DataInfo.docType.get(2) +
            "   3) " + DataInfo.docType.get(3) +
            "   4) " + DataInfo.docType.get(4) + "\n"
        );
        System.out.println("\n> Seleccione tipo de documento: ");
        int typeDocument = Main.input.nextInt();
        if(typeDocument > 0 && typeDocument < 5) {
            String dniType = DataInfo.docType.get(typeDocument);
            teacher.setDniType(dniType);
        }
        else {
            ErrorU.errorInvalid();
            Main.input.nextLine();
        }
    }

    private static void valName(Teacher teacher) {
        int sel = 0;

        do {
            try {
                System.out.println(
                    "Seleccione una opción:\n" +
                    "\n1) Primer nombre     2) Segundo nombre   3) Terminar\n"
                );

                sel = Main.input.nextInt();

                switch (sel) {
                    case 1:
                        Main.input.nextLine();
                        System.out.println("> Ingrese primer nombre: ");            
                        teacher.setPName(Main.input.nextLine());
                        break;

                    case 2:
                        Main.input.nextLine();
                        System.out.println("> Ingrese segundo nombre: ");
                        teacher.setSName(Main.input.nextLine());
                        break;
                
                    default:
                        break;
                }

            } catch (Exception e) {
                ErrorU.errorNum();
                Main.input.nextLine();
            }
            
        } while (sel != 3);
        
    }

    private static void valApellido(Teacher teacher) {
        int sel = 0;

        do {
            try {
                System.out.println(
                    "Seleccione una opción:\n" +
                    "\n1) Primer apellido     2) Segundo apellido   3) Terminar"
                );

                sel = Main.input.nextInt();
                switch (sel) {
                    case 1:
                        Main.input.nextLine();
                        System.out.println("> Ingrese primer apellido: ");
                        teacher.setPLastName(Main.input.nextLine());
                        break;

                    case 2:
                        Main.input.nextLine();
                        System.out.println("> Ingrese segundo apellido: ");
                        teacher.setSLastName(Main.input.nextLine());
                        break;
                
                    default:
                        break;
                }

            } catch (Exception e) {
                ErrorU.errorNum();
                Main.input.nextLine();
            }
            
        } while (sel != 3);
        
    }

}