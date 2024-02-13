package com.unisabio.views.logic_views.logic_students;

import java.time.LocalDate;

import com.unisabio.Main;
import com.unisabio.exceptions.errors.ErrorU;
import com.unisabio.repository.models.models_extends.Student;
import com.unisabio.util.DataInfo;

public class CrearStudent {
    private static final boolean EXIT = false;
    
    private CrearStudent(){}

    private static void menuCrear(int opc) {
        switch (opc) {
            case 1:
                System.out.println(
                    "\n 1) " + DataInfo.docType.get(1) +
                    "   2) " + DataInfo.docType.get(2) +
                    "   3) " + DataInfo.docType.get(3) +
                    "   4) " + DataInfo.docType.get(4) + "\n"
                );
                break;
        
            default:
                break;
        }
    }
    
    public static void crear() {
        do {
            try {
                System.out.println(
                    "\u001B[35m\n----------------------------------\n" +
                    "|          CREAR ESTUDIANTE        |\n" +
                    "|-----------------------------------"
                );

                System.out.println("   Ingrese el número 99 para salir");

                System.out.println("\n> Seleccione tipo de documento: ");
                menuCrear(1);
                int typeDocument = Main.input.nextInt();

                if(typeDocument == 99) {
                    break;
                }
                else {
                    if(typeDocument > 0 && typeDocument < 5) {
                        String dniType = DataInfo.docType.get(typeDocument);
                        System.out.println("> Ingrese número de documento (10 dígitos): ");
                        long dniNumber = Main.input.nextLong();
    
                        if(String.valueOf(dniNumber).length() > 0 && String.valueOf(dniNumber).length() < 11)  {
                            Main.input.nextLine();
                            System.out.println("> Ingrese primer nombre: ");
                            String pName = Main.input.nextLine();
                            System.out.println("> Ingrese segundo nombre: ");
                            String sName = Main.input.nextLine();
                            System.out.println("> Ingrese primer apellido: ");
                            String pLastName = Main.input.nextLine();
                            System.out.println("> Ingrese segundo apellido: ");
                            String sLastName = Main.input.nextLine();
                            Student student = new Student(dniType, dniNumber, pName, sName, pLastName, sLastName);
    
                            System.out.println("> Ingrese ciudad de residencia: ");
                            String residingCity = Main.input.nextLine();
                            System.out.println("> Ingrese dirección de la vivienda: ");
                            String addressHome = Main.input.nextLine();
                            System.out.println("> Ingrese teléfono: ");
                            String phone = Main.input.nextLine();
                            
                            int anio = valAnio();
    
                            int mes = valmes();
    
                            String bornDate = String.valueOf( valDia(anio, mes));
    
                            String sex = selSex();
                            
                            student.setInfoAdicional(residingCity, addressHome, phone, bornDate, sex);
    
                            Main.studentServices.crearStudent(student);
                        }
                        else {
                            ErrorU.errSizeNum();
                            Main.input.nextLine();
                        }
                    }
                    else {
                        ErrorU.errorInvalid();
                        Main.input.nextLine();
                    }
                }                


            } 
            catch (Exception e) {
                ErrorU.errorInvalid();
                Main.input.nextLine();
            }
            
        } while (!EXIT);
    }

    private static int valAnio() {
        int anio = 0;
        LocalDate hoy = LocalDate.now();

        do {
            try {
                System.out.println("\u001B[35m> Ingrese año de nacimiento: ");
                anio = Main.input.nextInt();

                if(anio > 1944 && anio <= hoy.getYear() - 10) {
                    break;
                }
                else {
                    anio = 0;
                }
                
            } catch (Exception e) {
                ErrorU.errorInvalid();
                Main.input.nextLine();
            }
            
        } while (anio == 0);
        return anio;
    }

    private static int valmes() {
        int mes = 0;

        do {
            try {
                System.out.println("\u001B[35m> Ingrese mes de nacimiento: ");
                System.out.println(
                        "\n------------------------------\n" +
                        "| Seleccione el mes:            \n" +
                        "--------------------------------\n"
                    );
                for(String mess : DataInfo.meses.keySet()) {
                    System.out.println(
                        "   " + DataInfo.meses.get(mess) + ") " + mess
                    );
                    
                }
                System.out.println("--------------------------------\n");
                mes = Main.input.nextInt();

                if(mes > 0 && mes <= 12) {
                    break;
                }
                else {
                    mes = 0;
                }
                
            } catch (Exception e) {
                ErrorU.errorInvalid();
                Main.input.nextLine();
            }
            
        } while (mes == 0);
        return mes;
    }

    private static LocalDate valDia(int anio, int mes) {
        LocalDate fecha = null;

        do {
            try {
                System.out.println("\u001B[35m> Ingrese día de nacimiento: ");
                int dia = Main.input.nextInt();
                fecha = LocalDate.of(anio, mes, dia);
                
            } catch (Exception e) {
                ErrorU.errDia();
                fecha = null;
                Main.input.nextLine();
            }
            
        } while (fecha == null);
        return fecha;
    }

    private static String selSex() {
        String sex = "";

        do {
            try {
                System.out.println("\u001B[35m> Seleccione sexo: ");
                System.out.println("\n> 1) Masculino    2) Femenino ");
                int sel = Main.input.nextInt();

                if(sel == 1 || sel == 2) {
                    sex = DataInfo.sexs.get(sel);
                    break;
                }
                else {}
                
            } catch (Exception e) {
                ErrorU.errorInvalid();
                Main.input.nextLine();
            }
            
        } while (sex.equals(""));
        return sex;
    }
}
