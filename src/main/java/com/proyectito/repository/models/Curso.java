package com.proyectito.repository.models;

public class Curso {
    private String nombre;
    private String guiaCatedra;
    
    public Curso(String nombre, String guiaCatedra) {
        this.nombre = nombre;
        this.guiaCatedra = guiaCatedra;
    }

    public Curso(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGuiaCatedra() {
        return guiaCatedra;
    }

    public void setGuiaCatedra(String guiaCatedra) {
        this.guiaCatedra = guiaCatedra;
    }

    
    public void imprimirCurso(){
        System.out.println("Nombre del curso: " + this.getNombre());
        System.out.println("Direccion Guia Catedra: " + this.getGuiaCatedra());
    }
    
}
