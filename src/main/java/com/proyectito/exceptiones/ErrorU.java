package com.proyectito.exceptiones;

public class ErrorU {

    private ErrorU(){}

    public static void errorNum() {
        System.out.println(
            "\u001B[31m\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" +
            "|                                             |\n" +
            "|   ERROR: Ingrese solo números!!!            |\n" +
            "|                                             |\n" +
            "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"        
        );
    }

    public static void errorInvalid() {
        System.out.println(
            "\u001B[31m\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" +
            "|                                             |\n" +
            "|   ERROR: Ingrese un valor valido del        |\n" +
            "|          menu de opciones!!                 |\n" +
            "|                                             |\n" +
            "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"        
        );
    }

    public static void errSizeNum() {
        System.out.println(
            "\u001B[31m\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" +
            "|                                             |\n" +
            "|   ERROR: La longitud del valor supera la    |\n" +
            "|          permitida.                         |\n" +
            "|                                             |\n" +
            "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"        
        );
    }

    public static void errDia() {
        System.out.println(
            "\u001B[31m\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" +
            "|                                             |\n" +
            "|   ERROR: El día ingresado es incorrecto     |\n" +
            "|                                             |\n" +
            "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"        
        );
    }

    public static void errDocDup() {
        System.out.println(
            "\u001B[31m\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" +
            "|                                             |\n" +
            "|   ERROR: El documento está duplicado        |\n" +
            "|                                             |\n" +
            "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"        
        );
    }
    
}

