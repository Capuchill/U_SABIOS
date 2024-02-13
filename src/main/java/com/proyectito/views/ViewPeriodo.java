package com.proyectito.views;

import com.proyectito.exceptiones.periodoexceptions.PeriodoNullException;
import com.proyectito.repository.models.Periodo;
import com.proyectito.utils.TextStyles;

public class ViewPeriodo extends ViewMain {
    
    private static final int SALIR = 6;

    public static void startMenu() {

        int op = 0;

        do {

            op = mostrarMenu();

            switch (op) {
                case 1:
                    crearPeriodo();
                    break;
                case 2:
                    listarPeriodoes();
                    break;
                case 3:
                    buscarPeriodo();
                    break;
                case 4:
                    modificarPeriodo();
                    break;
                case 5:
                    eliminarPeriodo();
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

    public static void crearPeriodo(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Crear Periodo");
        leer.nextLine();
        System.out.print("\n-> Año del periodo: ");
        String name = leer.nextLine();
        System.out.print("\n-> Semestre: ");
        int cat = leer.nextInt();
        Periodo periodo = new Periodo(name, cat);
        servicePeriodo.crear(periodo);
    }

    public static void listarPeriodoes(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Listar Periodos");
        System.out.println("\n");
        int cont = 0;
        for (Periodo Periodo : servicePeriodo.listar()) {
            cont += 1;
            System.out.println("\u001B[31m" + cont + ". \u001B[0m");
            Periodo.imprimirPeriodo();
            System.out.println();
        }
        
    }

    public static void buscarPeriodo(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Buscar Periodo");
        System.out.print("\n-> ID del Periodo: ");
        int id = leer.nextInt();

        try {
            Periodo periodo = servicePeriodo.porID(id);
            System.err.println("\nBuscando...\n");
            
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Se ha encontrado: ");
            periodo.imprimirPeriodo();

        } catch (PeriodoNullException e) {
            System.out.println(e.getMessage());
        }

        
    }

    public static void modificarPeriodo(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Modificar Periodo");
        System.out.print("\n-> ID del Periodo: ");
        int id = leer.nextInt();

        try {
            Periodo periodo = servicePeriodo.porID(id);
            periodo.imprimirPeriodo();
            leer.nextLine();
            
            System.out.print(" ¿Modificar año? si/no: " );
            String siNo = leer.nextLine();
            
            if (siNo.equalsIgnoreCase("si")) {
                System.out.print("\n-> Año del periodo: ");
                String nuevoNombre = leer.nextLine();
                periodo.setAño(nuevoNombre);
            }
            
            System.out.println("¿Modificar Semestre? si/no: ");
            siNo = leer.nextLine();

            if (siNo.equalsIgnoreCase("si")) {
                System.out.println("-> Semestre: : ");
                int cat = leer.nextInt();
                periodo.setSemestre(cat);
            }

            servicePeriodo.editar(periodo,id);
        } catch (PeriodoNullException e) {
            System.out.println(e.getMessage());
        }

        
    }

    public static void eliminarPeriodo(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Eliminar Periodo");
        System.out.print("\n-> ID del Periodo: ");
        int id = leer.nextInt();

        try {
            Periodo periodo = servicePeriodo.porID(id);
            leer.nextLine();
            System.out.print(" ¿Desea eliminar el Periodo " +periodo.getCod() + "? si/no: " );
            String siNo = leer.nextLine();
            if (siNo.equalsIgnoreCase("si")) {
                servicePeriodo.eliminar(periodo,id);
            }
        } catch (PeriodoNullException e) {
            System.out.println(e.getMessage());
        }
        
    }

    public static int mostrarMenu() {

        System.out.println("\n");
        TextStyles.prettyTitle("MENU PERIODO");
        System.out.println("\n");
        TextStyles.opMenu("Crear periodo", 1);
        TextStyles.opMenu("Listar periodos", 2);
        TextStyles.opMenu("Buscar periodo", 3);
        TextStyles.opMenu("Modificar periodo", 4);
        TextStyles.opMenu("Eliminar periodo", 4);
        TextStyles.opMenu("Salir", SALIR);
        System.out.println("\n");
        System.out.print("-> Opcion: ");
        return leer.nextInt();

    }
}
