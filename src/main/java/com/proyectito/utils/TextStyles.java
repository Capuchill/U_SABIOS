package com.proyectito.utils;

public class TextStyles {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_BOLD = "\u001B[1m";
    
    // Plantillas para diferentes tipos de mensajes
    public static void prettyTitle(String title) {

        System.out.println(ANSI_GREEN + "<------<--<--<-" + ANSI_RESET + ANSI_BOLD + ANSI_CYAN + " " + title + " " + ANSI_RESET + ANSI_GREEN + "->-->-->------>" + ANSI_RESET);
    }

    public static void msgError(String errorMessage) {
        System.out.println(ANSI_RED +"---------------------------------------------------------------------------------"+ ANSI_RESET);
        System.out.println(ANSI_BOLD + "  Error: " + errorMessage + ANSI_RESET);
        System.out.println(ANSI_RED +"---------------------------------------------------------------------------------"+ ANSI_RESET);
    }

    public static void opMenu(String text,int n) {
        System.out.println(ANSI_RED + n +  ". " + ANSI_RESET + text);
    }

    public static void prettySubTitle(String title) {

        System.out.println(ANSI_GREEN + "*'*'*'*'*" + ANSI_RESET + ANSI_BOLD + ANSI_YELLOW + " " + title + " " + ANSI_RESET + ANSI_GREEN + "*'*'*'*'*" + ANSI_RESET);
    }


}
