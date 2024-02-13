package com.proyectito.repository.models;

public class Periodo {
    
    private String cod;
    private String año;
    private int semestre;
    
    public String getAño() {
        return año;
    }
    
    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public void setAño(String año) {
        this.año = año;
    }
    
    public int getSemestre() {
        return semestre;
    }
    
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Periodo(String año, int semestre) {
        this.año = año;
        this.semestre = semestre;
    }

    public Periodo() {
    }

    public void imprimirPeriodo(){
        System.out.println("Código: " + this.getCod());
        System.out.println("Año: " + this.getAño());
        System.out.println("Semestre: " + this.getSemestre());
    }
    
    
}
