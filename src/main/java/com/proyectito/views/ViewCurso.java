package com.proyectito.views;

import com.proyectito.exceptiones.cursoexceptions.CursoNullException;
import com.proyectito.repository.models.Curso;
import com.proyectito.utils.TextStyles;

public class ViewCurso extends ViewMain {
     private static final int SALIR = 6;

    public static void startMenu() {

        int op = 0;

        do {

            op = mostrarMenu();

            switch (op) {
                case 1:
                    crearCurso();
                    break;
                case 2:
                    listarCursoes();
                    break;
                case 3:
                    buscarCurso();
                    break;
                case 4:
                    modificarCurso();
                    break;
                case 5:
                    eliminarCurso();
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

    public static void crearCurso(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Crear Curso");
        leer.nextLine();
        System.out.print("\n-> Nombre del curso: ");
        String name = leer.nextLine();
        System.out.print("\n-> Dirección Guia Cátedra: ");
        String cat = leer.nextLine();
        Curso Curso = new Curso(name, cat);
        serviceCurso.crear(Curso);
    }

    public static void listarCursoes(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Listar Cursos");
        System.out.println("\n");
        int cont = 0;
        for (Curso Curso : serviceCurso.listar()) {
            cont += 1;
            System.out.println("\u001B[31m" + cont + ". \u001B[0m");
            Curso.imprimirCurso();
            System.out.println();
        }
        
    }

    public static void buscarCurso(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Buscar Curso");
        System.out.print("\n-> ID del Curso: ");
        int id = leer.nextInt();

        try {
            Curso Curso = serviceCurso.porID(id);
            System.err.println("\nBuscando...\n");
            
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Se ha encontrado: ");
            Curso.imprimirCurso();

        } catch (CursoNullException e) {
            System.out.println(e.getMessage());
        }

        
    }

    public static void modificarCurso(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Modificar Curso");
        System.out.print("\n-> ID del Curso: ");
        int id = leer.nextInt();

        try {
            Curso curso = serviceCurso.porID(id);
            curso.imprimirCurso();
            leer.nextLine();
            
            System.out.print(" ¿Modificar nombre? si/no: " );
            String siNo = leer.nextLine();
            
            if (siNo.equalsIgnoreCase("si")) {
                System.out.print("\n-> Nombre del Curso: ");
                String nuevoNombre = leer.nextLine();
                curso.setNombre(nuevoNombre);
            }
            
            System.out.println("¿Modificar Guia Cátedra? si/no: ");
            siNo = leer.nextLine();

            if (siNo.equalsIgnoreCase("si")) {
                System.out.println("Guia Catedra: ");
                String cat = leer.nextLine();
                curso.setGuiaCatedra(cat);
            }

            serviceCurso.editar(curso,id);
        } catch (CursoNullException e) {
            System.out.println(e.getMessage());
        }

        
    }

    public static void eliminarCurso(){
        System.out.println("\n");
        TextStyles.prettySubTitle("Eliminar Curso");
        System.out.print("\n-> ID del Curso: ");
        int id = leer.nextInt();

        try {
            Curso curso = serviceCurso.porID(id);
            leer.nextLine();
            System.out.print(" ¿Desea eliminar el curso " +curso.getNombre() + "? si/no: " );
            String siNo = leer.nextLine();
            if (siNo.equalsIgnoreCase("si")) {
                serviceCurso.eliminar(curso,id);
            }
        } catch (CursoNullException e) {
            System.out.println(e.getMessage());
        }
        
    }

    public static int mostrarMenu() {

        System.out.println("\n");
        TextStyles.prettyTitle("MENU CURSO");
        System.out.println("\n");
        TextStyles.opMenu("Crear curso", 1);
        TextStyles.opMenu("Listar cursos", 2);
        TextStyles.opMenu("Buscar curso", 3);
        TextStyles.opMenu("Modificar curso", 4);
        TextStyles.opMenu("Eliminar curso", 4);
        TextStyles.opMenu("Salir", SALIR);
        System.out.println("\n");
        System.out.print("-> Opcion: ");
        return leer.nextInt();

    }
}
