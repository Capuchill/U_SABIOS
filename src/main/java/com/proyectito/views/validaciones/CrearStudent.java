package com.proyectito.views.validaciones;



import java.time.LocalDate;


import com.proyectito.exceptiones.ErrorU;
import com.proyectito.repository.models.Alumno;
import com.proyectito.utils.DataInfo;
import com.proyectito.views.ViewMain;



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
                int typeDocument = ViewMain.leer.nextInt();

                if(typeDocument == 99) {
                    break;
                }
                else {
                    if(typeDocument > 0 && typeDocument < 5) {
                        String dniType = DataInfo.docType.get(typeDocument);
                        System.out.println("> Ingrese número de documento (10 dígitos): ");
                        long dniNumber = ViewMain.leer.nextLong();
    
                        if(String.valueOf(dniNumber).length() > 0 && String.valueOf(dniNumber).length() < 11)  {
                            ViewMain.leer.nextLine();
                            System.out.println("> Ingrese primer nombre: ");
                            String pName = ViewMain.leer.nextLine();
                            System.out.println("> Ingrese segundo nombre: ");
                            String sName = ViewMain.leer.nextLine();
                            System.out.println("> Ingrese primer apellido: ");
                            String pLastName = ViewMain.leer.nextLine();
                            System.out.println("> Ingrese segundo apellido: ");
                            String sLastName = ViewMain.leer.nextLine();
                            Alumno student = new Alumno(dniType, dniNumber, pName, sName, pLastName, sLastName);
    
                            System.out.println("> Ingrese ciudad de residencia: ");
                            String residingCity = ViewMain.leer.nextLine();
                            System.out.println("> Ingrese dirección de la vivienda: ");
                            String addressHome = ViewMain.leer.nextLine();
                            System.out.println("> Ingrese teléfono: ");
                            String phone = ViewMain.leer.nextLine();
                            
                            int anio = valAnio();
    
                            int mes = valmes();
    
                            String bornDate = String.valueOf( valDia(anio, mes));
    
                            String sex = selSex();
                            
                            student.setInfoAdicional(residingCity, addressHome, phone, bornDate, sex);
    
                            ViewMain.serviceAlumno.crearAlumno(student);
                        }
                        else {
                            ErrorU.errSizeNum();
                            ViewMain.leer.nextLine();
                        }
                    }
                    else {
                        ErrorU.errorInvalid();
                        ViewMain.leer.nextLine();
                    }
                }                


            } 
            catch (Exception e) {
                ErrorU.errorInvalid();
                ViewMain.leer.nextLine();
            }
            
        } while (!EXIT);
    }

    private static int valAnio() {
        int anio = 0;
        LocalDate hoy = LocalDate.now();

        do {
            try {
                System.out.println("\u001B[35m> Ingrese año de nacimiento: ");
                anio = ViewMain.leer.nextInt();

                if(anio > 1944 && anio <= hoy.getYear() - 10) {
                    break;
                }
                else {
                    anio = 0;
                }
                
            } catch (Exception e) {
                ErrorU.errorInvalid();
                ViewMain.leer.nextLine();
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
                mes = ViewMain.leer.nextInt();

                if(mes > 0 && mes <= 12) {
                    break;
                }
                else {
                    mes = 0;
                }
                
            } catch (Exception e) {
                ErrorU.errorInvalid();
                ViewMain.leer.nextLine();
            }
            
        } while (mes == 0);
        return mes;
    }

    private static LocalDate valDia(int anio, int mes) {
        LocalDate fecha = null;

        do {
            try {
                System.out.println("\u001B[35m> Ingrese día de nacimiento: ");
                int dia = ViewMain.leer.nextInt();
                fecha = LocalDate.of(anio, mes, dia);
                
            } catch (Exception e) {
                ErrorU.errDia();
                fecha = null;
                ViewMain.leer.nextLine();
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
                int sel = ViewMain.leer.nextInt();

                if(sel == 1 || sel == 2) {
                    sex = DataInfo.sexs.get(sel);
                    break;
                }
                else {}
                
            } catch (Exception e) {
                ErrorU.errorInvalid();
                ViewMain.leer.nextLine();
            }
            
        } while (sex.equals(""));
        return sex;
    }
}
