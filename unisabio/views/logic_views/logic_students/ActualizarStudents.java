package com.unisabio.views.logic_views.logic_students;

import com.unisabio.Main;
import com.unisabio.exceptions.errors.ErrorU;
import com.unisabio.repository.models.models_extends.Student;
import com.unisabio.util.DataInfo;

public class ActualizarStudents {
    private static final int EXITAS = 8;

    private ActualizarStudents(){}

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

    public static void actualizarStud() {
        System.out.println("\n> Ingrese número de documento: ");
        try {
            long documento = Main.input.nextLong();
            Student student = Main.studentServices.buscarDocumStudent(documento);
            modificar(student);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void modificar(Student student) {
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
                        Main.input.nextLine();
                        System.out.println("> Ingrese ciudad de residencia: ");
                        student.setResidingCity(Main.input.nextLine());
                        break;

                    case 5:
                        Main.input.nextLine();
                        System.out.println("> Ingrese dirección de la vivienda: ");
                        student.setAddressHome(Main.input.nextLine());
                        break;

                    case 6:
                        Main.input.nextLine();
                        System.out.println("> Ingrese teléfono: ");
                        student.setPhone(Main.input.nextLine());
                        break;

                    case 7:
                        Main.studentServices.actualizarStudent(student);
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

    private static void valTypeDni(Student student) {
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
            student.setDniType(dniType);
        }
        else {
            ErrorU.errorInvalid();
            Main.input.nextLine();
        }
    }

    private static void valName(Student student) {
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
                        student.setPName(Main.input.nextLine());
                        break;

                    case 2:
                        Main.input.nextLine();
                        System.out.println("> Ingrese segundo nombre: ");
                        student.setSName(Main.input.nextLine());
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

    private static void valApellido(Student student) {
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
                        student.setPLastName(Main.input.nextLine());
                        break;

                    case 2:
                        Main.input.nextLine();
                        System.out.println("> Ingrese segundo apellido: ");
                        student.setSLastName(Main.input.nextLine());
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