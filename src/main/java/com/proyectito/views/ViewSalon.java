package com.proyectito.views;

import com.proyectito.exceptiones.salonexceptions.SalonNullException;
import com.proyectito.repository.models.Salon;
import com.proyectito.utils.TextStyles;

public class ViewSalon extends ViewMain {
     private static final int SALIR = 6;

    public static void startMenu() {

        int op = 0;

        do {

            op = mostrarMenu();

            switch (op) {
                case 1:
                    crearSalon();
                    break;
                case 2:
                    listarSalones();
                    break;
                case 3:
                    buscarSalon();
                    break;
                case 4:
                    modificarSalon();
                    break;
                case 5:
                    eliminarSalon();
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

    public static void crearSalon(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Crear Salon");
        leer.nextLine();
        System.out.print("\n-> Referencia Salon: ");
        String name = leer.nextLine();
        System.out.print("\n-> Capacidad Salon: ");
        int cap = leer.nextInt();
        System.out.println("\n-> ID piso: ");
        int piso = leer.nextInt();
        Salon salon = new Salon(name, cap, piso);
        serviceSalon.crear(salon);
    }

    public static void listarSalones(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Listar Salones");
        System.out.println("\n");
        int cont = 0;
        for (Salon salon : serviceSalon.listar()) {
            cont += 1;
            System.out.println("\u001B[31m" + cont + ". \u001B[0m");
            salon.imprimirSalon();
            System.err.println();
        }
        
    }

    public static void buscarSalon(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Buscar Salon");
        System.out.print("\n-> ID del Salon: ");
        int id = leer.nextInt();

        try {
            Salon salon = serviceSalon.porID(id);
            System.err.println("\nBuscando...\n");
            
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Se ha encontrado: ");
            salon.imprimirSalon();

        } catch (SalonNullException e) {
            System.out.println(e.getMessage());
        }

        
    }

    public static void modificarSalon(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Modificar Salon");
        System.out.print("\n-> ID del Salon: ");
        int id = leer.nextInt();

        try {
            Salon salon = serviceSalon.porID(id);
            salon.imprimirSalon();
            leer.nextLine();
            
            System.out.print(" ¿Modificar referencia? si/no: " );
            String siNo = leer.nextLine();
            
            if (siNo.equalsIgnoreCase("si")) {
                System.out.print("\n-> Referencia: ");
                String nuevoNombre = leer.nextLine();
                salon.setReference(nuevoNombre);
            }
            
            System.out.println("Modificar capacidad: si o no? ");
            siNo = leer.nextLine();

            if (siNo.equalsIgnoreCase("si")) {
                System.out.println("Capacidad: ");
                int nCAp = leer.nextInt();
                salon.setCapacidad(nCAp);
            }
            
            System.out.println("Modificar email: si o no? ");
            siNo = leer.nextLine();

            if (siNo.equalsIgnoreCase("si")) {
                System.out.println("ID piso: ");
                int idP = leer.nextInt();
                salon.setId_piso(idP);
            }

            serviceSalon.editar(salon,id);
        } catch (SalonNullException e) {
            System.out.println(e.getMessage());
        }

        
    }

    public static void eliminarSalon(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Eliminar Salon");
        System.out.print("\n-> ID del Salon: ");
        int id = leer.nextInt();

        try {
            Salon salon = serviceSalon.porID(id);
            leer.nextLine();
            System.out.print(" ¿Desea eliminar el salon" +salon.getReference() + "? si/no: " );
            String siNo = leer.nextLine();
            if (siNo.equalsIgnoreCase("si")) {
                serviceSalon.eliminar(salon,id);
            }
        } catch (SalonNullException e) {
            System.out.println(e.getMessage());
        }
        
    }

    public static int mostrarMenu() {
        System.out.println("\n");
        TextStyles.prettyTitle("MENU SALON");
        System.out.println("\n");
        TextStyles.opMenu("Crear salon", 1);
        TextStyles.opMenu("Listar salones", 2);
        TextStyles.opMenu("Buscar salon", 3);
        TextStyles.opMenu("Modificar salon", 4);
        TextStyles.opMenu("Eliminar salon", 4);
        TextStyles.opMenu("Salir", SALIR);
        System.out.println("\n");
        System.out.print("-> Opcion: ");
        return leer.nextInt();
    }
}
