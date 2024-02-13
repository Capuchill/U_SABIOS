package com.proyectito.views;

import com.proyectito.exceptiones.departamentoexceptions.DepartamentoNullException;
import com.proyectito.repository.models.Departamento;
import com.proyectito.utils.TextStyles;

public class ViewDepartamento extends ViewMain {

    private static final int SALIR = 6;

    public static void startMenu() {

        int op = 0;

        do {

            op = mostrarMenu();

            switch (op) {
                case 1:
                    crearDepartamento();
                    break;
                case 2:
                    listarDepartamentos();
                    break;
                case 3:
                    buscarDepartamento();
                    break;
                case 4:
                    modificarDepartamento();
                    break;
                case 5:
                    eliminarDepartamento();
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

    public static void crearDepartamento(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Crear Departamento");
        leer.nextLine();
        System.out.print("\n-> Nombre departamento: ");
        String name = leer.nextLine();
        Departamento depart = new Departamento(name);
        serviceDepartamento.crear(depart);
    }

    public static void listarDepartamentos(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Listar Departamentos");
        System.out.println("\n");
        int cont = 0;
        for (Departamento depart : serviceDepartamento.listar()) {
            cont += 1;
            System.out.println("\u001B[31m" + cont + ". \u001B[0m" + depart.getNombre());
            System.out.println();
        }
        
    }

    public static void buscarDepartamento(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Buscar Departamento");
        System.out.print("\n-> ID del departamento: ");
        int id = leer.nextInt();

        try {
            Departamento depart = serviceDepartamento.porID(id);
            System.err.println("\nBuscando...\n");
            
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Se ha encontrado: " + depart.getNombre());
        } catch (DepartamentoNullException e) {
            System.out.println(e.getMessage());
        }

        
    }

    public static void modificarDepartamento(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Modificar Departamento");
        System.out.print("\n-> ID del departamento: ");
        int id = leer.nextInt();

        try {
            Departamento depart = serviceDepartamento.porID(id);
            leer.nextLine();
            System.out.print(" ¿Modificar nombre? si/no: " );
            String siNo = leer.nextLine();
            if (siNo.equalsIgnoreCase("si")) {
                System.out.print("\n-> Nombre: ");
                String nuevoNombre = leer.nextLine();
                depart.setNombre(nuevoNombre);
                serviceDepartamento.editar(depart,id);
            }
        } catch (DepartamentoNullException e) {
            System.out.println(e.getMessage());
        }

        
    }

    public static void eliminarDepartamento(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Eliminar Departamento");
        System.out.print("\n-> ID del departamento: ");
        int id = leer.nextInt();

        try {
            Departamento depart = serviceDepartamento.porID(id);
            leer.nextLine();
            System.out.print(" ¿Desea eliminar " +depart.getNombre() + "? si/no: " );
            String siNo = leer.nextLine();
            if (siNo.equalsIgnoreCase("si")) {
                serviceDepartamento.eliminar(depart,id);
            }
        } catch (DepartamentoNullException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int mostrarMenu() {
        System.out.println("\n");
        TextStyles.prettyTitle("MENU DEPARTAMENTO");
        System.out.println("\n");
        TextStyles.opMenu("Crear departamento", 1);
        TextStyles.opMenu("Listar departamento", 2);
        TextStyles.opMenu("Buscar departamento", 3);
        TextStyles.opMenu("Modificar departamento", 4);
        TextStyles.opMenu("Eliminar departamento", 4);
        TextStyles.opMenu("Salir", SALIR);
        System.out.println("\n");
        System.out.print("-> Opcion: ");
        return leer.nextInt();
    }
}
