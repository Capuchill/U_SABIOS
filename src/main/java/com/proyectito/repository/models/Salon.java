package com.proyectito.repository.models;

public class Salon {
   
    private String reference;
    private int capacidad;
    private int id_piso;
    
    public String getReference() {
        return reference;
    }
    
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    public int getCapacidad() {
        return capacidad;
    }
    
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getId_piso() {
        return id_piso;
    }

    public void setId_piso(int id_piso) {
        this.id_piso = id_piso;
    }

    public Salon(String reference, int capacidad, int id_piso) {
        this.reference = reference;
        this.capacidad = capacidad;
        this.id_piso = id_piso;
    }

    public Salon() {
    }
    
    public void imprimirSalon() {
        System.out.println("Referencia: " + this.getReference());
        System.out.println("Capacidad: " + this.getCapacidad());
        System.out.println("ID del Piso: " + this.getId_piso());
    }

}
