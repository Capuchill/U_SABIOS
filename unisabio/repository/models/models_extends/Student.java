package com.unisabio.repository.models.models_extends;

import com.unisabio.repository.models.models_main.People;

public class Alumno extends People {

    public Alumno(
            String dniType, Long dniNumber, String pName, String sName,
             String pLastName, String sLastName
        ) {
        super(
                dniType, dniNumber, pName, sName, pLastName, sLastName
            );
    }

    public Alumno (
        int idPerson, String dniType, Long dniNumber, String pName, 
        String sName, String pLastName, String sLastName
    ) {
        super(
            idPerson, dniType, dniNumber, pName, sName, pLastName, sLastName
        );
    }


    @Override
    public String toString() {
        String presentacion =
            "\u001B[33m\n----------------------------------------------------------------------------------------\n" +
            "                                                                                          \n" +
            "   ID: " + getIdPerson() +
            "\n   Tipo de documento: " + getDniType() +
            "\n   Número de documento: " + getDniNumber() +
            "\n   Nombres: " + getPName() + " " + getSName() +
            "\n   Apellidos: " + getPLastName() + " " + getSLastName() +
            "\n   Ciudad residencia: " + getResidingCity() +
            "\n   Dirección: " + getAddressHome() +
            "\n   Teléfono: " + getPhone() +
            "\n   Fecha nacimiento: " + getBornDate() +
            "\n   Sexo: " + getSex() +
            "\n" +
            "\n----------------------------------------------------------------------------------------\n"
            
            ; 

        return presentacion;
    }
    
}
